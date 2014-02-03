/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon;

/**
 *
 * @author BYOB
 */
public class Pin {
    private char colour;
    private int checkersCounter;
    
    public Pin (){
        colour = ' ';
        checkersCounter = 0;
    }
    
    public int countCheckers(){
        return checkersCounter;
    }
    
    public char getColour(){
        return colour;
    }
    
    public int setPin(char col, int number){
            int retValue = 0;
            if (isAColour(col)) colour = col;
            else { 
                System.out.println("setPin: Wrong value for colour.");
                retValue = -1;
            }
            if ( number + checkersCounter <= 15 && number >= 1){
                checkersCounter = number;
                if ( checkersCounter == 0 ) colour = ' ';
            }
            else {
                System.out.println("setPin: Wrong value for number.");
                retValue = -1;
            }
            
            return retValue;
  
    }
    
    public boolean isEmpty(){
        boolean retValue = true;
        if ( checkersCounter != 0) retValue = false;
        return retValue;
    }
    
    private boolean isAColour (char c){
        return (c=='B' || c=='b'|| c=='W'||c=='w');
    }
}

