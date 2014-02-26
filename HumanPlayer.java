/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author BYOB
 */
public class HumanPlayer {

    /**
     * Allows the player to quit the game
     * <p>
     * This function checks if the player wishes to exit the game. If the player wishes to exit, it quits the game.
     * </p>
     */
    public void quitGame() {
        String response = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to exit the game: (y/n)");
        do {
            response = input.nextLine();
            response = response.trim();
            response = response.toLowerCase();
            if ((!response.equals("y")) && (!response.equals("n"))) {
                System.out.println("You have entered an invalid character, Please try again");
            }
        } while ((!response.equals("y")) && (!response.equals("n")));
        if (response.equals("y")) {
            System.out.println("Thank you for playing\n");
            System.exit(0);
        }
        System.out.println("You have choosen to continue playing\n");

    }
    
    /**
     * This function allows the player to pass their turn if they have no moves available
     * <p>
     * If there is no move available to the player they can pass their move. 
     * This function waits for a Y/N answer from the player on what they wish to do.
     * </p>
     * @param b is the board
     * @return Returns 1 if the player chooses to pass and 0 if the player chooses to continue.
     */
    public int passTurn(Board b) {
        String response = "";
        int retVal = 1;
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to pass your turn: (y/n)");
        do {
            response = input.nextLine();
            response = response.trim();
            response = response.toLowerCase();
            if ((!response.equals("y")) && (!response.equals("n"))) {
                System.out.println("You have entered an invalid character, Please try again");
            }
        } while ((!response.equals("y")) && (!response.equals("n")));
        if (response.equals("y")) {
            System.out.println("You have choosen to pass your turn\n");
            if (b.getTurn() == 'W') {
                b.setTurn('B');
            } else {
                b.setTurn('W');
            }
        } else {
            System.out.println("You have choosen to not pass your turn\n");
            retVal = 0;
        }
        return retVal;
    }
    /**
     * It reads one, two, three or four moves from the input and returns them in an
     * array. The function tolerates some mistakes.
     *
     * @param b it is the actual board
     * @return It returns a int[][] moves where moves[i][0] is the source point and
     * moves[i][1] is the destination point. Those parameters are computed to be the
     * input of board.makeMove()
     * @throws java.io.FileNotFoundException
    */
    public int[][] readMoves(Board b) throws FileNotFoundException {

        Scanner input;
        int[][] movesArray = null;
        int i, j;
        boolean validMoveFound = false;

        String inputMoves;

        input = new Scanner(System.in);

        while (!validMoveFound && input.hasNextLine()) {
            inputMoves = input.nextLine();
            inputMoves = inputMoves.trim();
            if( (inputMoves.equals("p")) || (inputMoves.equals("P")) ) {
                System.out.println("Turn Passed");
                validMoveFound = true;
            } else if ( (inputMoves.equals("q")) || (inputMoves.equals("Q")) ) {
                System.out.println("Thanks for playing");
                System.exit(0);
            } else if (!inputMoves.matches("(([1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})\\s+){0,3}[1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1}))")) {
                System.out.println("Invalid: " + inputMoves);
            } else {
                validMoveFound = true;
                inputMoves = inputMoves.replaceAll("\\s+", " ");
                inputMoves = inputMoves.replaceAll("\\s*-\\s*", "-");
                String[] splittedMoves = inputMoves.split(" ");
                movesArray = new int[splittedMoves.length][2];

                i = 0;

                for (String s : splittedMoves) {
                    String[] splittedValues = s.split("-");
                    j = 0;
                    for (String x : splittedValues) {
                        movesArray[i][j] = Integer.parseInt(x);
                        j++;
                    }
                    i++;
                }
            }
        }
        if (movesArray != null) {
            if (b.getTurn() == 'W') {
                for (i = 0; i < movesArray.length; i++) {
                    movesArray[i][0]--;
                    if (movesArray[i][0] == 24) {
                        movesArray[i][0] = Board.WHITE_BAR;
                        movesArray[i][1] = movesArray[i][1] - 1;
                    } else if (movesArray[i][0] + movesArray[i][1] > 24) {
                        movesArray[i][1] = Board.WHITE_OFF;
                    } else {
                        movesArray[i][1] = movesArray[i][0] + movesArray[i][1];
                    }
                }
            } else if (b.getTurn() == 'B') {
                for (i = 0; i < movesArray.length; i++) {
                    movesArray[i][0]--;
                    if (movesArray[i][0] == 24) {
                        movesArray[i][0] = Board.BLACK_BAR;
                        movesArray[i][1] = 24 - movesArray[i][1];
                    } else if (movesArray[i][0] - movesArray[i][1] < 0) {
                        movesArray[i][1] = Board.BLACK_OFF;
                    } else {
                        movesArray[i][1] = movesArray[i][0] - movesArray[i][1];
                    }
                }
            }

        }

        return movesArray;
    }

    /**
     * This function facilitates the player's move.
     * <p>
     * This function calls readMoves() to get the player's moves.
     * It gets the diceroll and if it's a double it provides 4 moves, 2 moves if not.
     * It provides moves for the different players ('B' and 'W')
     * </p>
     * @param b is the board
     * @param diceRoll is the rolled dice
     * @return Returns -1 if unsuccessful, 0 if successful
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int playerMove(Board b, int[] diceRoll) throws FileNotFoundException, IOException {
        int retVal = -1;
        int movesCounter;

        switch (b.getTurn()) {
            case 'W':
                System.out.println("It's White's turn");
                break;
            case 'B':
                System.out.println("It's Black's turn");
                break;
        }
        if (b.isADoubleRoll(diceRoll)) {
            movesCounter = 4;
            System.out.println("Double " + diceRoll[1] + "! Input your " + movesCounter + " moves in the format startPoint-steps or q to quit or p to pass: ");
            
        } else {

            movesCounter = 2;
            System.out.println("You got " + diceRoll[0] + "," + diceRoll[1] + "! Input your " + movesCounter + " moves in the format startPoint-steps or q to quit or p to pass: ");  
        }
        while (movesCounter > 0) {
           /* this.quitGame();
            if (this.passTurn(b) == 1) {
                break;
            }*/
            int[][] moves = readMoves(b);
            if (moves == null) {
                movesCounter = 0;
            }
            if ((moves != null) && (moves.length <= movesCounter)) {
                for (int[] move : moves) {
                    if (b.getColour(move[0]) == b.getTurn()) {
                        int tmp1 = move[0] + 1;
                        int tmp2 = move[1] + 1;
                        System.out.println("Move Performed " + tmp1 + " -> " + tmp2);
                        retVal = 0;
                        if (b.makeMove(move[0], move[1]) != -1) {
                            movesCounter--;
                        }
                    }
                    else {
                        System.out.println("Oops, wrong colour!");
                    }
                }
            }
        }
        if (b.getTurn() == 'B') {
            b.setTurn('W');
        } else {
            b.setTurn('B');
        }
        return retVal;
    }
}
