public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        ticTacToeGame.initializeGame();
        System.out.println("game winner is: " + ticTacToeGame.startGame());
    }
}