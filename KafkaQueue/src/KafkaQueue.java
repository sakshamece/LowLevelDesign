import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class KafkaQueue {
    public static class SubscriberWorker implements Runnable {
        private final Topic topic;
        private final TopicSubscriber topicSubscriber;

        public SubscriberWorker(final Topic topic, final TopicSubscriber topicSubscriber) {
            this.topic = topic;
            this.topicSubscriber = topicSubscriber;
        }

        @Override
        public void run() {
            synchronized (topicSubscriber) {
                do {
                    int curOffset = topicSubscriber.getOffset().get();
                    while (curOffset >= topic.getMessages().size()) {
                        try {
                            topicSubscriber.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Message message = topic.getMessages().get(curOffset);
                    try {
                        topicSubscriber.getSubscriber().consume(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    topicSubscriber.getOffset().compareAndSet(curOffset, curOffset + 1);
                } while (true);
            }
        }

        synchronized public void wakeUpIfNeeded() {
            synchronized (topicSubscriber) {
                topicSubscriber.notify();
            }
        }
    }

    public static class TopicHandler {
        private final Topic topic;
        private final Map<String, SubscriberWorker> subscriberWorkers;

        public TopicHandler(final Topic topic) {
            this.topic = topic;
            subscriberWorkers = new HashMap<>();
        }

        public void publish() {
            for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
                startSubscriberWorker(topicSubscriber);
            }
        }

        public void startSubscriberWorker(final TopicSubscriber topicSubscriber) {
            final String subscriberId = topicSubscriber.getSubscriber().getId();
            if (!subscriberWorkers.containsKey(subscriberId)) {
                final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
                subscriberWorkers.put(subscriberId, subscriberWorker);
                new Thread(subscriberWorker).start();
            }
            final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
            subscriberWorker.wakeUpIfNeeded();
        }
    }

    public static class Message {
        private String msg;

        public Message(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    public static class Topic {
        private final String topicName;
        private final String topicId;
        private final List<Message> messages;
        private final List<TopicSubscriber> subscribers;

        public Topic(final String topicName, final String topicId) {
            this.topicName = topicName;
            this.topicId = topicId;
            this.messages = new ArrayList<>();
            this.subscribers = new ArrayList<>();
        }

        public synchronized void addMessage(final Message message) {
            messages.add(message);
        }

        public void addSubscriber(final TopicSubscriber subscriber) {
            subscribers.add(subscriber);
        }

        public List<Message> getMessages() {
            return messages;
        }

        public List<TopicSubscriber> getSubscribers() {
            return subscribers;
        }

        public String getTopicId() {
            return topicId;
        }

        public String getTopicName() {
            return topicName;
        }
    }

    public static class TopicSubscriber {
        private final AtomicInteger offset;
        private final ISubscriber subscriber;

        public TopicSubscriber(final ISubscriber subscriber) {
            this.subscriber = subscriber;
            this.offset = new AtomicInteger(0);
        }

        public AtomicInteger getOffset() {
            return offset;
        }

        public ISubscriber getSubscriber() {
            return subscriber;
        }
    }

    public static interface ISubscriber {
        String getId();

        void consume(Message message) throws InterruptedException;
    }

    public static class Queue {
        private final Map<String, TopicHandler> topicProcessors;

        public Queue() {
            this.topicProcessors = new HashMap<>();
        }

        public Topic createTopic(final String topicName) {
            final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
            TopicHandler topicHandler = new TopicHandler(topic);
            topicProcessors.put(topic.getTopicId(), topicHandler);
            System.out.println("Created topic: " + topic.getTopicName());
            return topic;
        }

        public void subscribe(final ISubscriber subscriber, final Topic topic) {
            topic.addSubscriber(new TopicSubscriber(subscriber));
            System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
        }

        public void publish(final Topic topic, final Message message) {
            topic.addMessage(message);
            System.out.println(message.getMsg() + " published to topic: " + topic.getTopicName());
            new Thread(() -> topicProcessors.get(topic.getTopicId()).publish()).start();
        }

        public void resetOffset(final Topic topic, final ISubscriber subscriber, final Integer newOffset) {
            for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
                if (topicSubscriber.getSubscriber().equals(subscriber)) {
                    topicSubscriber.getOffset().set(newOffset);
                    System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                    new Thread(() -> topicProcessors.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
                    break;
                }
            }
        }
    }

    public static class SleepingSubscriber implements ISubscriber {
        private final String id;
        private final int sleepTimeInMillis;

        public SleepingSubscriber(String id, int sleepTimeInMillis) {
            this.id = id;
            this.sleepTimeInMillis = sleepTimeInMillis;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void consume(Message message) throws InterruptedException {
            System.out.println("Subscriber: " + id + " started consuming: " + message.getMsg());
            Thread.sleep(sleepTimeInMillis);
            System.out.println("Subscriber: " + id + " done consuming: " + message.getMsg());
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        run(args);
    }

    public static void run(String[] args) throws Exception {
        final Queue queue = new Queue();
        final Topic topic1 = queue.createTopic("t1");
        final Topic topic2 = queue.createTopic("t2");
        SleepingSubscriber sub1 = new SleepingSubscriber("sub1", 10000);
        SleepingSubscriber sub2 = new SleepingSubscriber("sub2", 10000);
        queue.subscribe(sub1, topic1);
        queue.subscribe(sub2, topic1);

        SleepingSubscriber sub3 = new SleepingSubscriber("sub3", 5000);
        queue.subscribe(sub3, topic2);

        queue.publish(topic1, new Message("m1"));
        queue.publish(topic1, new Message("m2"));

        queue.publish(topic2, new Message("m3"));

        Thread.sleep(15000);
        queue.publish(topic2, new Message("m4"));
        queue.publish(topic1, new Message("m5"));

        queue.resetOffset(topic1, sub1, 0);
    }
}
