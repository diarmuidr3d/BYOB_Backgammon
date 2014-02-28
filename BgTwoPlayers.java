/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.io.IOException;

/**
 *
 * @author Stefano
 */
public class BgTwoPlayers {

    Board board;
    HumanPlayer blackPlayer, whitePlayer;
    int playCount = 0;

    public BgTwoPlayers() {
        board = new Board();
        board.setBoard();
        blackPlayer = new HumanPlayer();
        blackPlayer.setPlayerColour('B');
        whitePlayer = new HumanPlayer();
        whitePlayer.setPlayerColour('W');
    }

    /**
     * Decides which player goes first
     * <p>
     * This method decides which player goes first at the start of the game. It
     * rolls the dice for each player and set's the next turn for whichever
     * player receives the higher score.
     * <p>
     * @return Returns the rolled dice
     */
    public int[] firstPlay() {
        int[] diceRoll = board.rollDice();
        boolean endFirstPlay = false;

        while (!endFirstPlay) {
            if (diceRoll[0] > diceRoll[1]) {
                board.setTurn('W');
                endFirstPlay = true;
            } else if (diceRoll[0] < diceRoll[1]) {
                board.setTurn('B');
                endFirstPlay = true;
            } else {
                diceRoll = board.rollDice();
            }
        }
        return diceRoll;
    }

    public char game() throws IOException {
        int[] diceRoll;
        boolean finishedGame = false;
        boolean endTurn;
        char winner = 'N';

        while (playCount == 0) {
            diceRoll = firstPlay();
            board.printBoard();
            if (board.getTurn() == 'W') {
                if (whitePlayer.playerMove(board, diceRoll) != -1) {
                    playCount++;
                }
            } else {

                if (blackPlayer.playerMove(board, diceRoll) != -1) {
                    playCount++;
                }
            }
        }

        while (!finishedGame) {

            switch (board.getTurn()) {

                case ('W'):
                    endTurn = false;
                    diceRoll = board.rollDice();
                    do {
                        board.printBoard();
                        if (whitePlayer.playerMove(board, diceRoll) != -1) {
                            endTurn = true;
                            playCount++;
                        }
                        finishedGame = (board.whiteOff == 15);
                        winner = 'W';

                    } while (!endTurn);

                    break;

                case ('B'):
                    endTurn = false;
                    diceRoll = board.rollDice();
                    do {
                        board.printBoard();
                        if (blackPlayer.playerMove(board, diceRoll) != -1) {
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
