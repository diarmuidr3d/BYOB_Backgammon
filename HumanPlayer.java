/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author BYOB
 */
public class HumanPlayer {

    public int[][] readMoves() throws FileNotFoundException {

        Scanner input;
        int[][] movesArray = null;
        int i, j;
        boolean validMoveFound = false;

        String inputMoves;

        input = new Scanner(System.in);

        while (!validMoveFound && input.hasNextLine()) {
            inputMoves = input.nextLine();
            inputMoves = inputMoves.trim();

            if (!inputMoves.matches("([1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})\\s+[1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1}))")
                    && !inputMoves.matches("([1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})\\s+){3}[1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})")) {
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
            for (i = 0; i < movesArray.length; i++) {
                for (j = 0; j < movesArray[i].length - 1; j++) {
                    movesArray[i][j]--;
                    movesArray[i][j + 1] = movesArray[i][j] + movesArray[i][j + 1];
                }
            }
        }

        input.close();

        return movesArray;
    }
    
    	public int quitGame(){
		String response = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to exit the game: (y/n)\n");
		do{
			response = input.nextLine();
			response = response.trim();
			response = response.toLowerCase();
			if((!response.equals("y")) && (!response.equals("n"))){
				System.out.println("You have entered an invalid character, Please try again\n");
			}
		}while((!response.equals("y")) && (!response.equals("n")));
		if(response.equals("y")){
			System.out.println("Thank you for playing\n");
			System.exit(0);
		}
		System.out.println("You have choosen to continue playing\n");
		input.close();
		return 0;
	}
    
    public int playerMove(Board b, char player, int[] diceRoll) throws FileNotFoundException {
        int retVal = 0;
        int[][] moves;
        boolean moveComplete = false;
        b.printBoard();
        if (b.isADoubleRoll(diceRoll)) {
            System.out.println("Doubles rolled. 4 moves of " + diceRoll[0] + " available.\nPlease make your moves in the format Source-Steps Source-Steps.");
        } else {
            System.out.println("2 moves, " + diceRoll[0] + " and " + diceRoll[1] + " available.\nPlease make your moves in the format Source-Steps Source-Steps.");
        }
        
        while (!moveComplete) {
            moves = readMoves();
            
            for (int move[] : moves) {
                if (b.boardPins[move[0]].getColour() == player ) {
                    if (((b.boardPins[move[0]].getColour() == 'W') && (move[0] < move[1])) || ((b.boardPins[move[0]].getColour() == 'B') && (move[0] > move[1]))) {
                        if (b.makeMove(move[0], move[1]) == 0) {
                            moveComplete = true;
                        } else {
                            retVal = -1;
                            System.out.println("Uh oh, something went wrong making the move! Please try again.");
                        }
                    } else {
                        retVal = -1;
                        System.out.println("Oops, moved in the wrong direction! Please try again."); 
                    }
                } else {
                    retVal = -1;
                    System.out.println("Oops, wrong pin, please pick your own colour! Please try again.");  
                }
            }
        }
        return retVal;
    }

    public boolean quitQuestion() {
        return true;
    }
}
