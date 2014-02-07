package backgammon;

import java.util.Random;

/**
 *
 * @author BYOB
 */
public class Board {
    
    Point[] boardPins  = new Point[24]; // 0-23 pins are the actual pins. Setting refers to: http://en.wikipedia.org/wiki/File:Bg-movement.svg
    int whiteBar;
    int blackBar;
    int whiteOff;
    int blackOff;
    public static final int WHITE_OFF = 24;
    public static final int BLACK_OFF = 25;
    public static final int WHITE_BAR = 26;
    public static final int BLACK_BAR = 27;
    
    public Board () {        
        for (int j = 0; j<24; j++){
            boardPins[j] = new Point();       
        }
    }
    
    public void setBoard() {
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
    
    public String printTopOfBoard() {
            String topofboard = "";
            for (int i=12; i <= 17; i++){
                if(boardPins[i].countCheckers()>0)
                    topofboard = topofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
                else
                    topofboard = topofboard + "|   ";
            }
            if(whiteBar == 0)
                topofboard = topofboard + "    ";
            else
                topofboard = topofboard + whiteBar + "W  ";
            for (int i=18; i <= 23; i++){
                if(boardPins[i].countCheckers()>0)
                    topofboard = topofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
                else
                    topofboard = topofboard + "|   ";
            }
            if(blackOff == 0) 
                topofboard = topofboard + "    ";
            else 
                topofboard = topofboard + blackOff + "B ";		
        return topofboard;        
	} 
	
    public String printBottomOfBoard() {
	String bottomofboard = "";
	for (int i=11; i >=6; i--){
	    if(boardPins[i].countCheckers()>0)
		bottomofboard = bottomofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
	    else
		bottomofboard = bottomofboard + "|   ";
        }	
        if(blackBar == 0)
            bottomofboard = bottomofboard + "    ";
        else
            bottomofboard = bottomofboard + blackBar + "B  ";
	for (int i=5; i >=0; i--){
	    if(boardPins[i].countCheckers()>0)
		bottomofboard = bottomofboard + boardPins[i].countCheckers() + boardPins[i].getColour() + "  ";
	    else
		bottomofboard = bottomofboard + "|   ";
        }	
	if(whiteOff == 0)
            bottomofboard = bottomofboard + "   ";
        else
            bottomofboard = bottomofboard + whiteOff + "W ";
        return bottomofboard;
    }
	
    public void printBoard() {
    	System.out.println("13--+---+---+---+---18  BAR 19--+---+---+---+---24  OFF");
    	System.out.println(printTopOfBoard());
    	System.out.println("\n");
    	System.out.println(printBottomOfBoard());
    	System.out.println("12--+---+---+---+---07  BAR 06--+---+---+---+---01  OFF");
        System.out.println("\n\n");
    }
    
    public int[] rollDice(){
        int[] a = new int[2];
        Random randomGenerator = new Random();
        a[0] = randomGenerator.nextInt(6) + 1;
        a[1] = randomGenerator.nextInt(6) + 1;
        return a;
    }
    
    public int makeMove(int source, int destination){
        Point sourcePin = boardPins[source];
        Point destPin = boardPins[destination];
        int retVal = 0;      
        if (sourcePin.countCheckers() == 0) {
            System.out.println("Nothing on the source pin");
        }
        else if (destination == WHITE_OFF) {
            whiteOff++;
            sourcePin.setPin('W', (sourcePin.countCheckers() - 1));
            retVal = 0;
        }
        else if (destination == BLACK_OFF) {
            blackOff++;
            sourcePin.setPin('B', (sourcePin.countCheckers() - 1));
            retVal = 0;
        }
        else if (source == WHITE_BAR) {
            whiteBar--;
            destPin.setPin('W', (destPin.countCheckers() + 1));
            retVal = 0;
        }
        else if (source == BLACK_BAR) {
            blackBar--;
            destPin.setPin('B', (destPin.countCheckers() + 1));
            retVal = 0;
        }
        else if ((sourcePin.getColour() == destPin.getColour()) || (destPin.isEmpty())) {
            sourcePin.setPin(sourcePin.getColour(), (sourcePin.countCheckers() - 1));
            destPin.setPin(sourcePin.getColour(), (destPin.countCheckers() + 1));
            retVal = 0;
        }
        else if (destPin.countCheckers() == 1) {
            if (destPin.getColour() == 'W') whiteBar++;
            else if (destPin.getColour() == 'B') blackBar++;
            sourcePin.setPin(sourcePin.getColour(), (sourcePin.countCheckers() - 1));
            destPin.setPin(sourcePin.getColour(), 1);
            retVal = 0;
        }      
        return retVal;
    }
}