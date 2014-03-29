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


    public HumanPlayer() {

    }

    /**
     * Sets the player colour
     *
     * @param c is the colour to be set
     */
    public void setPlayerColour(char c) {
        playerColour = c;
    }

    /**
     * It reads one, two, three or four moves from the input and returns them in
     * an array. The function tolerates some mistakes.
     *
     * @param b it is the actual board
     * @return It returns a int[][] moves where moves[i][0] is the source point
     * and moves[i][1] is the destination point. Those parameters are computed
     * to be the input of board.makeMove()
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
            if ((inputMoves.equals("p")) || (inputMoves.equals("P"))) {
                System.out.println("Turn Passed");
                validMoveFound = true;
            } else if ((inputMoves.equals("q")) || (inputMoves.equals("Q"))) {
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
            }
        }

        return movesArray;
    }

    public void computeMoves(int[][] movesArray, Board b) {

        int i;

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

    }

    /**
     * This function facilitates the player's move.
     * <p>
     * This function calls readMoves() to get the player's moves. It gets the
     * dice roll and if it's a double it provides 4 moves, 2 moves if not. It
     * provides moves for the different players ('B' and 'W')
     * </p>
     *
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
                System.out.println("It's White's turn.");
                break;
            case 'B':
                System.out.println("It's Black's turn.");
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
           
            if (moves != null) {
                boolean valid = b.isValidMove(moves, d, b.getTurn());
                if (!valid) {
                    System.out.println("One or more of your moves are not valid...Retype them!");
                } else if (moves.length <= movesCounter && valid) {
                    
                    for(int[] move:moves)
                        d.isMatchFor(move[1], b);
                    
                    computeMoves(moves, b);

                    for (int[] move : moves) {
                        b.makeMove(move[0], move[1]);
                        b.printBoard();
                        System.out.println("Moving from " + (1+move[0]) + " to " +(1+move[1]));
                        System.out.println();
                        movesCounter--;
                    }
                }
            }
        }
        
        retVal = 1;

        if ( playerColour == 'B') {
            b.setTurn('W');
        } else {
            b.setTurn('B');
        }
        return retVal;
    }
}
