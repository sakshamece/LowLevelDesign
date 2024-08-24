import java.util.HashMap;

class UserManager {
    private final HashMap<Integer, User> users;

    public UserManager()
    {
        users = new HashMap<Integer, User>();
    }
    public Boolean addUser(int id, String details, String name)
    {
        if (users.containsKey(id)) {
            return false;
        }
        User user = new User(id, details, name);
        users.put(id, user);
        return true;
    }

    public Boolean addUser(User user)
    {
        if (users.containsKey(user.getId())) {
            return false;
        }

        users.put(user.getId(), user);
        return true;
    }

    public boolean remove(User u)
    {
        return remove(u.getId());
    }

    public boolean remove(int id)
    {
        if (users.containsKey(id)) {
            return false;
        }
        users.remove(id);
        return true;
    }

    public User find(int id)
    {
        return users.get(id);
    }
}
