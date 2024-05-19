import java.io.Serializable;

class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int jobId;

    public Job(int jobId) {
        this.jobId = jobId;
    }

    public int getJobId() {
        return jobId;
    }
}