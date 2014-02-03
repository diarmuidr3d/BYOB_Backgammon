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
    
    public boolean isMovePossible (int source, int destination) {
        boolean retValue = false;
        if (
                (
                    (
                        (boardPins[source].getColour()) == (boardPins[destination].getColour()) 
                    || 
                        (boardPins[destination].getColour() == ' ')
                    )
                &&
                    (destination <= 23) 
                && 
                    (destination >= 0)
                )
           ) 
        {
            retValue = true;
        }
        return retValue;
    }
    
    public int makeMove(int source, int destination){
        int retValue = 0;        
        if (isMovePossible(source, destination)) {
            boardPins[destination].setPin((boardPins[source].getColour()), (boardPins[destination].countCheckers() + 1));
            boardPins[source].setPin((boardPins[source].getColour()), (boardPins[source].countCheckers() - 1));
        }
        else {
            System.out.println("Cannot move to this destination");
            retValue = -1;
        } 
        return retValue;
    }
    
}
