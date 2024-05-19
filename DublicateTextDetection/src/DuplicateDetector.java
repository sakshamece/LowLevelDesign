import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

// Singleton class for duplicate detection
class DuplicateDetector {
    private static DuplicateDetector instance;
    private Set<String> hashes;

    private DuplicateDetector() {
        hashes = new HashSet<>();
    }

    public static synchronized DuplicateDetector getInstance() {
        if (instance == null) {
            instance = new DuplicateDetector();
        }
        return instance;
    }

    public boolean isDuplicate(String data) {
        String hash = calculateHash(data);
        return !hashes.add(hash); // Returns true if the hash is already present (duplicate)
    }

    private String calculateHash(String data) {
        if (data == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}