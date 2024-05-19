public class Email implements NotificationSender{

    @Override
    public void sendNotification() {
        System.out.println("We are sending a EMAIL");
    }
}
