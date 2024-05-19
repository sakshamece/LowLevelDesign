import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeLadder {
    public Board board;
    List<User> users = new ArrayList<>();
    List<User> winnerList = new ArrayList<>();
    public void addUser(User newUser) {
        users.add(newUser);
    }

    public SnakeLadder(Board board, List<User> userList) {
        this.board = board;
        this.users = userList;
    }

    public int rollDice() {
        Random r = new Random();
        int value = r.nextInt(6) + 1;
        if (value == 6) {
            value += r.nextInt(6) + 1;
        }
        if (value == 12) {
            value += r.nextInt(6) + 1;
        }
        if (value == 18) {
            return 0;
        }
        return value;
    }

    public void simulate() {
        int current = 0;
        int ite = 0;
        while (winnerList.size() < users.size()) {

            if (current >= users.size() - 1) {

                current = 0;
            } else {
                current += 1;

            }
            //System.out.println(current);
            User currUser = users.get(current);
            if (currUser.position == 100) {
                continue;
            }
            int rollValue = rollDice();
            board.advanced(currUser, rollValue);
            if (currUser.position == board.winingPositon) {
                winnerList.add(currUser);
                System.out.println(" We have a " + winnerList.size() + " winner " + currUser.name);
            }
            for (User user : users) {
                System.out.println(" UserName " + user.name + " current Position " + user.position);
            }
            ite += 1;
        }
        System.out.println(" Simulation finished Total Iteration " + ite);
    }
}
