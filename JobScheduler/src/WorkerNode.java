import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
class WorkerNode extends Thread {
    public final Socket socket;
    public final ObjectInputStream inputStream;
    public final ObjectOutputStream outputStream;

    public WorkerNode(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Receive job from master
                Job job = (Job) inputStream.readObject();
                System.out.println("Worker received job: " + job.getJobId());

                // Simulate job execution
                Thread.sleep(1000);

                // Send response to master
                outputStream.writeObject("Job " + job.getJobId() + " completed");
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
