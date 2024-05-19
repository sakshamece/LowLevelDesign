import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {

    String directoryName;
    List<FileSystem> fileSystemList;

    public Directory(String directoryName) {
        this.directoryName = directoryName;
        fileSystemList = new ArrayList<>();
    }

    public void ls() {
        System.out.println("Directory Name: " + directoryName);

        for (FileSystem fileSystem : fileSystemList) {
            fileSystem.ls();
        }
    }

    public void add(FileSystem fileSystem) {
        fileSystemList.add(fileSystem);
    }

    public boolean delete() {
        // Implement directory deletion logic
        System.out.println("Deleting directory: " + directoryName);
        return true; // Assuming deletion is successful
    }

    public FileSystem copy(String newName) {
        // Implement directory copying logic
        System.out.println("Copying directory: " + directoryName + " to " + newName);
        Directory newDirectory = new Directory(newName);
        for (FileSystem fileSystem : fileSystemList) {
            newDirectory.add(fileSystem.copy(fileSystem instanceof File ?
                    ((File) fileSystem).fileName : "NewFile"));
        }
        return newDirectory;
    }
}
