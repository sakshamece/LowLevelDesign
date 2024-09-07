import java.util.List;

public interface FileSystemComponent {
    String getName();
    void rename(String newName);
    void delete();
    void display(String indent);
    void add(FileSystemComponent component) throws UnsupportedOperationException;
    void remove(FileSystemComponent component) throws UnsupportedOperationException;
    List<FileSystemComponent> getChildren() throws UnsupportedOperationException;
    String read() throws UnsupportedOperationException;
    void write(String content) throws UnsupportedOperationException;
    FileSystemComponent search(String name);
    void copyTo(FileSystemComponent destination);
    void moveTo(FileSystemComponent destination);
}
