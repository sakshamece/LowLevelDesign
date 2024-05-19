public class File implements FileSystem {

    String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public void ls() {
        System.out.println("File Name: " + fileName);
    }

    public boolean delete() {
        // Implement file deletion logic
        System.out.println("Deleting file: " + fileName);
        return true; // Assuming deletion is successful
    }

    public FileSystem copy(String newName) {
        // Implement file copying logic
        System.out.println("Copying file: " + fileName + " to " + newName);
        return new File(newName);
    }
}
