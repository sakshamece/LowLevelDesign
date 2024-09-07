import java.util.List;

public class File implements FileSystemComponent {
    private String name;
    private String content;

    public File(String name) {
        this.name = name;
        this.content = "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void rename(String newName) {
        this.name = newName;
    }

    @Override
    public void delete() {
        // Code to delete the file from the file system
        System.out.println("Deleting file: " + name);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "File: " + name);
    }

    @Override
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add to a file.");
    }

    @Override
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a file.");
    }

    @Override
    public List<FileSystemComponent> getChildren() {
        throw new UnsupportedOperationException("File does not have children.");
    }

    @Override
    public String read() {
        return content;
    }

    @Override
    public void write(String content) {
        this.content = content;
    }

    @Override
    public FileSystemComponent search(String name) {
        return this.name.equals(name) ? this : null;
    }

    @Override
    public void copyTo(FileSystemComponent destination) {
        if (destination != null) {
            File copy = new File(this.name);
            copy.write(this.content);
            destination.add(copy);
        } else {
            throw new UnsupportedOperationException("Cannot copy file to non-directory.");
        }
    }

    @Override
    public void moveTo(FileSystemComponent destination) {
        this.copyTo(destination);
        this.delete();
    }
}
