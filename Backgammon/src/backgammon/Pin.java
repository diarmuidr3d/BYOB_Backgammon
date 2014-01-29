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
    public char colour;
    public int checkersCounter;
    
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
    
    public void setPin(char col, int number){
        if (isAColour(col)) colour = col;
        else System.out.println("setPin: Wrong value for colour.");
        if ( number + checkersCounter <= 15) checkersCounter += number;
        else System.out.println("setPin: Wrong value for number.");
    }
    
    private boolean isAColour (char c){
        return (c=='B' || c=='b'|| c=='W'||c=='w');
    }
}

