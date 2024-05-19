import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MasterNode {
    private final List<WorkerNode> workers = new ArrayList<>();
    private int jobIdCounter = 0;

    public MasterNode(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Master node started on port " + port);

            // Start accepting worker connections
            acceptWorkers(serverSocket);

            // Schedule and distribute jobs
            scheduleJobs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Accept worker connections
    private void acceptWorkers(ServerSocket serverSocket) throws IOException {
        while (workers.size() < 2) { // assuming at least 2 worker nodes
            Socket socket = serverSocket.accept();
            WorkerNode workerNode = new WorkerNode(socket);
            workerNode.start();
            workers.add(workerNode);
        }
    }

    // Schedule and distribute jobs to worker nodes
    void scheduleJobs() {
        while (true) {
            try {
                // Create job
                Job job = new Job(jobIdCounter++);

                // Distribute job to a worker node
                WorkerNode worker = workers.get(jobIdCounter % workers.size());
                ObjectOutputStream outputStream = worker.outputStream;
                outputStream.writeObject(job);

                // Receive response from worker
                ObjectInputStream inputStream = worker.inputStream;
                String response = (String) inputStream.readObject();
                System.out.println("Master received response: " + response);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
