import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DuplicateDetectorTest {

    @Test
    void testDuplicateDetection() {
        DuplicateDetector detector = DuplicateDetector.getInstance();

        // Add some data
        assertFalse(detector.isDuplicate("Data1")); // First occurrence, should return false
        assertTrue(detector.isDuplicate("Data1"));  // Duplicate occurrence, should return true
        assertFalse(detector.isDuplicate("Data2")); // New data, should return false
        assertTrue(detector.isDuplicate("Data2"));  // Duplicate occurrence, should return true
    }

    @Test
    void testEmptyData() {
        DuplicateDetector detector = DuplicateDetector.getInstance();

        // Test with empty data
        assertFalse(detector.isDuplicate(""));
        assertFalse(detector.isDuplicate(null));
    }
}
