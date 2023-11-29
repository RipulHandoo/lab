package TICTACTOE;
import java.util.Random;
import java.util.Scanner;

public class tictactoe {
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private char currentPlayer;

    public tictactoe() {
        board = new char[3][3];
        currentPlayer = PLAYER_X; 
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isMagicSquare() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
    
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
    
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    private boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != EMPTY) {
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public void play() {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    while (true) {
        System.out.println("Current board:");
        printBoard();

        if (currentPlayer == PLAYER_X) {
            System.out.println("Player " + currentPlayer + ", make your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!makeMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }
        } else {
            System.out.println("Computer (Player " + currentPlayer + ") is making a move...");

            int row, col;
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
                if (isMagicSquare()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            } while (!makeMove(row, col));
        }

        if (isMagicSquare()) {
            System.out.println("Player " + currentPlayer + " wins!");
            break;
        }

        if (isBoardFull()) {
            System.out.println("It's a draw!");
            break;
        }

        switchPlayer();
    }
}


    public static void main(String[] args) {
        tictactoe ticTacToe = new tictactoe();
        ticTacToe.play();
    }
}