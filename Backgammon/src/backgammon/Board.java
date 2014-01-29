/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon;

import java.lang.reflect.Array;

/**
 *
 * @author BYOB
 */
public class Board {
    
    Pin[] boardPins  = new Pin[26]; // notice that 0-23 pins are the actual pins, 25-26 are the white and black bar respectively
    //Setting refers to: http://en.wikipedia.org/wiki/File:Bg-movement.svg
    public Board (){
        
        for (int j = 0; j<26; j++){
            boardPins[j] = new Pin();
        }
        
        boardPins[0].setPin('W', 2);
        boardPins[23].setPin('B', 2);
        
        boardPins[5].setPin('B', 5);
        boardPins[18].setPin('W', 5);
        
        boardPins[7].setPin('B', 3);
        boardPins[16].setPin('W', 3);
        
        boardPins[11].setPin('W', 5);
        boardPins[12].setPin('B', 5);
        
        boardPins[24].setPin('W', 0);
        boardPins[25].setPin('B', 0);
        
    }
    
    public void printBoard (){
        for (int i=0; i < 26; i++)
            System.out.println(i + " " + boardPins[i].countCheckers() + " " + boardPins[i].getColour());
    }
    
    
}
