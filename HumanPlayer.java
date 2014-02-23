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

    public int[][] readMoves(Board b) throws FileNotFoundException {

        Scanner input;
        int[][] movesArray = null;
        int i, j;
        boolean validMoveFound = false;

        String inputMoves;

        input = new Scanner(System.in);
        
        while (!validMoveFound && input.hasNext()) {
            inputMoves = input.nextLine();
            inputMoves = inputMoves.trim();

            if (!inputMoves.matches("(([1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})\\s+){0,3}[1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1}))")) {
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
                    if(b.getTurn() == 'W')
                        movesArray[i][j + 1] = movesArray[i][j] + movesArray[i][j + 1];
                    else if (b.getTurn() == 'B')
                        movesArray[i][j + 1] = movesArray[i][j] - movesArray[i][j+1];
                }
            }
        }

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
    
    public int playerMove(Board b, int[] diceRoll) throws FileNotFoundException, IOException {
        int retVal = 0;
        int movesCounter;

        switch (b.getTurn()) {
            case 'W':
                if (b.isADoubleRoll(diceRoll)) {
                    movesCounter = 4;

                    while (movesCounter != 0) {
                        System.out.println("Double " + diceRoll[1] + "! Input your " + movesCounter + " moves in the format startPoint-steps: ");

                        int[][] moves = readMoves(b);

                        if (moves != null) {
                            for (int[] move : moves) {
                                if (b.makeMove(move[0], move[1]) != -1) {
                                    movesCounter--;
                                }
                            }
                        }
                    }
                } else {

                    movesCounter = 2;

                    while (movesCounter != 0) {

                        System.out.println("You got " + diceRoll[0] + "," + diceRoll[1] + "! Input your " + movesCounter + " moves in the format startPoint-steps: ");

                        int[][] moves = readMoves(b);
                        if (moves != null) {
                            for (int[] move : moves) {
                               if( b.makeMove(move[0], move[1])!= -1 )
                                movesCounter--;
                            }
                        }

                    }

                }
                b.setTurn('B');
                break;
            case 'B':
                if (b.isADoubleRoll(diceRoll)) {
                    movesCounter = 4;

                    while (movesCounter != 0) {
                        System.out.println("Double " + diceRoll[1] + "! Input your " + movesCounter + " moves in the format startPoint-steps: ");

                        int[][] moves = readMoves(b);

                        if (moves != null) {
                            for (int[] move : moves) {
                                if (b.makeMove(move[0], move[1]) != -1) {
                                    movesCounter--;
                                }
                            }
                        }
                    }
                } else {

                    movesCounter = 2;

                    while (movesCounter != 0) {

                        System.out.println("You got " + diceRoll[0] + "," + diceRoll[1] + "! Input your " + movesCounter + " moves in the format startPoint-steps: ");

                        int[][] moves = readMoves(b);
                        if (moves != null) {
                            for (int[] move : moves) {
                                
                                if ( b.makeMove(move[0], move[1])!=-1)
                                movesCounter--;
                            }
                        }

                    }

                }
                b.setTurn('W');
                break;

        }

        return retVal;
    }

    public boolean quitQuestion() {
        return true;
    }
}
