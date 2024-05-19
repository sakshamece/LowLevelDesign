public class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        // Test case 2: Add elements to the cache
        cache.refer(1);
        cache.refer(2);
        cache.refer(3);
        cache.display(); // Expected output: 3 2 1
        System.out.println();

        // Test case 3: Add duplicate element
        cache.refer(2);
        cache.display(); // Expected output: 2 3 1
        System.out.println();

        // Test case 4: Add more elements than cache capacity
        cache.refer(4);
        cache.refer(5);
        cache.display(); // Expected output: 5 4 2
        System.out.println();

        // Test case 5: Add elements beyond cache capacity
        cache.refer(6);
        cache.refer(7);
        cache.display(); // Expected output: 7 6 5
        System.out.println();
    }
}