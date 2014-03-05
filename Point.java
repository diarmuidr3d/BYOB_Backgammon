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
public class Point {
    private char colour;
    private int checkersCounter;
    
    /**
    * It creates an empty point.
    */
    public Point (){
        colour = ' ';
        checkersCounter = 0;
    }
    /**
    * It returns the number of the checkers on a point.
    * @return the number of the checkers on a point.
    */   
    public int countCheckers(){
        return checkersCounter;
    }
    /**
    * It returns the colour of the checkers on a point.
    * @return It returns the colour of the point.
    */     
    public char getColour(){
        return colour;
    }
    /**
    * It sets the colour of the checkers on a point.
    * @param col it is the colour to be set as the colour of the point
    * @return It returns -1 if something went wrong, 0 otherwise.
    */     
    public int setColour(char col){
        int retValue = 0;
        if (isAColour(col)) colour = col;
            else { 
                System.out.println("setPin: Wrong value for colour.");
                retValue = -1;
            }
       return retValue;
    }
    /**
    * It sets the number of the checkers on a point.
    * @param number it is the number to be set for the point
    * @return It returns -1 if something went wrong, 0 otherwise.
    */     
    public int setCheckersNumber(int number){
        int retValue = 0;
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
    
    /**
     * It sets the colour of the checkers on a point.
     * @param col it is the colour to be set as the colour of the point
     * @param number it is the number to be set as the checker numbers on the point
     * @return It returns -1 if something went wrong, 0 otherwise.
    */    
    public int setPin(char col, int number){
        int retValue = 0;

        if ( this.setCheckersNumber(number) == -1 ) retValue = -1;
        if (this.checkersCounter == 0) col = ' ';
        if ( this.setColour(col) == -1 ) retValue = -1;

        return retValue;
    }
    /**
    * It checks if a point has no checker on.
    * @return It returns true if there is at least a checker on the point, false otherwise.
    */    
    public boolean isEmpty(){
        boolean retValue = true;
        if ( checkersCounter != 0) retValue = false;
        return retValue;
    }
    /**
   * It checks if a character is a colour.
   * @param c it is the character to be checked.
   * @return It returns true if a character is a colour, false otherwise.
   */      
    private boolean isAColour (char c){
        return (c=='B' || c=='b'|| c=='W'||c=='w'|| c==' ');
    }

}