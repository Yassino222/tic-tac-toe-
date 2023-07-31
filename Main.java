import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // PLAYERS NAME INPUT
        System.out.print("Player 1, What's your name?");
        String P1 = in.nextLine();
        System.out.print("Player 2, what's your name?");
        String P2 = in.nextLine();

        // 3x3 board
        // x player 1, o player 2, --- empty
        char[][] board = new char[3][3];

        // Filling the board --
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        // We need to keep track of whose turn it's (to stay consistent)
        boolean isPlayer1 = true;

        // Check if game ended
        boolean gameEnded = false;
        // keep the game going or restart when a tie
        while(!gameEnded){
        //Draw board
            drawBoard(board);
            // this calls the lower function drawBoard using the above board function

            //Tracking what symbol the player is using
            char symbol = ' ';
            if (isPlayer1) {
                symbol = 'x';
            } else {
                symbol = 'o';
            }

            //print the player's turn
            if (isPlayer1) {
                System.out.println(P1 + "'s Turn(x):"); // taking the name input and outputting it
            } else {
                System.out.println(P2 + "'s Turn(o):");
            }

            // column and row variables so could set them out of while loop
            int col = 0;
            int row = 0;

            while (true) {
                //Get which column and row from the user
                System.out.print("Enter a row (0,1, or 2): ");
                row = in.nextInt();
                System.out.print("Enter a col (0,1, or 2): ");
                col = in.nextInt();

                //Check if the column and row are valid
                if (row < 0 || col < 0 || row > 2 || col > 2) {
                    System.out.println("Your inputs are invalid please choose between 0, 1 or 2");
                } else if (board[row][col] != '-') {
                    System.out.println("A move has already been made here!!");
                } else {
                    // the choosen row/col are valid
                    break; // ending the while loop once the input is valid
                }
            }
            board[row][col] = symbol;
            // check if someone won
            if (hasWon(board) == 'x'){
                // Player 1 won!!!
                System.out.println(P1 + " has won !!!");
                gameEnded=true;
            } else if (hasWon(board) == '0'){
                // Player 2 won!!!
                System.out.println(P2 + " has won !!!");
                gameEnded=true;
            }else {
             //Nobody has won or tied
                if(hasTied(board)){
                    //Tied
                    System.out.println("it's a tie!!!");
                    gameEnded=true;
                }
                    // CONTINUING THE GAME
                isPlayer1= !isPlayer1;
            }
        }
        // Print out the ending state of board
        drawBoard(board);

    }

    // void since building with no return
    public static void drawBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static char hasWon(char[][] board) {
        //Check if any winning row
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]
                    && board[i][0] != '-') {
                return board[i][0];
            }
        }
        // cclumns checking
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]
                    && board[0][j] != '-') {
                return board[0][j];
            }
        }
        //diagonal checking
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]
                && board[0][0] != '-') {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2]
                && board[2][0] != '-') {
            return board[2][0];}
        // Nobody won return value -
       return '-';
    }
    // Checking if board is full
    public static boolean hasTied(char[][] board) {
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                if (board[i][j]=='-'){
                    return false;
                }
            }
        }
        return true;
    }
}