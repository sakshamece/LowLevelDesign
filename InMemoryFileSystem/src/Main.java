public class Main {
    public static void main(String[] args) {

        FileSystemComponent root = new Directory("root");
        FileSystemComponent home = new Directory("home");
        FileSystemComponent user = new Directory("user");
        FileSystemComponent file1 = new File("file1.txt");
        FileSystemComponent file2 = new File("file2.txt");

        try {
            root.add(home);
            home.add(user);
            user.add(file1);
            user.add(file2);

            System.out.println("Initial File System Structure:");
            root.display("");

            System.out.println("\nReading file1.txt:");
            file1.write("Hello, World!");
            System.out.println(file1.read());

            System.out.println("\nListing files in 'user' directory:");
            user.display("  ");

            System.out.println("\nRenaming file1.txt to 'document.txt':");
            file1.rename("document.txt");
            user.display("  ");

            System.out.println("\nDeleting file2.txt:");
            user.remove(file2);
            user.display("  ");

            System.out.println("\nCopying 'user' directory to 'home':");
            user.copyTo(home);
            home.display("");

            System.out.println("\nMoving 'document.txt' to 'home':");
            file1.moveTo(home);
            home.display("");
            user.display("  ");

            System.out.println("\nSearching for 'document.txt':");
            FileSystemComponent searchResult = root.search("document.txt");
            if (searchResult != null) {
                System.out.println("Found: " + searchResult.getName());
            } else {
                System.out.println("File not found");
            }

        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }
}
