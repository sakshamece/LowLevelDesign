import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        RateLimiter rateLimiter = RateLimiter.getInstance(5 ,1 , TimeUnit.MILLISECONDS);

        for (int i = 0; i < 10; i++) {
            if (rateLimiter.tryAcquire()) {
                System.out.println("Operation " + (i + 1) + " allowed.");
            } else {
                System.out.println("Operation " + (i + 1) + " blocked due to rate limiting.");
            }

            try {
                Thread.sleep(300); // Simulate some processing time between operations
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        // cache node

        DistributedCache distributedCache = new DistributedCache(5);

        distributedCache.put("key1", "value1");
        distributedCache.put("key2", "value2");
        distributedCache.put("key3", "value3");

        // Retrieve data from the cache
        System.out.println("Value for key1: " + distributedCache.get("key1"));
        System.out.println("Value for key2: " + distributedCache.get("key2"));
        System.out.println("Value for key3: " + distributedCache.get("key3"));

        // Delete data from the cache
        distributedCache.delete("key2");

        // Try to retrieve the deleted data
        System.out.println("Value for key2 after deletion: " + distributedCache.get("key2"));

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("inside run function");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("inside run function2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(runnable1);
        System.out.println("name of thread :" + t1.getName());
        t1.start();

        Thread t2 = new Thread(runnable2);
        t2.start();



        RunnableDemo runnableDemo = new RunnableDemo("th1");
        runnableDemo.start();

        LRUCache lruCache = new LRUCache(5);

        lruCache.refer(2);
        lruCache.refer(43);
        lruCache.refer(2);

        lruCache.display();


        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        Thread t1 = new Thread(threadLocalDemo);
        Thread t2 = new Thread(threadLocalDemo);
        Thread t3 = new Thread(threadLocalDemo);
        Thread t4 = new Thread(threadLocalDemo);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            System.out.println("interrupt");
        }

    }


}