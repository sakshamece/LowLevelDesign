import java.net.Socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class DistributedJobSchedulerTest {
    // Test case to verify if the master node schedules and distributes jobs correctly
    public void testJobScheduling() {
        // Create master node
        MasterNode masterNode = new MasterNode(8888);
        WorkerNode workerNode1 = null;
        WorkerNode workerNode2 = null;
        // Create worker nodes
        try {
            workerNode1 = new WorkerNode(new Socket("localhost", 8888));
            workerNode2 = new WorkerNode(new Socket("localhost", 8888));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return; // Exit the test if host is unknown
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit the test if an I/O error occurs
        }


        // Start worker nodes
        workerNode1.start();
        workerNode2.start();

        // Verify job scheduling
        for (int i = 0; i < 5; i++) {
            // Create job and distribute to worker nodes
            masterNode.scheduleJobs();

            // Wait for job execution
            try {
                Thread.sleep(2000); // assuming each job takes 2 seconds to execute
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DistributedJobSchedulerTest test = new DistributedJobSchedulerTest();
        test.testJobScheduling();
    }
}
