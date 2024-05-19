public interface FileSystem {
    void ls();
    boolean delete();
    FileSystem copy(String newName);
}
