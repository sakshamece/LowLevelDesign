import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache {
    private final int cacheSize;
    private final HashSet<Integer> hashSet;
    private final ConcurrentLinkedDeque<Integer> deque;
    private final ReadWriteLock lock;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.hashSet = new HashSet<>();
        this.deque = new ConcurrentLinkedDeque<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public void refer(int page) {
        lock.writeLock().lock();
        try {
            if(hashSet.contains(page)) {
                deque.remove(page);
            } else {
                if(deque.size() == cacheSize) {
                    int last = deque.removeLast();
                    hashSet.remove(last);
                }
            }
            deque.push(page);
            hashSet.add(page);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // display contents of cache
    public void display() {
        lock.readLock().lock();
        try {
            for (Integer integer : deque) {
                System.out.print(integer + " ");
            }
        } finally {
            lock.readLock().unlock();
        }
    }
}
