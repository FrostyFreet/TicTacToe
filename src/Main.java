import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Main {
    public static void printBoard(String[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean checkWin(String[][] board, String symbol) {
        // Rows and Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0]==symbol && board[i][1]==symbol && board[i][2]==symbol) ||
                    (board[0][i]==symbol && board[1][i]==symbol && board[2][i]==symbol)) {
                return true;
            }
        }

        // Diagonals
        if ((board[0][0]==symbol && board[1][1]==symbol && board[2][2]==symbol) ||
                (board[0][2]==symbol && board[1][1]==symbol && board[2][0]==symbol)) {
            return true;
        }
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            System.out.println("It's a draw!");
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String[][] board=new String[3][3];
        boolean playing=true;
        boolean player=true;

        int playerInput;
        String symbol;
        System.out.print("Choose your symbol(O,X):");
        symbol=input.next();
        while (!symbol.equalsIgnoreCase("O") && !symbol.equalsIgnoreCase("X")) {
            System.out.print("Invalid symbol. Please choose either O or X: ");
            symbol = input.next();
        }
        while(playing){
            System.out.println((player ? "Player 1" : "Player 2") + " follows with "+symbol.toUpperCase()+". Please input your coordinate (1-9):");
            playerInput=input.nextInt();

            if(playerInput>0 && playerInput<10){
                int row = (playerInput - 1) / 3;
                int col = (playerInput - 1) % 3;


                if(board[row][col]==null){
                    board[row][col]=symbol;
                    printBoard(board);

                    if(checkWin(board,symbol)){
                        System.out.println(player?"Player1 Wins":"Player2 Wins");
                        playing=false;
                    }
                    else{
                        player=!player;
                        symbol = (symbol.equals("O")) ? "X" : "O";
                    }
                }
                else{
                    System.out.println("Occupied");
                }

            }
            else {
                System.out.println("Invalid input. Please choose a number between 1 and 9.");
            }



        };


    }
}