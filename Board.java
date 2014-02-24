/*
 /*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */

import java.util.Random;

/**
 *
 * @author BYOB
 */
public class Board {

    Point[] boardPins = new Point[24]; // 0-23 pins are the actual pins. Setting refers to: http://en.wikipedia.org/wiki/File:Bg-movement.svg
    /* Counters for the bar and the off */
    int whiteBar;
    int blackBar;
    int whiteOff;
    int blackOff;
    char playerTurn = 'W';
    /**
     * This constant represents the White Off.
     */
    public static final int WHITE_OFF = 24;
    /**
     * This constant represents the Black Off.
     */
    public static final int BLACK_OFF = 25;
    /**
     * This constant represents the White Bar.
     */
    public static final int WHITE_BAR = 26;
    /**
     * This constant represents the Black Bar.
     */
    public static final int BLACK_BAR = 27;

    /**
     * Sets an empty board.
     */
    public Board() {
        for (int j = 0; j < 24; j++) {
            boardPins[j] = new Point();
        }
    }

    public char getTurn() {
        return playerTurn;
    }

    public void setTurn(char playerChar) {
        playerTurn = playerChar;
    }

    /**
     * Sets the board to start a game.
     * <p>
     * White and black checkers are put in their initial position by this method
     * <p>
     */
    public void setBoard() {
        for (int j = 0; j < 24; j++) {
            boardPins[j].setPin(' ', 0);
        }
        boardPins[0].setPin('W', 2);
        boardPins[23].setPin('B', 2);
        boardPins[5].setPin('B', 5);
        boardPins[18].setPin('W', 5);
        boardPins[7].setPin('B', 3);
        boardPins[16].setPin('W', 3);
        boardPins[11].setPin('W', 5);
        boardPins[12].setPin('B', 5);

        whiteBar = 0;
        blackBar = 0;
        whiteOff = 0;
        blackOff = 0;
    }

    private String printTopOfBoard() {
        String topofboard = "";
        for (int i = 12; i <= 17; i++) {
            if (boardPins[i].countCheckers() > 0) {
                topofboard = topofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
            } else {
                topofboard = topofboard + "|   ";
            }
        }
        if (blackBar == 0) {
            topofboard = topofboard + "    ";
        } else {
            topofboard = topofboard + blackBar + "B  ";
        }
        for (int i = 18; i <= 23; i++) {
            if (boardPins[i].countCheckers() > 0) {
                topofboard = topofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
            } else {
                topofboard = topofboard + "|   ";
            }
        }
        if (whiteOff == 0) {
            topofboard = topofboard + "   ";
        } else {
            topofboard = topofboard + whiteOff + "W ";
        }
        return topofboard;
    }

    private String printBottomOfBoard() {
        String bottomofboard = "";
        for (int i = 11; i >= 6; i--) {
            if (boardPins[i].countCheckers() > 0) {
                bottomofboard = bottomofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
            } else {
                bottomofboard = bottomofboard + "|   ";
            }
        }
        if (whiteBar == 0) {
            bottomofboard = bottomofboard + "    ";
        } else {
            bottomofboard = bottomofboard + whiteBar + "W  ";
        }
        for (int i = 5; i >= 0; i--) {
            if (boardPins[i].countCheckers() > 0) {
                bottomofboard = bottomofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
            } else {
                bottomofboard = bottomofboard + "|   ";
            }
        }
        if (blackOff == 0) {
            bottomofboard = bottomofboard + "    ";
        } else {
            bottomofboard = bottomofboard + blackOff + "B ";
        }
        return bottomofboard;
    }

    /**
     * Prints the actual configuation of the board. It uses printTopOfBoard()
     * and printBottomOfBoard() to create the strings for the top and bottom of
     * the board respectively.
     */
    public void printBoard() {
        System.out.println("13--+---+---+---+---18  BAR 19--+---+---+---+---24  OFF");
        System.out.println(printTopOfBoard());
        System.out.println("\n");
        System.out.println(printBottomOfBoard());
        System.out.println("12--+---+---+---+---07  BAR 06--+---+---+---+---01  OFF");
        System.out.println("\n\n");
    }

    /**
     * Rolls the dice. (1)
     * <p>
     * The method generates two random numbers when called.
     * <p>
     * @return Returns an int array a[2] containing the two random numbers.
     */
    public int[] rollDice() {
        int[] a = new int[2];
        Random randomGenerator = new Random();
        a[0] = randomGenerator.nextInt(6) + 1;
        a[1] = randomGenerator.nextInt(6) + 1;
        return a;
    }

    /**
     * Makes the specified move from the black bar to a destination.
     * <p>
     * @param destination It specifies the number of the destination in
     * boardPins[]
     */
    private int moveFromBlackBar(int destination) {
        int retVal = 0;
        char b = 'B';
        char w = 'W';
        if ((boardPins[destination].countCheckers() < 2) && (boardPins[destination].getColour() == w)) {
            blackBar--;
            whiteBar++;
            boardPins[destination].setPin(b, (boardPins[destination].countCheckers()));
            retVal = 0;
        } else if ((boardPins[destination].getColour() == b) || (boardPins[destination].getColour() == ' ')) {
            blackBar--;
            boardPins[destination].setPin(b, (boardPins[destination].countCheckers() + 1));
            retVal = 0;
        } else if ((boardPins[destination].countCheckers() >= 2) && (boardPins[destination].getColour() == w)) {
            System.out.println("invalid Move");
            retVal = -1;
        }

        return retVal;
    }

    /**
     * Makes the specified move from the white bar to a destination.
     * <p>
     * @param destination It specifies the number of the destination in
     * boardPins[]
     */
    private int moveFromWhiteBar(int destination) {
        int retVal = 0;
        char b = 'B';
        char w = 'W';
        if ((boardPins[destination].countCheckers() < 2) && (boardPins[destination].getColour() == b)) {
            whiteBar--;
            blackBar++;
            boardPins[destination].setPin(w, (boardPins[destination].countCheckers()));
            retVal = 0;
        } else if ((boardPins[destination].getColour() == w) || (boardPins[destination].getColour() == ' ')) {
            whiteBar--;
            boardPins[destination].setPin(w, (boardPins[destination].countCheckers() + 1));
            retVal = 0;
        } else if ((boardPins[destination].countCheckers() >= 2) && (boardPins[destination].getColour() == b)) {
            System.out.println("invalid Move");
            retVal = -1;
        }
        return retVal;
    }

    /**
     * Makes the specified move from a source to a destination.
     * <p>
     * @param source It specifies the number of the source point in boardPins[]
     * @param destination It specifies the number of the destination in
     * boardPins[]
     * @return It returns 0 if everything went well, -1 otherwise.
     */
    public int makeMove(int source, int destination) {

        int retVal = 0;
        //from black bar
        if (source == BLACK_BAR) {
            retVal = moveFromBlackBar(destination);

        } //from white bar
        else if (source == WHITE_BAR) {
            retVal = moveFromWhiteBar(destination);
        } //empty source!
        else if (boardPins[source].countCheckers() == 0) {
            System.out.println("Nothing on the source pin");
            retVal = -1;
        } //to white off
        else if (destination == WHITE_OFF) {
            whiteOff++;
            boardPins[source].setPin('W', (boardPins[source].countCheckers() - 1));
            retVal = 0;
        } //to black off
        else if (destination == BLACK_OFF) {
            blackOff++;
            boardPins[source].setPin('B', (boardPins[source].countCheckers() - 1));
            retVal = 0;
        } //same colour move or empty destination
        else if ((boardPins[source].getColour() == boardPins[destination].getColour()) || (boardPins[destination].isEmpty())) {
            boardPins[destination].setPin(boardPins[source].getColour(), (boardPins[destination].countCheckers() + 1));
            boardPins[source].setPin(boardPins[source].getColour(), (boardPins[source].countCheckers() - 1));
            retVal = 0;
        } //eating move
        else if (boardPins[destination].countCheckers() == 1) {
            if (boardPins[destination].getColour() == 'W') {
                whiteBar++;
            } else if (boardPins[destination].getColour() == 'B') {
                blackBar++;
            }
            boardPins[destination].setPin(boardPins[source].getColour(), 1);
            boardPins[source].setPin(boardPins[source].getColour(), (boardPins[source].countCheckers() - 1));
            retVal = 0;
        }
        else{
        	System.out.println("Invalid Move");
        	retVal = -1;
        }
        return retVal;
    }
    
    public char getColour(int position){
        return boardPins[position].getColour();
    }


    public int[] firstPlay() {
        int[] diceRoll = rollDice();
        boolean endFirstPlay = false;

        while(!endFirstPlay){
            if(diceRoll[0] > diceRoll[1]){
                this.setTurn('W');
                endFirstPlay = true;
            } 
            else if(diceRoll[0] < diceRoll[1]){
                this.setTurn('B');
                endFirstPlay = true;
            }
            else{
            	diceRoll = rollDice();
            }
        }
        return diceRoll;
    }

    public boolean whiteCheckForBearOff() {
        int count = 0;
        for (int j = 18; j <= 23; j++) {
            if (boardPins[j].getColour() == 'W') {
                count = count + boardPins[j].countCheckers();
            }
        }
        count = count + whiteOff;
        return count == 15;
    }

    public boolean blackCheckForBearOff() {
        int count = 0;
        for (int j = 0; j <= 5; j++) {
            if (boardPins[j].getColour() == 'B') {
                count = count + boardPins[j].countCheckers();
            }
        }
        count = count + blackOff;
        return count == 15;
    }
    
    public boolean checkBearOff(char player){
        if (player == 'W') return whiteCheckForBearOff();
        else return blackCheckForBearOff();
    }
    
    public boolean isADoubleRoll(int[] diceRoll){
        return diceRoll[0] == diceRoll[1];
    }
}
