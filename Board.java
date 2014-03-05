/*
 /*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;
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
    /**
     * Gets which player's turn it is.
     * @return Returns a capital character B (Black) or W (White) depending which player's turn it is
     */
    public char getTurn() {
        return playerTurn;
    }
    /**
     * Set's which player's turn it is
     * <p>
     * The method takes in a character 'B' (black) or 'W' (white) depending on which player's turn it is and assigns the current player turn to tat player.
     * <p>
     * @param playerChar
     */
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
     * Prints the actual configuration of the board. It uses printTopOfBoard()
     * and printBottomOfBoard() to create the strings for the top and bottom of
     * the board respectively.
     */
     public void printBoard() {
    	    if(this.getTurn()=='W'){
       		    System.out.println("13--+---+---+---+---18  BAR 19--+---+---+---+---24  OFF");
       	     System.out.println(printTopOfBoard());
       	     System.out.println("\n");
       	     System.out.println(printBottomOfBoard());
       	     System.out.println("12--+---+---+---+---07  BAR 06--+---+---+---+---01  OFF");
       	     System.out.println("\n\n");
    	    }
    	    else{
           		System.out.println("12--+---+---+---+---07  BAR 06--+---+---+---+---01  OFF");
           		System.out.println(printBottomOfBoard());
           		System.out.println("\n");
           		System.out.println(printTopOfBoard());
           		System.out.println("13--+---+---+---+---18  BAR 19--+---+---+---+---24  OFF");
           		System.out.println("\n\n");
        }
    }
    /**
     * Rolls the dice. (1)
     * <p>
     * The method generates two random numbers when called.
     * <p>
     * @return Returns an int array a[2] containing the two random numbers.
     */
 /*   public int[] rollDice() {
        int[] a = new int[2];
        Random randomGenerator = new Random();
        a[0] = randomGenerator.nextInt(6) + 1;
        a[1] = randomGenerator.nextInt(6) + 1;
        return a;
    }*/

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
        int retVal = -1;
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
            if (checkBearOff('W')) {
                whiteOff++;
                boardPins[source].setPin('W', (boardPins[source].countCheckers() - 1));
                retVal = 0;
            } else {
                System.out.println("You cannot bear off yet!");
            }
        } //to black off
        else if (destination == BLACK_OFF) {
            if (checkBearOff('B')) {
                blackOff++;
                boardPins[source].setPin('B', (boardPins[source].countCheckers() - 1));
                retVal = 0;
            } else {
                System.out.println("You cannot bear off yet!");
            }
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
        if ((whiteOff == 15) || (blackOff == 15)) {
            String result;
            char opposingPlayer;
            if (getTurn() == 'W') opposingPlayer = 'B';
            else opposingPlayer = 'W';
            result = getResult(opposingPlayer);
            System.out.println("You have won. Result: "+result);
        }
        return retVal;
    }
    
    private String getResult (char opposingPlayer) {
        int lastCheckerLocation = lastChecker(opposingPlayer);
        String result;
        if (opposingPlayer == 'W') {
            if (lastCheckerLocation >= 17) result = "Single";
            else if (lastCheckerLocation >= 6) result = "Gammon";
            else result = "Backgammon";
        } else {
            if ((lastCheckerLocation <= 5) || (lastCheckerLocation == BLACK_BAR)) result = "Single";
            else if (lastCheckerLocation <= 16) result = "Gammon";
            else result = "Backgammon";
        }
        return result;
    }
    
    /**
     * Gets the colour of the pieces on a point
     * @param position
     * @return Returns the colour ('B' or 'W') of the pieces on a Point
     */
    public char getColour(int position){
        char c;
        
        if (position == WHITE_BAR || position == WHITE_OFF ) c = 'W';
        else if (position == BLACK_BAR || position == BLACK_OFF ) c = 'B';
        else c = boardPins[position].getColour();
        
        return c;
    }
   
    /**
     * Checks if a player can bear off or not
     * @param player
     * @return Returns true if a player can bear off, false if not
     */
    public boolean checkBearOff(char player){
        /*if (player == 'W') return whiteCheckForBearOff();
        else return blackCheckForBearOff();*/
        boolean ableToBearOff = false;
        int lastCheckerLocation = lastChecker(player);
        if ((player == 'W') && (lastCheckerLocation >= 17) && (lastCheckerLocation <= 23)) {
            ableToBearOff = true;
        } else if ((player == 'B') && (lastCheckerLocation <= 5) && (lastCheckerLocation >= 0)) {
            ableToBearOff = true;
        }
        return ableToBearOff;
    }
    
    /**
     * Figures out where a player's last (furthest from home) checker is
     * @param player
     * @return Returns the location of the player's last checker
     */
    private int lastChecker (char player) {
        int locationOfLastChecker = -1;
        if (player == 'W') {
            if (whiteBar > 0) {
                locationOfLastChecker = WHITE_BAR;
            } else {
                int i=0;
                while ((i <= 23) && (boardPins[i].getColour() != 'W')) {
                    i++;
                }
                locationOfLastChecker = i;
            }
        } else if (player == 'B') {
            if (blackBar > 0) {
                locationOfLastChecker = BLACK_BAR;
            } else {
                int i=23;
                while ((i >= 0) && (boardPins[i].getColour() != 'W')) {
                    i--;
                }
                locationOfLastChecker = i;
            }
            
        } else {
            System.out.println("Error, this is not a player");
        }
        return locationOfLastChecker;
    }
    
    /**
     * Checks if the player rolled a double
     * @param diceRoll
     * @return Returns true if a double was rolled, false if not
     */
   /* public boolean isADoubleRoll(int[] diceRoll){
        return diceRoll[0] == diceRoll[1];
    }*/
}
