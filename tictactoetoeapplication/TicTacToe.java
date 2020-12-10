// Picture of Game with Index:
// FOR STORAGE:
//  0 | 1 | 2
// --------------------
//  3 | 4 | 5
// --------------------
//  6 | 7 | 8
// WHAT THE USER THINKS:
//  1 | 2 | 3
// --------------------
//  4 | 5 | 6
// --------------------
//  7 | 8 | 9


// UI Picture of Game:
// INIT:
// | - | - | -
// ------------
// | - | - | -
// ------------
// | - | - | -
// GAMEPLAY:
// | O | - | O
// ------------
// | - | X | -
// ------------
// | - | - | X
package tictactoetoeapplication;

public class TicTacToe {

    protected char[] board;
    protected char userMaker;
    protected char aiMarker;
    protected char winner;
    protected char currentMaker;

    //Constructor
    public TicTacToe(char playerToken, char aiMarker) {
        this.userMaker = playerToken;
        this.aiMarker = aiMarker;
        this.winner = '-';
        this.board = setBoard();
        this.currentMaker = userMaker;
    }

    public static char[] setBoard() {
        // the following means that the array shall have 9 char in it.
        char[] board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
        return board;
    }

    //This say if its your chance or the AI has a chance now
    public boolean playTurn(int spot) {
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);
        if (isValid) {
            //spot -1 is present because we are giving the using 1-9 as a option but in reality the array and stuff are going to have 0-8;
            board[spot - 1] = currentMaker;
            /*This is just an if-else statement but with the help of ternary operator
            Here the first statement comes out if the condition is true and the if the condition is false the second statement comes out.
             */
            currentMaker = (currentMaker == userMaker) ? aiMarker : userMaker;
        }
        return isValid;
    }

    // Check if our spot is in range
    public boolean withinRange(int number) {
        //This statement just says if number is between 0 and 10; then the number is valid.
        return number > 0 && number < board.length + 1;
    }

    //Check if the spot is taken
    public boolean isSpotTaken(int number) {
        //Here java would check if the number put by the user has some data or not.
        // number - 1 is there because the if the give spot name is 3 the real number is 2
        // if the board doesn't consists any data on that in particular number then the board shall consists '-';
        return board[number - 1] != '-';
    }

    //This will print the basic board of the game
    //This shall look like
    /*
    | - | - | -
    ------------
    | - | - | -
    ------------
    | - | - | -
     */
    //This shall print the '-' basic to start upon
    public void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }

// this shall print the numbers in the their places.
    public static void printIndexBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {        //original: (int i = 0; i < board.length; i++)
            if (i % 3 == 0 && i != 0) {
                System.out.println();
                System.out.println("------------");
            }
            System.out.print(" | " + (i + 1));
        }
        System.out.println();
    }

    //This shall check if there is any winner on the board...
    public boolean isThereAWinner() {
        //this Di mean diagonals
        boolean diagonalsAndMiddles = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';

        if (diagonalsAndMiddles) {
            this.winner = board[4];
        } else if (topAndFirst) {
            this.winner = board[0];
        } else if (bottomAndThird) {
            this.winner = board[8];
        }
        return diagonalsAndMiddles || topAndFirst || bottomAndThird;
    }

    public boolean topRow() {
        return board[0] == board[1] && board[1] == board[2];
    }

    public boolean middleRow() {
        return board[3] == board[4] && board[4] == board[5];
    }

    public boolean bottomRow() {
        return board[6] == board[7] && board[7] == board[8];
    }

    public boolean firstCol() {
        return board[0] == board[3] && board[3] == board[6];
    }

    public boolean secondCol() {
        return board[1] == board[4] && board[4] == board[7];
    }

    public boolean thirdCol() {
        return board[2] == board[5] && board[5] == board[8];
    }

    public boolean rightDi() {
        return board[0] == board[4] && board[4] == board[8];
    }

    public boolean leftDi() {
        return board[2] == board[4] && board[4] == board[6];
    }
    public boolean isTheBoardFilled(){
        for (int i = 0; i < board.length; i++){
            if(board[i] == '-'){
                return false;
            }
        }
        return true;
    }
    public String gameOver() {
        boolean didSomeoneWin = isThereAWinner();
        if (didSomeoneWin) {
            return "We have a winner! The winner is " + this.winner + "'s";
        } else if (isTheBoardFilled()) {
            return "Draw: Game Over!";
        } else {
            return "notOver";
        }

    }

}
