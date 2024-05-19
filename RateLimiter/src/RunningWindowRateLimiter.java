import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
public class RunningWindowRateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final Queue<Long> requestTimes;

    public RunningWindowRateLimiter(int maxRequests, long windowSize, TimeUnit timeUnit) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = timeUnit.toMillis(windowSize);
        this.requestTimes = new ArrayDeque<>(maxRequests);
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        cleanExpiredRequests(currentTime);
        if (requestTimes.size() < maxRequests) {
            requestTimes.add(currentTime);
            return true;
        }
        return false;
    }

    private void cleanExpiredRequests(long currentTime) {
        while (!requestTimes.isEmpty() && currentTime - requestTimes.peek() > windowSizeInMillis) {
            requestTimes.poll();
        }
    }
}
