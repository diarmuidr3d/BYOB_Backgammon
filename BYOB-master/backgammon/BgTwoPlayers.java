/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.io.IOException;

/**
 *
 * @author BYOB
 */
public class BgTwoPlayers {

    Board board;
    HumanPlayer blackPlayer, whitePlayer;
    int playCount;

    public BgTwoPlayers(int test) {
        board = new Board();
        if (test==0)
            board.setBoard();
        else board.setBoardTestBO();
        blackPlayer = new HumanPlayer();
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
        
        firstPlay(d);
        
        while (playCount == 0) {
            board.printBoard();
            if (board.getTurn() == 'W') {
                if (whitePlayer.playerMove(board, d) > 0 ) {
                    playCount++;
                }
            } else {

                if (blackPlayer.playerMove(board, d) > 0) {
                    playCount++;
                }
            }
        }
        
        while (!finishedGame) {
            switch (board.getTurn()) {
                case ('W'):
                    endTurn = false;
                    d.rollDice();
                    do {
                        board.printBoard();
                        if (whitePlayer.playerMove(board, d) > 0) {
                            endTurn = true;
                            playCount++;
                        }
                        finishedGame = (board.whiteOff == 15);
                        winner = 'W';

                    } while (!endTurn);

                    break;

                case ('B'):
                    endTurn = false;
                    d.rollDice();
                    do {
                        board.printBoard();
                        if (blackPlayer.playerMove(board, d) > 0) {
                            endTurn = true;
                            playCount++;
                        }
                        finishedGame = (board.blackOff == 15);
                        winner = 'B';
                    } while (!endTurn);
                    break;

                default:
                    break;
            }

        }
        return winner;
    }
}