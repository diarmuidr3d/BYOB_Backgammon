/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */

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
        
        while (!validMoveFound && input.hasNextLine() ) {
            inputMoves = input.nextLine();
            inputMoves = inputMoves.trim();
                                    
            if (!inputMoves.matches("([1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})\\s+[1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1}))") &&
                !inputMoves.matches("([1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})\\s+){3}[1-9]([0-9]{0,1})(\\s)*-(\\s)*[1-9]([0-9]{0,1})")) {
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
        if (movesArray != null){
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

    
    public int playerMove(Board b) throws FileNotFoundException{
        int retVal = -1;
        int[] dice;
        int[][] move = null;
        boolean moveComplete = false;
        while ((b.blackOff < 15) && (b.whiteOff < 15) && quitQuestion()) {
            dice = b.rollDice();
            while (!moveComplete) {
                b.printBoard();
                if(dice[0] == dice[1]) {
                    System.out.println("Doubles rolled. 4 moves of "+dice[0]+" available.\nPlease make your moves in the format Source-Destination Source-Destination.");
                    move = readMoves();
                    for (int moves[] : move){
                        System.out.println(move[0] + " " + move[1]);
                    }
                    for (int i = 0; i < 4; i++) {
                        if (b.makeMove(move[i][0], move[i][1]) == 0) {
                            moveComplete = true;
                        }
                    }
                } else {
                    System.out.println("2 moves, "+dice[0]+" and "+dice[1]+" available.\nPlease make your moves in the format Source-Destination Source-Destination.");
                    move = readMoves();
                    for (int moves[] : move){
                        System.out.println(move[0] + " " + move[1]);
                    }
                    for (int i = 0; i < 2 ; i++) {
                        if (b.makeMove(move[i][0], move[i][1]) == 0) {
                            moveComplete = true;
                        }
                    }
                }
            }
        }
        return retVal;
    }
    
    public boolean quitQuestion() {
        return true;
    }
 }
