import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LRUCache {
    private final int cacheSize;
    private final HashSet<Integer> hashSet;
    private final ConcurrentLinkedDeque<Integer> deque;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.hashSet = new HashSet<>();
        this.deque = new ConcurrentLinkedDeque<>();
    }

    public void refer(int page)
    {
        synchronized (this) {
            if (!hashSet.contains(page)) {
                if (deque.size() == cacheSize) {
                    int last = deque.removeLast();
                    hashSet.remove(last);
                }
            } else { /* The found page may not be always the last
                element, even if it's an intermediate
                element that needs to be removed and added
                to the start of the Queue */
                deque.remove(page);
            }
            deque.push(page);
            hashSet.add(page);
        }
    }

    // display contents of cache
    public void display() {
        for (Integer integer : deque) {
            System.out.print(integer + " ");
        }
    }
}
