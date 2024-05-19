import java.util.Scanner;
import java.util.Random;

class Cell {
    private int value;
    private boolean isInitial;

    public Cell() {
        this.value = 0;
        this.isInitial = false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public void setInitial(boolean isInitial) {
        this.isInitial = isInitial;
    }
}

class SudokuGrid {
    private Cell[][] grid;

    public SudokuGrid() {
        grid = new Cell[9][9];
        // Initialize all cells
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, int value) {
        grid[row][col].setValue(value);
    }

    public boolean isCellInitial(int row, int col) {
        return grid[row][col].isInitial();
    }

    public void setCellInitial(int row, int col, boolean isInitial) {
        grid[row][col].setInitial(isInitial);
    }
}

class SudokuGame {
    private SudokuGrid grid;

    public SudokuGame() {
        grid = new SudokuGrid();
    }

    public void generateNewPuzzle() {
        // This method would generate a new Sudoku puzzle
        // You can implement your Sudoku generator algorithm here
        // For demonstration purposes, let's just fill the grid with random numbers
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int randomNumber = random.nextInt(9) + 1;
                grid.setCell(i, j, randomNumber);
                grid.setCellInitial(i, j, true); // Mark as initial
            }
        }
    }

    public SudokuGrid getGrid() {
        return grid;
    }
}

class SudokuController {
    private SudokuGame game;
    private Scanner scanner;

    public SudokuController() {
        game = new SudokuGame();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Sudoku!");
        playGame();
    }

    private void playGame() {
        game.generateNewPuzzle();
        SudokuGrid grid = game.getGrid();
        while (!isGameOver(grid)) {
            displayGrid(grid);
            int row = getValidInput("Enter row (1-9) or 0 to reload: ", 0, 9) - 1;
            if (row == -1) {
                game.generateNewPuzzle();
                grid = game.getGrid();
                continue;
            }
            int col = getValidInput("Enter column (1-9): ", 1, 9) - 1;
            if (!grid.isCellInitial(row, col)) {
                int value = getValidInput("Enter value (1-9): ", 1, 9);
                grid.setCell(row, col, value);
            } else {
                System.out.println("Cannot modify initial cell!");
            }
        }
        displayGrid(grid);
        System.out.println("Congratulations! You solved the Sudoku!");
    }

    private boolean isGameOver(SudokuGrid grid) {
        // Check if the grid is filled
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid.getCell(i, j).getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void displayGrid(SudokuGrid grid) {
        System.out.println("   1 2 3   4 5 6   7 8 9");
        System.out.println("  -------------------------");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println(" |-------+-------+-------|");
            }
            System.out.print(i + 1 + "|");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print(" ");
                }
                int value = grid.getCell(i, j).getValue();
                System.out.print(value == 0 ? " " : value);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("  -------------------------");
    }

    private int getValidInput(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }
}

public class Main {
    public static void main(String[] args) {
        SudokuController controller = new SudokuController();
        controller.startGame();
    }
}
