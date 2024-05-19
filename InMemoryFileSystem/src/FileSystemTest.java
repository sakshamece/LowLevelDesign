import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class FileSystemTest {

    @Test
    public void testFileDeletion() {
        FileSystem file = new File("testFile.txt");
        assertTrue(file.delete());
    }

    @Test
    public void testDirectoryDeletion() {
        Directory directory = new Directory("testDirectory");
        assertTrue(directory.delete());
    }

    @Test
    public void testFileCopying() {
        FileSystem file = new File("testFile.txt");
        FileSystem copiedFile = file.copy("copiedFile.txt");
        assertTrue(copiedFile instanceof File);
        assertEquals("copiedFile.txt", ((File) copiedFile).fileName);
    }

    @Test
    public void testDirectoryCopying() {
        Directory directory = new Directory("testDirectory");
        directory.add(new File("file1.txt"));
        directory.add(new File("file2.txt"));

        FileSystem copiedDirectory = directory.copy("copiedDirectory");
        assertTrue(copiedDirectory instanceof Directory);
        assertEquals("copiedDirectory", ((Directory) copiedDirectory).directoryName);

        List<FileSystem> copiedFiles = ((Directory) copiedDirectory).fileSystemList;
        assertEquals(2, copiedFiles.size());
        assertTrue(copiedFiles.get(0) instanceof File);
        assertEquals("file1.txt", ((File) copiedFiles.get(0)).fileName);
        assertTrue(copiedFiles.get(1) instanceof File);
        assertEquals("file2.txt", ((File) copiedFiles.get(1)).fileName);
    }
}
