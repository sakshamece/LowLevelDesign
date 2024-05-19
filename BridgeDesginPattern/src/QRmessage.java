public class QRmessage extends Notification {
    public QRmessage(NotificationSender notificationSender) {
        super(notificationSender);
        System.out.println("this is a QR message");
    }

    @Override
    void sendMessage() {
        notificationSender.sendNotification();
    }
}
