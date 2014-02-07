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
public class Point {
    private char colour;
    private int checkersCounter;
    
    public Point (){
        colour = ' ';
        checkersCounter = 0;
    }
    
    public int countCheckers(){
        return checkersCounter;
    }
    
    public char getColour(){
        return colour;
    }
    
    public void setColour(char col){
        if (isAColour(col)) colour = col;
            else { 
                System.out.println("setPin: Wrong value for colour.");
            }
    }
    
    public void setCheckersNumber(int number){
        if ( number + checkersCounter <= 15 && number >= 0){
                checkersCounter = number;
                if ( checkersCounter == 0 ) colour = ' ';
            }
            else {
                System.out.println("setPin: Wrong value for number.");
                
            }
    }
    
    public int setPin(char col, int number){
            int retValue = 0;
   
            if (isAColour(col)) colour = col;
            else { 
                System.out.println("setPin: Wrong value for colour.");
                retValue = -1;
            }
            if ( number + checkersCounter <= 15 && number >= 0){
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
        return (c=='B' || c=='b'|| c=='W'||c=='w'|| c==' ');
    }
}

