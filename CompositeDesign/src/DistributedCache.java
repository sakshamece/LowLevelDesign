public class DistributedCache {

    private final DistributedCacheNode[] cacheNodes;

    DistributedCache(int numNodes) {
        this.cacheNodes = new DistributedCacheNode[numNodes];
        for (int i = 0; i < numNodes; i++) {
            cacheNodes[i] = new DistributedCacheNode();
        }
    }

    private int getCacheNodeIndex(String key) {
        // Use a simple hash function or consistent hashing algorithm to determine the cache node index
        return Math.abs(key.hashCode()) % cacheNodes.length;
    }

    String get(String key) {
        int nodeIndex = getCacheNodeIndex(key);
        return cacheNodes[nodeIndex].get(key);
    }

    void put(String key, String value) {
        int nodeIndex = getCacheNodeIndex(key);
        cacheNodes[nodeIndex].put(key, value);
    }

    void delete(String key) {
        int nodeIndex = getCacheNodeIndex(key);
        cacheNodes[nodeIndex].delete(key);
    }
}
