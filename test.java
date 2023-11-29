import java.util.*;

public class test {
    private char Player_X = 'x';
    private char Player_O = 'o';
    private char Empty = '-';

    // declare board 
    private char[][] board;
    private char currentPlayer;


    private test(){
        board = new char[3][3];
        currentPlayer = Player_X;
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i = 0;i < 3;i++){
            for(int j = 0;j<3;j++){
                board[i][j] = Empty;
            }
        }
    }

    private void switchPlayer(){
        if(currentPlayer == Player_X){
            currentPlayer = Player_O;
        }else{
            currentPlayer = Player_X;
        }
    }

    private boolean isBoardFull(){
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if(board[i][j] == Empty){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean makeMove(int row, int col){
        if(row < 0 || row > 3 || col < 0 || col > 3 || board[row][col] != Empty){
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    private void play(){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        while(true){
            System.out.println("Current Board");
            printBoard();
            if(currentPlayer == Player_X){
                System.out.println("Enter Coordaintes Player_X: ");
                int row = sc.nextInt();
                int col = sc.nextInt();

                if(!makeMove(row, col)){
                    System.out.println("Invalid move. Please try again");
                    continue;
                }
            }else{
                int row;
                int col;
                do{
                    row = rand.nextInt(3);
                    col = rand.nextInt(3);
                    if(isMagicSquare()){
                        System.out.println("Player "+currentPlayer+" Wins!!!!!!!!!!!!!!!!!!!");
                        break;
                    }
                }while(!makeMove(row, col));
            }

            if(isBoardFull()){
                System.out.println("Its a Draw");
                break;
            }

            if(isMagicSquare()){
                System.out.println("Player "+ currentPlayer + " wins!!!!!!!!!!!!!");
                break;
            }

            switchPlayer();
        }

    }

    private boolean isMagicSquare(){
        // check in the row
        for(int i = 0;i<3;i++){
            if(board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer){
                return true;
            }
        }

        // check in the col
        for(int j = 0;j<3;j++){
            if(board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer){
                return true;
            }
        }

        // check diagonaly
        if(board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer){
            return true;
        }

        if(board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer){
            return true;
        }

        return false;
    }


    private void printBoard(){
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args) {
        test game = new test();
        game.play();
    }
}
