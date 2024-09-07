import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemComponent {
    private String name;
    private final List<FileSystemComponent> components;

    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
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
        for (FileSystemComponent component : components) {
            component.delete();
        }
        components.clear();
        System.out.println("Deleting directory: " + name);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Directory: " + name);
        for (FileSystemComponent component : components) {
            component.display(indent + "  ");
        }
    }

    @Override
    public void add(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public List<FileSystemComponent> getChildren() {
        return components;
    }

    @Override
    public String read() {
        throw new UnsupportedOperationException("Cannot read a directory.");
    }

    @Override
    public void write(String content) {
        throw new UnsupportedOperationException("Cannot write to a directory.");
    }

    @Override
    public FileSystemComponent search(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        for (FileSystemComponent component : components) {
            FileSystemComponent result = component.search(name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public void copyTo(FileSystemComponent destination) {
        if (destination instanceof Directory) {
            Directory copy = new Directory(this.name);
            for (FileSystemComponent component : components) {
                component.copyTo(copy);
            }
            destination.add(copy);
        } else {
            throw new UnsupportedOperationException("Cannot copy directory to non-directory.");
        }
    }

    @Override
    public void moveTo(FileSystemComponent destination) {
        this.copyTo(destination);
        this.delete();
    }
}
