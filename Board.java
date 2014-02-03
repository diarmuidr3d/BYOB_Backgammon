/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon;

import java.util.Random;

/**
 *
 * @author BYOB
 */
public class Board {
    public static int WHITE_OFF = 24;
    public static int BLACK_OFF = 25;
    public static int WHITE_BAR = 26;
    public static int BLACK_BAR = 27;
    Pin[] boardPins  = new Pin[24]; // 0-23 pins are the actual pins. Setting refers to: http://en.wikipedia.org/wiki/File:Bg-movement.svg
    int whiteBar;
    int blackBar;
    int whiteOff;
    int blackOff;
    
    public Board (){
        
        for (int j = 0; j<24; j++){
            boardPins[j] = new Pin();
            whiteBar = 0;
            blackBar = 0;
            whiteOff = 0;
            blackOff = 0;

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
        
    }
    
    public void printBoard (){
        for (int i=0; i < 24; i++)
            System.out.println(i + " " + boardPins[i].countCheckers() + " " + boardPins[i].getColour());
        System.out.println(" ");
    }
    
    public int[] rollDice(){
        int[] a = new int[2];
        Random randomGenerator = new Random();
        a[0] = randomGenerator.nextInt(6) + 1;
        a[1] = randomGenerator.nextInt(6) + 1;
        return a;
    }
    
    public int makeMove(int source, int destination) {
        int retVal = -1;
        
        if (boardPins[source].countCheckers() == 0) {
            System.out.println("Nothing on the source pin");
        }
        else if (destination == WHITE_OFF) {
            whiteOff++;
            boardPins[source].setPin('W', (boardPins[source].countCheckers() - 1));
            retVal = 0;
        }
        else if (destination == BLACK_OFF) {
            blackOff++;
            boardPins[source].setPin('B', (boardPins[source].countCheckers() - 1));
            retVal = 0;
        }
        else if (source == WHITE_BAR) {
            whiteBar--;
            boardPins[destination].setPin('W', (boardPins[destination].countCheckers() + 1));
            retVal = 0;
        }
        else if (source == BLACK_BAR) {
            blackBar--;
            boardPins[destination].setPin('B', (boardPins[destination].countCheckers() + 1));
            retVal = 0;
        }
        else if ((boardPins[source].getColour() == boardPins[destination].getColour()) || (boardPins[destination].isEmpty())) {
            boardPins[source].setPin(boardPins[source].getColour(), (boardPins[source].countCheckers() - 1));
            boardPins[destination].setPin(boardPins[source].getColour(), (boardPins[destination].countCheckers() + 1));
            retVal = 0;
        }
        else if (boardPins[destination].countCheckers() == 1) {
            if (boardPins[destination].getColour() == 'W') whiteBar++;
            else if (boardPins[destination].getColour() == 'B') blackBar++;
            boardPins[source].setPin(boardPins[source].getColour(), (boardPins[source].countCheckers() - 1));
            boardPins[destination].setPin(boardPins[source].getColour(), 1);
            retVal = 0;
        }
        return retVal;
    }
}
 
    

