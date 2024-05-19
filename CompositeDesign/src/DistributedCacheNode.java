import java.util.concurrent.ConcurrentHashMap;

public class DistributedCacheNode {
    private final ConcurrentHashMap<String, String> cacheData;

    public DistributedCacheNode() {
        this.cacheData = new ConcurrentHashMap<>();
    }

    String get(String key) {
        return cacheData.get(key);
    }

    void put(String key, String value) {
        cacheData.put(key, value);
    }

    void delete(String key) {
        cacheData.remove(key);
    }
}
