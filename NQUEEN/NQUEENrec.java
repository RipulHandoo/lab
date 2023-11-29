package NQUEEN;
import java.util.*;

public class NQUEENrec {
    char[][] board;

    public NQUEENrec(int n){
        board = new char[n][n];
        initializeBoard();
    }

    public void initializeBoard(){
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                board[i][j] = '.';
            }
        }
    }

    public void solvePuzzle(int col, List<char[][]> ans, int n){
        // /base case
        if(col == n){
           ans.add(copyBoard());
           return;
        }

        for(int row = 0; row<n; row++){
            if(isSafe(row, col, n)){
                board[row][col] = 'Q';
                solvePuzzle(col+1, ans, n);
                board[row][col] = '.';
            }
        }
    }

    public char[][] copyBoard() {
        char[][] copy = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }

    public boolean isSafe(int row, int col, int n){
        int dupRow = row;
        int dupCol = col;

        // check in the upper left diagonal
        while(row >= 0 && col >= 0){
            if(board[row][col] == 'Q'){
                return false;
            }
            row--;
            col--;
        }

        // check in the same row

        row = dupRow;
        col = dupCol;

        while(col >= 0){
            if(board[row][col] == 'Q'){
                return false;
            }
            col--;
        }


        // check in the low left diagonal
        row = dupRow;
        col = dupCol;
        while(row < n && col >= 0){
            if(board[row][col] == 'Q'){
                return false;
            }
            row++;
            col--;
        }

        return true;
    }

    public static void printSolution(char[][] ans){
        for(int i = 0;i<ans.length;i++){
            for(int j = 0;j<ans[i].length;j++){
                System.out.print(ans[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public static void main(String[] args){
        int n = 4;
        NQUEENrec solve = new NQUEENrec(n);
        List<char[][]> ans = new ArrayList<>();
        solve.solvePuzzle(0,ans,n);

        if(ans.size() == 0){
            System.out.println("No solution possible");
        }

        for(int i = 0;i<ans.size();i++){
            printSolution(ans.get(i));
            System.out.println(ans.size());
            System.out.println("-------------------------");
        }
    }
}
