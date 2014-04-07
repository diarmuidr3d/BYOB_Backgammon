/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.io.IOException;

public class BgOnePlayer{
	
    Board board;
    HumanPlayer whitePlayer;
    RandomPlayer blackPlayer;
    int playCount;
	
	public BgOnePlayer(){
	    board = new Board();
	    board.setBoard();
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
    public void firstPlay(Dice d) {
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
    public char game() throws IOException {
        Dice d = new Dice();
        boolean finishedGame = false;
        boolean endTurn;
        char winner = 'N';
        int entry1[][] = new int[2][2];
        int entry2[][] = new int[4][2];
        
        firstPlay(d);
        
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
            switch (board.getTurn()) {
                case ('W'):
                    endTurn = false;
                    d.rollDice();
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

                    break;

                case ('B'):
                    endTurn = false;
                    d.  rollDice();
                    do {
                        board.printBoard();
                        if(d.isDoubleRoll()){
                			entry2 = blackPlayer.getPlay(d, board);
                			for(int i=0;i<4;i++){
                				board.makeMove(entry2[i][0], entry2[i][1]);
                			}
                		}
                		else{
                			entry1 = blackPlayer.getPlay(d, board);
                			for(int i=0;i<2;i++){
                				board.makeMove(entry1[i][0], entry1[i][1]);
                			}
                		}
                        endTurn = true;
                        playCount++;
                        finishedGame = (board.blackOff == 15);
                       
                        if (finishedGame){
                            winner = 'B';
                        }
                    } while (!endTurn);
                    
                    break;

                default:
                    break;
            }

        }
        System.out.println("The winner is: " + winner + " with a " + board.getResult(winner));
        return winner;
    }

}
