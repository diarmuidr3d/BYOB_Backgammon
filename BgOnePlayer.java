/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

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
	

}

