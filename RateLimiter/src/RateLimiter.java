import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiter {
    private static RateLimiter instance;
    private final int maxTokens;
    private final long refillInterval;
    private long lastRefillTimestamp;
    private int availableToken;
    private final Lock lock;

    private RateLimiter(int maxTokens, long refillInterval, TimeUnit timeUnit) {
        this.maxTokens = maxTokens;
        this.refillInterval = timeUnit.toMillis(refillInterval);
        this.lastRefillTimestamp = System.currentTimeMillis();
        this.availableToken = maxTokens;
        this.lock = new ReentrantLock();
    }

    public static synchronized RateLimiter getInstance(int maxTokens, long refillInterval, TimeUnit timeUnit) {
        if (instance == null) {
            instance = new RateLimiter(maxTokens, refillInterval, timeUnit);
        }
        return instance;
    }

    public boolean tryAcquire() {
        lock.lock();
        try {
            refillTokens();
            if(availableToken > 0) {
                availableToken--;
                return  true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void refillTokens() {

        long currentTimestamp = System.currentTimeMillis();
        long elapsedTime = currentTimestamp - lastRefillTimestamp;
        if (elapsedTime > refillInterval) {
            long tokensToBeAdded = elapsedTime / refillInterval;
            lastRefillTimestamp = currentTimestamp;
            availableToken = Math.max(maxTokens, availableToken + (int) tokensToBeAdded);
        }
    }
}
