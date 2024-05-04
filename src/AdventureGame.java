import java.util.Scanner;
import java.util.Random;

public class AdventureGame {

    public static void main(String[] args) {
        char[][] forest = generateForest(10, 10);
        Scanner scanner = new Scanner(System.in);
        displayForest(forest);

        //  user input
        String input;
        System.out.println("Use 'W' (up), 'S' (down), 'A' (left), 'D' (right) to move. Type 'exit' to quit.");
        while (true) {
            System.out.print("Enter your move: ");
            input = scanner.nextLine().toUpperCase();
            if ("EXIT".equals(input)) {
                break;
            }
            if (input.length() == 1 && "WSAD".contains(input)) {
                movePlayer(forest, input.charAt(0));
                displayForest(forest);
            } else {
                System.out.println("Invalid command. Please enter W, S, A, D, or exit.");
            }
        }
        scanner.close();
    }

    public static char[][] generateForest(int rows, int cols) {
        Random random = new Random();
        char[][] forest = new char[rows][cols];

        // Populate forest
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                forest[i][j] = random.nextDouble() < 0.7 ? '.' : 'T';
            }
        }

        // Place player at random empty location
        int playerRow, playerCol;
        do {
            playerRow = random.nextInt(rows);
            playerCol = random.nextInt(cols);
        } while (forest[playerRow][playerCol] == 'T');
        forest[playerRow][playerCol] = 'P';

        return forest;
    }

    public static void displayForest(char[][] forest) {
        for (char[] row : forest) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void movePlayer(char[][] forest, char direction) {
        int playerRow = -1, playerCol = -1;
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[i].length; j++) {
                if (forest[i][j] == 'P') {
                    playerRow = i;
                    playerCol = j;
                    break;
                }
            }
            if (playerRow != -1) break;
        }

        int newRow = playerRow, newCol = playerCol;
        switch (direction) {
            case 'W': newRow--; break;
            case 'S': newRow++; break;
            case 'A': newCol--; break;
            case 'D': newCol++; break;
        }

        if (newRow >= 0 && newRow < forest.length && newCol >= 0 && newCol < forest[newRow].length) {
            if (forest[newRow][newCol] == '.') {
                forest[playerRow][playerCol] = '.';
                forest[newRow][newCol] = 'P';
            } else {
                System.out.println("Invalid move: You cannot move into a tree.");
            }
        } else {
            System.out.println("Invalid move: Out of bounds.");
        }
    }
}