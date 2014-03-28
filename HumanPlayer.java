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
    
    private char playerColour;

    public HumanPlayer(){
        
    }
    
    /**
     * Sets the player colour
     * @param c  is the colour to be set
     */
    public void setPlayerColour(char c){
        playerColour = c;
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
        int count = 0;
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
            } else if (!inputMoves.matches("(([1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-6]\\s+){0,3}[1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-6])")) {
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
                
                count = 0;
                for(i=0;i< movesArray.length; i++){
                	if(movesArray[i][0]>25){
                		count++;
                    	validMoveFound = false;
                    	if(count==1){
                    		System.out.println("Invalid: " + inputMoves);
                    	}
                    }
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
     * It gets the dice roll and if it's a double it provides 4 moves, 2 moves if not.
     * It provides moves for the different players ('B' and 'W')
     * </p>
     * @param b is the board
     * @param d is the dice
     * @return Returns -1 if unsuccessful, 0 if successful
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int playerMove(Board b, Dice d) throws FileNotFoundException, IOException {
        int retVal = -1;
        int movesCounter;
        int dieUsed = -1;
        int errCode;
        switch (b.getTurn()) {
            case 'W':
                System.out.println("It's White's turn");
                break;
            case 'B':
                System.out.println("It's Black's turn");
                break;
        }
        if (d.isDoubleRoll()) {
            movesCounter = 4;
            System.out.println("Double " + d.getFirstDice() + "! Input your " + movesCounter + " moves in the format startPoint-steps or q to quit or p to pass: ");
        } else {
            movesCounter = 2;
            System.out.println("You got " + d.getFirstDice() + "," + d.getSecondDice() + "! Input your " + movesCounter + " moves in the format startPoint-steps or q to quit or p to pass: ");  
        }
        while (movesCounter > 0) {
            int[][] moves = readMoves(b);
            if (moves == null) {
                movesCounter = 0;
            }
            if ((moves != null) && (moves.length <= movesCounter)) {
                for (int[] move : moves) {
                    if (b.getColour(move[0]) == b.getTurn()) {
                        if ((dieUsed = d.isMatchFor(move, b)) > 0) {
                            if ((b.getBar(b.getTurn()) == 0) || (b.getBar(b.getTurn()) > 0 && (move[0] == Board.WHITE_BAR || move[0] == Board.BLACK_BAR))) {
                                if ((errCode = b.makeMove(move[0], move[1])) >= 0) {
                                    int tmp1 = move[0] + 1;
                                    int tmp2 = move[1] + 1;
                                    retVal = 0;
                                    System.out.println("Move Performed " + tmp1 + " -> " + tmp2);
                                    movesCounter--;
                                } else {
                                    System.out.println("Move unsuccessful");
                                    d.resetDieCheck(dieUsed);
                                    retVal = errCode;
                                }
                            } else {
                                System.out.println("Empty the bar first!");
                                d.resetDieCheck(dieUsed);
                                retVal = Board.EMPTY_BAR;
                            }
                        } else {
                            System.out.println("Oops, wrong dice!");
                            d.resetDieCheck(dieUsed);
                            retVal = dieUsed;
                        }
                    } 
                    else if((b.whiteBar==0)&&(b.getTurn()=='W')&&(move[0]==25)){
                    	System.out.println("Nothing on the source pin");
                        retVal = Board.EMPTY_POINT;
                    }
                    else if((b.blackBar==0)&&(b.getTurn()=='B')&&(move[0]==25)){
                    	System.out.println("Nothing on the source pin");
                        retVal = Board.EMPTY_POINT;
                    }
                    else if(b.getColour(move[0])==' '){
                    	System.out.println("Nothing on the source pin");
                        retVal = Board.EMPTY_POINT;
                    }
                    else {
                        System.out.println("Oops, wrong colour!");
                        retVal = Board.INVALID_POINT;
                    }
                }
            } else {
                System.out.println("No moves or too many moves!");
                retVal = Board.WRONG_DIE;
                d.resetDieCheck(dieUsed);
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
