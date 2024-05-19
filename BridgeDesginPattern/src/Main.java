public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        QRmessage qrMessage = new QRmessage(new SMS());
        qrMessage.sendMessage();


        TextMessage textMessage = new TextMessage(new Email());
        textMessage.sendMessage();
    }
}