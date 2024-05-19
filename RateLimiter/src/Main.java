import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RateLimiter rateLimiter = RateLimiter.getInstance(5 ,1 , TimeUnit.MILLISECONDS);

        for (int i = 0; i < 10; i++) {
            if (rateLimiter.tryAcquire()) {
                System.out.println("Operation " + (i + 1) + " allowed.");
            } else {
                System.out.println("Operation " + (i + 1) + " blocked due to rate limiting.");
            }

            try {
                Thread.sleep(300); // Simulate some processing time between operations
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}