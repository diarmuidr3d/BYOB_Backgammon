/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.io.IOException;
import java.util.Scanner;

public class BgOnePlayer{
	
	private Dice d;
    private Board board;
    private HumanPlayer whitePlayer;
    private RandomPlayer blackPlayer;
    int playCount;
	
	private BgOnePlayer(){
	    board = new Board();
	    board.setBoard();
	    d = new Dice();
	    blackPlayer = new RandomPlayer();
	    blackPlayer.setPlayerColour('B');
	    whitePlayer = new HumanPlayer();
	    whitePlayer.setPlayerColour('W');
	    playCount = 0;
	}
	
    /**
     * Decides which player goes first
     * <p>
     * This method decides which player goes first at the start of the game. It
     * rolls the dice for each player and set's the next turn for whichever
     * player receives the higher score.
     * <p>
     * @param d is the dice
     */
    private void firstPlay() {
        d.rollDice();
        boolean endFirstPlay = false;

        while (!endFirstPlay) {
            if (d.getFirstDice() > d.getSecondDice()) {
                board.setTurn('W');
                System.out.println("W: " + d.getFirstDice() + " B: " + d.getSecondDice() +" White starts!\n");
                endFirstPlay = true;
            } else if (d.getFirstDice() < d.getSecondDice()) {
                board.setTurn('B');
                System.out.println("W: " + d.getFirstDice() + " B: " + d.getSecondDice() +" Black starts!\n");
                endFirstPlay = true;
            } else {
                System.out.println("W: " + d.getFirstDice() + " B: " + d.getSecondDice() +" Draw. Roll again!\n");
                d.rollDice();
            }
        }
    }
	
    /**
     * It starts a new game between two human players and manage it.
     * <p>
     * Manage the first turn and the following ones. Count the number of turns.
     * <p>
     * @return It the returns the winner of the game.
     * @throws java.io.IOException
     */
    private char game() throws IOException {
        boolean finishedGame = false;
        boolean endTurn;
        char winner = 'N';
        int entry1[][] = new int[2][2];
        int entry2[][] = new int[4][2];
        
        firstPlay();
        
        while (playCount == 0) {
            board.printBoard();
            if (board.getTurn() == 'W') {
                if (whitePlayer.playerMove(board, d) > 0 ) {
                    playCount++;
                }
            } else {

            	entry1 = blackPlayer.getPlay(d,board);
            	for(int i=0;i<2;i++){
            		board.makeMove(entry1[i][0], entry1[i][1]);
            	}
                playCount++;
            }
        }
        
        while (!finishedGame) {
        	d.rollDice();
            switch (board.getTurn()) {
            	case ('W'):
                    endTurn = false;
                    do {
                        board.printBoard();
                        if(whitePlayer.playerMove(board, d) > 0 ){
                            endTurn = true;
                            playCount++;
                            finishedGame = (board.whiteOff == 15);
                        }
                        
                        if (finishedGame){
                            winner = 'W';
                        }

                    } while (!endTurn);
                    board.setTurn('B');
                    break;

                case ('B'):
                    endTurn = false;
                    do {
                        board.printBoard();
                        if(d.isDoubleRoll()){
                			entry2 = blackPlayer.getPlay(d, board);
                			for(int i=0;i<4;i++){
                				board.makeMove(entry2[i][0], entry2[i][1]);
                				entry2[i][0]++;
                				entry2[i][1]++;
                				System.out.println("RandomPlayer moving from "+entry2[i][0]+" to "+entry2[i][1]);
                			}
                		}
                		else{
                			entry1 = blackPlayer.getPlay(d, board);
                			for(int i=0;i<2;i++){
                				if (entry1[i][1] == Board.WHITE_OFF) entry1[i][1] = Board.BLACK_OFF;
                				board.makeMove(entry1[i][0], entry1[i][1]);
                				System.out.println("RandomPlayer moving from "+entry1[i][0]+" to "+entry1[i][1]);
                			}
                		}
                        endTurn = true;
                        playCount++;
                        finishedGame = (board.blackOff == 15);
                        if (finishedGame){
                            winner = 'B';
                        }
                    } while (!endTurn);
                    //board.setTurn('W');
                    break;

                default:
                    break;
            }

        }
        board.printBoard();
        System.out.println("The winner is: " + winner + " with a " + board.getResult(winner));
        return winner;
    }
    
    public static void main(String[] args) throws IOException {
		BgOnePlayer newGame = new BgOnePlayer();
		boolean playAgain = true;
		Scanner input;
		String answer;
		while (playAgain) {
			newGame.game();
			System.out.println("Do you want to play again?");
			input = new Scanner(System.in);
			answer = input.nextLine(); 
			answer.trim();
			if (!answer.equals("y") || !answer.equals("Y") || !answer.equals("Yes") || !answer.equals("yes")|| !answer.equals("YES")) {
				playAgain = false;
			}
		}
	}

}
