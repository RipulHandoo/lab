package TICTACTOE;

import java.util.Scanner;

public class TicTacToeMinMax {

    public static void main(String[] args) {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        printBoard(board);

        while (true) {
            userMove(board);
            printBoard(board);
            if (checkWin(board, 'X')) {
                System.out.println("You win!");
                break;
            }
            if (isBoardFull(board)) {
                System.out.println("It's a draw!");
                break;
            }

            computerMove(board);
            printBoard(board);
            if (checkWin(board, 'O')) {
                System.out.println("Computer wins!");
                break;
            }
            if (isBoardFull(board)) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    private static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void userMove(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.print("Enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (board[row][col] != ' ');

        board[row][col] = 'X';
    }

    private static void computerMove(char[][] board) {
        int[] bestMove = minimax(board, 'O');
        board[bestMove[0]][bestMove[1]] = 'O';
    }

    private static int[] minimax(char[][] board, char player) {
        int[] bestMove = {-1, -1};
        int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = player;
                    int score = minimaxHelper(board, 0, false);
                    board[i][j] = ' ';

                    if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimaxHelper(char[][] board, int depth, boolean isMaximizing) {
        if (checkWin(board, 'X')) {
            return -1;
        }
        if (checkWin(board, 'O')) {
            return 1;
        }
        if (isBoardFull(board)) {
            return 0;
        }

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        maxScore = Math.max(maxScore, minimaxHelper(board, depth + 1, false));
                        board[i][j] = ' ';
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        minScore = Math.min(minScore, minimaxHelper(board, depth + 1, true));
                        board[i][j] = ' ';
                    }
                }
            }
            return minScore;
        }
    }

    private static boolean checkWin(char[][] board, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

