public class TextMessage extends Notification {

    public TextMessage(NotificationSender notificationSender) {
        super(notificationSender);
        System.out.println("this is a text message");
    }

    @Override
    void sendMessage() {
        notificationSender.sendNotification();
    }
}
