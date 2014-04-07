/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.util.LinkedList;
import java.util.List;

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
     * This constant represents the Invalid Point error code.
     */
    public static final int INVALID_POINT = -101;

    /**
     * This constant represents the Empty Point error code.
     */
    public static final int EMPTY_POINT = -102;

    /**
     * This constant represents the Wrong Die error code.
     */
    public static final int WRONG_DIE = -103;

    /**
     * This constant represents the Blocked Point error code.
     */
    public static final int BLOCKED_POINT = -104;

    /**
     * This constant represents the can't bear off error code.
     */
    public static final int NO_BEAROFF = -105;

    /**
     * This constant represents the bear off with higher roll error code.
     */
    public static final int HIGHER_ROLL = -106;

    /**
     * This constant represents the empty the bar first error code.
     */
    public static final int EMPTY_BAR = -107;
    
    /**
     * This constant represents the case where a player was not 'B' or 'W' error code.
     */
    public static final int NOT_A_PLAYER = -108;

    /**
     * Sets an empty board.
     */
    public Board() {
        for (int j = 0; j < 24; j++) {
            boardPins[j] = new Point();
        }
    }

    /**
     * Makes an identical copy of the actual Board, instantiating a new object.
     * @return the copy of the board.
     */
    public Board copy() {

        Board newBoard = new Board();
        for (int j = 0; j < 24; j++) {
            newBoard.boardPins[j].setPin(this.boardPins[j].getColour(), this.boardPins[j].getCheckers());
        }

        newBoard.whiteBar = whiteBar;
        newBoard.blackBar = blackBar;
        newBoard.whiteOff = whiteOff;
        newBoard.blackOff = blackOff;
        newBoard.playerTurn = this.getTurn();
        return newBoard;

    }

    /**
     * Gets which player's turn it is.
     *
     * @return Returns a capital character B (Black) or W (White) depending
     * which player's turn it is
     */
    public char getTurn() {
        return playerTurn;
    }

    /**
     * Set's which player's turn it is
     * <p>
     * The method takes in a character 'B' (black) or 'W' (white) depending on
     * which player's turn it is and assigns the current player turn to tat
     * player.
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

    /**
     * Useful for jumping to bear off stages / other stages of a game for
     * testing!
     */
    public void setBoardTest() {
        for (int j = 0; j < 24; j++) {
            boardPins[j].setPin(' ', 0);
        }
        boardPins[23].setPin('W', 2);
        boardPins[1].setPin('B', 1);

        whiteBar = 1;
        blackBar = 0;
        whiteOff = 12;
        blackOff = 12;
    }

    private String printTopOfBoard() {
        String topofboard = "";
        for (int i = 12; i <= 17; i++) {
            if (boardPins[i].getCheckers() > 0) {
                topofboard = topofboard + boardPins[i].getCheckers() + boardPins[i].getColour() + "  ";
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
            if (boardPins[i].getCheckers() > 0) {
                topofboard = topofboard + boardPins[i].getCheckers() + boardPins[i].getColour() + "  ";
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
            if (boardPins[i].getCheckers() > 0) {
                bottomofboard = bottomofboard + boardPins[i].getCheckers() + boardPins[i].getColour() + "  ";
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
            if (boardPins[i].getCheckers() > 0) {
                bottomofboard = bottomofboard + boardPins[i].getCheckers() + boardPins[i].getColour() + "  ";
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
        if (this.getTurn() == 'W') {
            System.out.println("13--+---+---+---+---18  BAR 19--+---+---+---+---24  OFF");
            System.out.println(printTopOfBoard());
            System.out.println("\n");
            System.out.println(printBottomOfBoard());
            System.out.println("12--+---+---+---+---07  BAR 06--+---+---+---+---01  OFF");
            System.out.println("\n\n");
        } else {
            System.out.println("12--+---+---+---+---07  BAR 06--+---+---+---+---01  OFF");
            System.out.println(printBottomOfBoard());
            System.out.println("\n");
            System.out.println(printTopOfBoard());
            System.out.println("13--+---+---+---+---18  BAR 19--+---+---+---+---24  OFF");
            System.out.println("\n\n");
        }
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
        if ((boardPins[destination].getCheckers() < 2) && (boardPins[destination].getColour() == w)) {
            blackBar--;
            whiteBar++;
            boardPins[destination].setPin(b, (boardPins[destination].getCheckers()));
            retVal = 0;
        } else if ((boardPins[destination].getColour() == b) || (boardPins[destination].getColour() == ' ')) {
            blackBar--;
            boardPins[destination].setPin(b, (boardPins[destination].getCheckers() + 1));
            retVal = 0;
        } else if ((boardPins[destination].getCheckers() >= 2) && (boardPins[destination].getColour() == w)) {
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
        if ((boardPins[destination].getCheckers() < 2) && (boardPins[destination].getColour() == b)) {
            whiteBar--;
            blackBar++;
            boardPins[destination].setPin(w, (boardPins[destination].getCheckers()));
            retVal = 0;
        } else if ((boardPins[destination].getColour() == w) || (boardPins[destination].getColour() == ' ')) {
            whiteBar--;
            boardPins[destination].setPin(w, (boardPins[destination].getCheckers() + 1));
            retVal = 0;
        } else if ((boardPins[destination].getCheckers() >= 2) && (boardPins[destination].getColour() == b)) {
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
     * @return It returns 0 if everything went well, an error code otherwise.
     */
    public int makeMove(int source, int destination) {
        int retVal = -1;
        //from black bar
        if (source == BLACK_BAR) {
            if (blackBar > 0) {
                retVal = moveFromBlackBar(destination);
            } else {
                System.out.println("Nothing on the source pin");
                retVal = EMPTY_POINT;
            }
        } //from white bar
        else if (source == WHITE_BAR) {
            if (whiteBar > 0) {
                retVal = moveFromWhiteBar(destination);
            } else {
                System.out.println("Nothing on the source pin");
                retVal = EMPTY_POINT;
            }
        } //empty source!
        else if (boardPins[source].getCheckers() == 0) {
            System.out.println("Nothing on the source pin");
            retVal = EMPTY_POINT;
        } //to white off
        else if (destination == WHITE_OFF) {
            if (checkBearOff('W')) {
                whiteOff++;
                boardPins[source].setPin('W', (boardPins[source].getCheckers() - 1));
                retVal = 0;
            } else {
                System.out.println("You cannot bear off yet!");
                retVal = NO_BEAROFF;
            }
        } //to black off
        else if (destination == BLACK_OFF) {
            if (checkBearOff('B')) {
                blackOff++;
                boardPins[source].setPin('B', (boardPins[source].getCheckers() - 1));
                retVal = 0;
            } else {
                System.out.println("You cannot bear off yet!");
                retVal = NO_BEAROFF;
            }
        } //same colour move or empty destination
        else if ((boardPins[source].getColour() == boardPins[destination].getColour()) || (boardPins[destination].isEmpty())) {
            boardPins[destination].setPin(boardPins[source].getColour(), (boardPins[destination].getCheckers() + 1));
            boardPins[source].setPin(boardPins[source].getColour(), (boardPins[source].getCheckers() - 1));
            retVal = 0;
        } //eating move
        else if (boardPins[destination].getCheckers() == 1) {
            if (boardPins[destination].getColour() == 'W') {
                whiteBar++;
            } else if (boardPins[destination].getColour() == 'B') {
                blackBar++;
            }
            boardPins[destination].setPin(boardPins[source].getColour(), 1);
            boardPins[source].setPin(boardPins[source].getColour(), (boardPins[source].getCheckers() - 1));
            retVal = 0;
        } else {
            System.out.println("Invalid Move");
            if (boardPins[destination].getCheckers() > 1) {
                retVal = BLOCKED_POINT;
            } else {
                retVal = INVALID_POINT;
            }
        }
        /*end of the game*/
        if ((whiteOff == 15) || (blackOff == 15)) {
            String result;
            char opposingPlayer;
            if (getTurn() == 'W') {
                opposingPlayer = 'B';
            } else {
                opposingPlayer = 'W';
            }
            result = getResult(opposingPlayer);
            if (getTurn() == 'W') {
                System.out.println("\nWhite has won");
            } else {
                System.out.println("\nBlack has won");
            }
            System.out.println("Result: " + result + "\n");
            System.out.println("Thanks for playing\n");
            System.exit(0);
        }
        return retVal;
    }

    /**
     * Gives the score of the game
     *
     * @param opposingPlayer is the loser of the game
     * @return a string with the result (Single, Gammon or Backgammon)
     */
    public String getResult(char opposingPlayer) {
        int lastCheckerLocation = lastChecker(opposingPlayer);
        String result;
        if (opposingPlayer == 'W') {
            if (lastCheckerLocation >= 17) {
                result = "Single";
            } else if (lastCheckerLocation >= 6) {
                result = "Gammon";
            } else {
                result = "Backgammon";
            }
        } else {
            if ((lastCheckerLocation <= 5) || (lastCheckerLocation == BLACK_BAR)) {
                result = "Single";
            } else if (lastCheckerLocation <= 16) {
                result = "Gammon";
            } else {
                result = "Backgammon";
            }
        }
        return result;
    }

    /**
     * Gets the colour of the pieces on a point
     *
     * @param position
     * @return Returns the colour ('B' or 'W') of the pieces on a Point
     */
    public char getColour(int position) {
        char c;

        if (position == WHITE_BAR || position == WHITE_OFF) {
            c = 'W';
        } else if (position == BLACK_BAR || position == BLACK_OFF) {
            c = 'B';
        } else {
            c = boardPins[position].getColour();
        }

        return c;
    }

    /**
     * Gets the colour of the pieces on a point
     *
     * @param position
     * @return Returns the colour ('B' or 'W') of the pieces on a Point
     */
    public boolean isEmpty(int position) {
        boolean retVal = false;
        if (position == WHITE_BAR) {
            if (whiteBar == 0) retVal = true;
        } else if (position == WHITE_OFF) {
        	if (whiteOff == 0) retVal = true;
        } else if (position == BLACK_BAR) {
        	if (blackBar == 0) retVal = true;
        } else if (position == BLACK_OFF) {
        	if (blackOff == 0) retVal = true;
        } else if (position >= 0 && position < 24){
           retVal = boardPins[position].isEmpty();
        }
        return retVal;
    }

    /**
     * Checks if a player can bear off or not
     *
     * @param player
     * @return Returns true if a player can bear off, false if not
     */
    public boolean checkBearOff(char player) {
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
     *
     * @param player
     * @return Returns the location of the player's last checker
     */
    public int lastChecker(char player) {
        int locationOfLastChecker = -1;
        if (player == 'W') {
            if (whiteBar > 0) {
                locationOfLastChecker = WHITE_BAR;
            } else {
                int i = 0;
                while ((i <= 23) && (boardPins[i].getColour() != 'W')) {
                    i++;
                }
                locationOfLastChecker = i;
            }
        } else if (player == 'B') {
            if (blackBar > 0) {
                locationOfLastChecker = BLACK_BAR;
            } else {
                int i = 23;
                while ((i >= 0) && (boardPins[i].getColour() != 'B')) {
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
     * Returns the number of checkers on the specified bar
     *
     * @param p is the player
     * @return retVal: the number of checkers on the p bar or -1 if wrong player
     * input
     */
    public int getBar(char p) {
        int retVal = NOT_A_PLAYER;
        if (p == 'W') {
            retVal = whiteBar;
        } else if (p == 'B') {
            retVal = blackBar;
        }
        return retVal;
    }

    /**
     * Prints the error message associated with an error code
     * @param errCode the error code
     * @param msg a message to be added on to the start of the printed error message
     */
    public void printErrorCode(int errCode, String msg) {
        String s;

        switch (errCode) {
            case BLOCKED_POINT:
                s = msg + "Can't move to that point!";
                break;
            case EMPTY_BAR:
                s = msg + "Empty the bar first...";
                break;
            case EMPTY_POINT:
                s = msg + "No checkers on the source point!";
                break;
            case HIGHER_ROLL:
                s = msg + "Can't bear this piece off";
                break;
            case INVALID_POINT:
                s = msg + "Wrong colour point!";
                break;
            case NO_BEAROFF:
                s = msg + "Can't bear off yet!";
                break;
            case WRONG_DIE:
                s = msg + "You don't have that roll...";
                break;
            default: 
                s = "WRONG ERROR CODE...";
                break;

        }

        System.out.println(s);

    }

    /**
     * Checks if a move is valid
     * @param moves the moves being made
     * @param d is the dice
     * @param p is the player
     * @return true if it is a valid move, false if not
     */
    public boolean isValidMove(int moves[][], Dice d, char p) {
        
        Board tmpBoard = this.copy();
        Dice tmpDice = d.copy();
        int[][] movesArray = new int[moves.length][2];
        int usedDie = -5;
        boolean moveIsValid = true;
        
        tmpDice.printDice();
        
        for(int k = 0; k < moves.length; k++){
            System.arraycopy(moves[k], 0, movesArray[k], 0, 2);
        } 
        
        int i;

        if (movesArray != null) {
            if (p == 'W') {
                for (i = 0; i < movesArray.length; i++) {
                    movesArray[i][0]--;
                    if (movesArray[i][0] > 23) {
                    	movesArray[i][0] = Board.WHITE_BAR;
                    	movesArray[i][1] = movesArray[i][1] - 1;
                    } else if (movesArray[i][0] + movesArray[i][1] > 24) {
                        movesArray[i][1] = Board.WHITE_OFF;
                    } else {
                        movesArray[i][1] = movesArray[i][0] + movesArray[i][1];
                    }
                }
            } else if (p == 'B') {
                for (i = 0; i < movesArray.length; i++) {
                    movesArray[i][0]--;
                    if (movesArray[i][0] > 23) {
                        movesArray[i][0] = Board.BLACK_BAR;
                        movesArray[i][1] = 24 - movesArray[i][1];
                    } else if (movesArray[i][0] - movesArray[i][1] < 0) {
                        movesArray[i][1] = Board.BLACK_OFF;
                    } else {
                        movesArray[i][1] = movesArray[i][0] - movesArray[i][1];
                    }
                }
            }
        }
            
        i = 0;

        for (int[] move : movesArray) {
        	switch (p) {
                case ('W'):
                    if ((move[0] < 0 || (move[0] > 23 && move[0] != WHITE_BAR)) || (move[1] > 23 && move[1] != WHITE_OFF) || (tmpBoard.getColour(move[0]) == 'B')) {
                        moveIsValid = false;
                        printErrorCode(INVALID_POINT, "Move " + i + ":");
                    } else if (tmpBoard.isEmpty(move[0])) {
                        moveIsValid = false;
                        printErrorCode(EMPTY_POINT, "Move " + i + ":");
                    } else if ((usedDie = tmpDice.isMatchFor(moves[i][1], tmpBoard)) < 0) {
                        moveIsValid = false;
                        tmpDice.resetDieCheck(usedDie);
                        printErrorCode(WRONG_DIE, "Move " + i + ":");
                    } else if (!checkBearOff('W') && move[1] == WHITE_OFF) {
                        moveIsValid = false;
                        printErrorCode(NO_BEAROFF, "Move " + i + ":");
                        break;
                    } else if (move[0] > tmpBoard.lastChecker('W') && move[1] == WHITE_OFF){
                        moveIsValid = false;
                        printErrorCode(HIGHER_ROLL, "Move " + i + ":");
                        break;
                    } else if (tmpBoard.getBar('W')>0 && move[0] != WHITE_BAR){
                        moveIsValid = false;
                        printErrorCode(EMPTY_BAR, "Move " + i + ":");
                        break;  
                    } else if ( move[1] != Board.WHITE_OFF && tmpBoard.boardPins[move[1]].getColour() == 'B' && tmpBoard.boardPins[move[1]].getCheckers() > 1 ){
                        moveIsValid = false;
                        printErrorCode(BLOCKED_POINT, "Move " + i + ":");
                        break;
                    } else {
                        tmpBoard.makeMove(move[0], move[1]);
                    }
               break;
                
                case ('B'):
                     if ((move[0] < 0 || (move[0] > 23 && move[0] != BLACK_BAR)) || (move[1] > 23 && move[1] != BLACK_OFF)||(tmpBoard.boardPins[move[0]].getColour() == 'W')) {
                        moveIsValid = false;
                        printErrorCode(INVALID_POINT, "Move " + i + ":");
                    } else if (tmpBoard.boardPins[move[0]].isEmpty()) {
                         moveIsValid = false;
                        printErrorCode(EMPTY_POINT, "Move " + i + ":");
                    } else if ((usedDie = tmpDice.isMatchFor(moves[i][1], tmpBoard)) < 0) {
                        moveIsValid = false;
                        tmpDice.resetDieCheck(usedDie);
                        printErrorCode(WRONG_DIE, "Move " + i + ":");
                    } else if (!checkBearOff('B') && move[1] == BLACK_OFF) {
                        moveIsValid = false;
                        printErrorCode(NO_BEAROFF, "Move " + i + ":");
                        break;
                    } else if (move[0] < tmpBoard.lastChecker('B') && move[1] == BLACK_OFF){
                        moveIsValid = false;
                        printErrorCode(HIGHER_ROLL, "Move " + i + ":");
                        break;
                    } else if (tmpBoard.getBar('B')>0 && move[0] != BLACK_BAR){
                        moveIsValid = false;
                        printErrorCode(EMPTY_BAR, "Move " + i + ":");
                        break;  
                    } else if (move[1] != Board.BLACK_OFF && tmpBoard.boardPins[move[1]].getColour() == 'W' && tmpBoard.boardPins[move[1]].getCheckers() > 1 ){
                        moveIsValid = false;
                        printErrorCode(BLOCKED_POINT, "Move " + i + ":");
                        break;
                    } else {
                        tmpBoard.makeMove(move[0], move[1]);
                    }
                break;
            }
            i++;
            if (!moveIsValid && usedDie > 0) {
               	tmpDice.resetDieCheck(usedDie);
            }
        }
        return moveIsValid;
    }
    
    /**
     * To be called before Board.makeMove(), it changes the format of each element 
     * in movesArray into [sourcePoint; destPoint].
     * @param movesArray the array to be modified
     * @param b the actual board
     */
    public void computeMoves(int[][] movesArray, Board b) {
        int i;
        if (movesArray != null) {
            if (b.getTurn() == 'W') {
                for (i = 0; i < movesArray.length; i++) {
                    movesArray[i][0]--;
                    if (movesArray[i][0] == 24) {
                        movesArray[i][0] = Board.WHITE_BAR;
                        movesArray[i][1] = movesArray[i][1] - 1;
                    } else if (movesArray[i][0] + movesArray[i][1] > 24) {
                        movesArray[i][1] = Board.WHITE_OFF;
                    } else {
                        movesArray[i][1] = movesArray[i][0] + movesArray[i][1];
                    }
                }
            } else if (b.getTurn() == 'B') {
                for (i = 0; i < movesArray.length; i++) {
                    movesArray[i][0]--;
                    if (movesArray[i][0] == 24) {
                        movesArray[i][0] = Board.BLACK_BAR;
                        movesArray[i][1] = 24 - movesArray[i][1];
                    } else if (movesArray[i][0] - movesArray[i][1] < 0) {
                        movesArray[i][1] = Board.BLACK_OFF;
                    } else {
                        movesArray[i][1] = movesArray[i][0] - movesArray[i][1];
                    }
                }
            }
        }
    }
    
    /**
     * Gets an array of points for all a player's checkers on the board.
     * Passes this to searchForPlays. Gets the return from this and processes it to remove unwanted moves.
     * @param d is the dice
     * @param b is the board
     * @return a list of integer arrays corresponding to the moves in the format {play, source, destination}
     */
    public List<int[]> allPossiblePlays(Dice d, Board b) {
    	Board boardCopy = b.copy();
    	int[] source = new int[15];
    	int sourceCounter = 0;
    	int playCounter = 0;
    	int overAllMax = 0, currentPlayMax = 0, biggestMove = 0;
    	if (boardCopy.getBar(boardCopy.getTurn()) > 0) {
    		for (int i = 0; i < boardCopy.getBar(boardCopy.getTurn()); i++) {
    			if (boardCopy.getTurn() == 'B') {
    				source[sourceCounter] = BLACK_BAR;
    			} else {
    				source[sourceCounter] = WHITE_BAR;
    			}
    			sourceCounter++;
    		}
    	}
    	if (!(boardCopy.getBar(boardCopy.getTurn()) > 3 && d.isDoubleRoll()) && !(boardCopy.getBar(boardCopy.getTurn()) > 1 && !d.isDoubleRoll())) {
	    	for (int i = 0; i <= 23; i++) {
	    		if (boardCopy.getColour(i) == b.getTurn()) {
	    			source[sourceCounter] = i+1;
	    			sourceCounter++;
	    		}
	    	}
    	}
    	int[] sourcePoints = new int[sourceCounter];
    	for (int i = 0; i < sourceCounter; i++) {
    		sourcePoints[i] = source[i];
    	}
    	List<int[]> allPlays = searchForPlays(d, playCounter, boardCopy, sourcePoints);
    	playCounter=0;
    	for (int i = 0; i < allPlays.size(); i++) {
    		if ((allPlays.get(i)[0]) == playCounter) {
    			currentPlayMax++;
    		} else {
    			playCounter = allPlays.get(i)[0];
    			if (currentPlayMax > overAllMax) {
    				overAllMax = currentPlayMax;
    				currentPlayMax = 1;
    			}
    		}
    	}
    	if (currentPlayMax > overAllMax) {
			overAllMax = currentPlayMax;
			currentPlayMax = 0;
		}
    	if ((allPlays.size() == 2) && (allPlays.get(0)[1] == allPlays.get(1)[1])) {
    		if (Math.abs(allPlays.get(0)[1] - allPlays.get(0)[2]) > Math.abs(allPlays.get(1)[1] - allPlays.get(1)[2])) {
    			allPlays.remove(1);
    		} else if (Math.abs(allPlays.get(0)[1] - allPlays.get(0)[2]) < Math.abs(allPlays.get(1)[1] - allPlays.get(1)[2])) {
    			allPlays.remove(0);
    		}
    	}
    	if (overAllMax == 1) {
    		for (int i = 0; i < allPlays.size(); i++) {
    			if ((Math.abs(allPlays.get(i)[1] - allPlays.get(i)[2])) > biggestMove) {
    				biggestMove = Math.abs(allPlays.get(i)[1] - allPlays.get(i)[2]);
    			} 
    		}
    		for (int i = 0; i < allPlays.size(); i++) {
    			if ((Math.abs(allPlays.get(i)[1] - allPlays.get(i)[2])) < biggestMove) {
    				allPlays.remove(i);
    				i--;
    			}
    		}
    	}
    	return allPlays;
    }
    
    /**
     * Method only used in searchForPlays, for replacing integers with constants for Bar and Off
     * @param source is the source point
     * @param dice is the dice (negative for black)
     * @return is the constant or normal
     */
    private int barAndOffSFP (int source, int dice) {
    	int retVal;
    	if (source == BLACK_BAR) retVal = 25 + dice;
		else if (source == WHITE_BAR) retVal = dice;
		else if (source + dice > 24) retVal = WHITE_OFF;
		else if (source + dice < 1) retVal = BLACK_OFF;
		else retVal = source + dice;
    	return retVal;
    }

    /**
     * Checks all moves a player could make against isValidMove, returns all valid moves
     * @param d is the dice
     * @param boardCopy is a copy of the board
     * @param sourcePoints are all points that the player has a checker on
     * @return a List of integer arrays consisting of source and destination of all possible valid moves
     */
    private List<int[]> searchForPlays(Dice d, int playCounter, Board boardCopy, int[] sourcePoints) {
    	int moves1[][] = new int [1][2];
    	int moves2[][] = new int [2][2];
    	int moves3[][] = new int [3][2];
    	int moves4[][] = new int [4][2];
    	int index = 0;
    	List<int[]> retVal = new LinkedList<int[]>();
    	int dice1, dice2;
    	if (boardCopy.getTurn() == 'B') {
    		dice1 = 0-d.getFirstDice();
    		dice2 = 0-d.getSecondDice();
    	} else {
    		dice1 = d.getFirstDice();
    		dice2 = d.getSecondDice();
    	}
    	if (!d.isDoubleRoll()) {
    		moves1[0][0] = sourcePoints[0];
    		moves1[0][1] = d.getFirstDice();
    		System.out.println("SRC: "+sourcePoints[0]+" D: "+d.getFirstDice());
    		if (boardCopy.isValidMove(moves1, d, boardCopy.getTurn())) {
    			int moveToRetVal[] = new int[3];
    			moveToRetVal[0] = playCounter;
    			moveToRetVal[1] = sourcePoints[0];
    			moveToRetVal[2] = barAndOffSFP (sourcePoints[0], dice1);
    			retVal.add(index, moveToRetVal);
    			index++;
    			moves2[0][0] = sourcePoints[0];
    			moves2[0][1] = d.getFirstDice();
    			moves2[1][0] = sourcePoints[0] + dice1;
    			moves2[1][1] = d.getSecondDice();
    			if (boardCopy.isValidMove(moves2, d, boardCopy.getTurn())) {
    				int moveToRetVal2[] = new int[3];
        			moveToRetVal2[0] = playCounter;
        			moveToRetVal2[1] = sourcePoints[0] + dice1;
        			moveToRetVal2[2] = barAndOffSFP (sourcePoints[0], dice1+dice2);
        			retVal.add(index, moveToRetVal2);
        			index++;
    			}
    			playCounter++;
    		}
    		moves1[0][0] = sourcePoints[0];
    		moves1[0][1] = d.getSecondDice();
    		if (boardCopy.isValidMove(moves1, d, boardCopy.getTurn())) {
    			int moveToRetVal3[] = new int[3];
    			moveToRetVal3[0] = playCounter;
    			moveToRetVal3[1] = sourcePoints[0];
    			moveToRetVal3[2] = barAndOffSFP (sourcePoints[0], dice2);
    			retVal.add(index, moveToRetVal3);
    			index++;
    			moves2[0][0] = sourcePoints[0];
    			moves2[0][1] = d.getSecondDice();
    			moves2[1][0] = sourcePoints[0] + dice2;
    			moves2[1][1] = d.getFirstDice();
    			if (boardCopy.isValidMove(moves2, d, boardCopy.getTurn())) {
    				int moveToRetVal4[] = new int[3];
        			moveToRetVal4[0] = playCounter;
        			moveToRetVal4[1] = sourcePoints[0] + dice2;
        			moveToRetVal4[2] = barAndOffSFP (sourcePoints[0], dice1+dice2);
        			retVal.add(index, moveToRetVal4);
        			index++;
    			}
    			playCounter++;
    		}
    	} else {
    		moves1[0][0] = sourcePoints[0];
    		moves1[0][1] = d.getFirstDice();
    		if (boardCopy.isValidMove(moves1, d, boardCopy.getTurn())) {
    			int moveToRetVal5[] = new int[3];
    			moveToRetVal5[0] = playCounter;
    			moveToRetVal5[1] = sourcePoints[0];
    			moveToRetVal5[2] = barAndOffSFP (sourcePoints[0], dice1);
    			retVal.add(moveToRetVal5);
    			moves2[0][0] = sourcePoints[0];
    			moves2[0][1] = d.getFirstDice();
    			moves2[1][0] = dice1 + sourcePoints[0];
    			moves2[1][1] = d.getSecondDice();
    			if (boardCopy.isValidMove(moves2, d, boardCopy.getTurn())) {
    				int moveToRetVal6[] = new int[3];
    				moveToRetVal6[0] = playCounter;
        			moveToRetVal6[1] = dice1 + sourcePoints[0];
        			moveToRetVal6[2] = barAndOffSFP (sourcePoints[0], dice1+dice2);
        			retVal.add(moveToRetVal6);
        			moves3[0][0] = sourcePoints[0];
        			moves3[0][1] = d.getFirstDice();
        			moves3[1][0] = dice1 + sourcePoints[0];
        			moves3[1][1] = d.getSecondDice();
    				moves3[2][0] = dice1 + sourcePoints[0] + dice2;
    				moves3[2][1] = d.getSecondDice();
    				if (boardCopy.isValidMove(moves3, d, boardCopy.getTurn())) {
    					int moveToRetVal7[] = new int[3];
    					moveToRetVal7[0] = playCounter;
            			moveToRetVal7[1] = dice1 + sourcePoints[0] + dice2;
            			moveToRetVal7[2] = barAndOffSFP (sourcePoints[0], dice1+dice2+dice2);
            			retVal.add(moveToRetVal7);
            			moves4[0][0] = sourcePoints[0];
            			moves4[0][1] = d.getFirstDice();
            			moves4[1][0] = dice1 + sourcePoints[0];
            			moves4[1][1] = d.getSecondDice();
            			moves4[2][0] = dice1 + sourcePoints[0] + dice2;
            			moves4[2][1] = d.getSecondDice();
    					moves4[3][0] = dice2 + dice1 + sourcePoints[0] + dice2;
    					moves4[3][1] = d.getSecondDice();
    					if (boardCopy.isValidMove(moves4, d, boardCopy.getTurn())) {
    						int moveToRetVal8[] = new int[3];
    						moveToRetVal8[0] = playCounter;
    	        			moveToRetVal8[1] = dice2 + dice1 + sourcePoints[0] + dice2;
    	        			moveToRetVal8[2] = barAndOffSFP (sourcePoints[0], dice1+dice2+dice1+dice2);
    	        			retVal.add(moveToRetVal8);
    					}
    				}
    			}
    			playCounter++;
    		}
    	}
    	if (sourcePoints.length > 1) {
    		int[] sourcePointsShorter = new int[sourcePoints.length-1];
    		for (int i=0; i < sourcePointsShorter.length; i++) {
    			sourcePointsShorter[i] = sourcePoints[i+1];
    		}
    		List<int[]> recursiveReturn = boardCopy.searchForPlays(d, playCounter, boardCopy, sourcePointsShorter);
    		for (int i = 0; i < recursiveReturn.size(); i ++) {
    			retVal.add(recursiveReturn.get(i));
    		}
    	}
    	return retVal;
    }
}
