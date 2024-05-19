public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int port = 8888; // Master node port
        new MasterNode(port);
    }
}