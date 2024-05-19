import java.io.Serializable;

class Singleton implements Serializable {

    private static Singleton instance = null;

    private Singleton() {
        if (instance != null) {
            throw new RuntimeException("You have broken Singleton class!");
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public Object readResolve() {
        
        return instance;
    }

}