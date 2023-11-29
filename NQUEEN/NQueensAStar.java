package NQUEEN;

import java.util.*;

public class NQueensAStar {
    static class State implements Comparable<State> {
        char[][] board;
        int gValue; // Cost to reach this state
        int hValue; // Heuristic value

        public State(char[][] board, int gValue, int hValue) {
            this.board = board;
            this.gValue = gValue;
            this.hValue = hValue;
        }

        @Override
        public int compareTo(State other) {
            // Compare based on the sum of g-value and h-value
            return Integer.compare(this.gValue + this.hValue, other.gValue + other.hValue);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        List<char[][]> solutions = solveNQueensAStar(n);

        if (solutions.isEmpty()) {
            System.out.println("No solution possible");
        }

        for (char[][] solution : solutions) {
            printSolution(solution);
            System.out.println("-------------------------");
        }
    }

    public static List<char[][]> solveNQueensAStar(int n) {
        List<char[][]> solutions = new ArrayList<>();
        PriorityQueue<State> pq = new PriorityQueue<>();

        char[][] initialBoard = new char[n][n];
        initializeBoard(initialBoard);

        pq.add(new State(initialBoard, 0, heuristic(initialBoard)));

        while (!pq.isEmpty()) {
            State currentState = pq.poll();

            if (currentState.hValue == 0) {
                // Found a solution
                solutions.add(currentState.board);
                continue;
            }

            int col = currentState.gValue % n; // Column to place the queen

            for (int row = 0; row < n; row++) {
                if (isSafe(currentState.board, row, col, n)) {
                    char[][] newBoard = copyBoard(currentState.board);
                    newBoard[row][col] = 'Q';

                    int newGValue = currentState.gValue + 1;
                    int newHValue = heuristic(newBoard);

                    pq.add(new State(newBoard, newGValue, newHValue));
                }
            }
        }

        return solutions;
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '.';
            }
        }
    }

    public static char[][] copyBoard(char[][] board) {
        char[][] copy = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }

    public static boolean isSafe(char[][] board, int row, int col, int n) {
        // Check in the same row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }

        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check lower left diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static int heuristic(char[][] board) {
        int conflicts = 0;
        int n = board.length;
    
        // Check columns
        for (int col = 0; col < n; col++) {
            int queensInColumn = 0;
            for (int row = 0; row < n; row++) {
                if (board[row][col] == 'Q') {
                    queensInColumn++;
                }
            }
            conflicts += queensInColumn * (queensInColumn - 1) / 2; // C(n,2) for each column
        }
    
        // Check diagonals
        for (int i = 0; i < n; i++) {
            // Check upper left diagonal
            for (int j = 0; j < n - i; j++) {
                if (board[i + j][j] == 'Q') {
                    conflicts++;
                }
            }
    
            // Check lower left diagonal
            for (int j = 0; j < n - i; j++) {
                if (board[j][i + j] == 'Q') {
                    conflicts++;
                }
            }
        }
    
        // Check upper right diagonal
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (board[j][i + j] == 'Q') {
                    conflicts++;
                }
            }
        }
    
        return conflicts;
    }
    

    public static void printSolution(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}