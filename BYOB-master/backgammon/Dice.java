/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */

package backgammon;

import java.util.Random;

/**
 *
 * @author BYOB
 */
public class Dice {
    private int dice1;
    private int dice2;
    private boolean checkedDice1, checkedDice2, checkedDice3, checkedDice4;
    private Random randomGenerator;
    
    public Dice(){
        randomGenerator = new Random();
        checkedDice1 = false;
        checkedDice2 = false;
        checkedDice3 = false;
        checkedDice4 = false;
         
    }
    
    public void printDice(){
        System.out.println("Dice1: " + dice1 + " Dice2: " + dice2);
    }
    
    public Dice copy(){
        Dice newDice = new Dice();
        newDice.dice1 = this.dice1;
        newDice.dice2 = this.dice2;
        newDice.checkedDice1 = this.checkedDice1;
        newDice.checkedDice2 = this.checkedDice2;
        newDice.checkedDice3 = this.checkedDice3;
        newDice.checkedDice4 = this.checkedDice4;
        return newDice;
    }
    
    /**
     * Gives the value of the first dice
     * @return dice1 The integer value of one of two dice rolled
     */
    public int getFirstDice() {
        return dice1;
    }
    
    /**
     * Gives the value of the second dice.
     * @return dice1 The integer value of one of two dice rolled.
     */
    public int getSecondDice() {
        return dice2;
    }
    
    /**
     * Rolls the dice and sets the checks for isMatchFor() to false.
     */
    public void rollDice() {
        dice1 = randomGenerator.nextInt(6) + 1;
        dice2 = randomGenerator.nextInt(6) + 1;
        checkedDice1 = false;
        checkedDice2 = false;
        checkedDice3 = false;
        checkedDice4 = false;
    }
    
    /**
     * Checks if the dice was a double roll.
     * @return true if the dice are equal, false if not
     */
    public boolean isDoubleRoll() {
        return dice1 == dice2;
    }
    
    /**
     * Returns the value of a particular dice
     * <p> This is simply for ease of use in loops. </p>
     * @param index is which dice is wanted.
     * @return retVal: the value of the dice
     */
    public int valueAt(int index) {
        int retVal = -1;
        if (index > 4) throw new OutOfBoundsException();
        if (index == 1) retVal = dice1;
        else if (index == 2) retVal = dice2;
        else if ((index > 2) && (isDoubleRoll())) retVal = dice1;
        return retVal;
    }
    
    /**
     * Checks if the move has a matching dice
     * <p> If the move has a matching dice it is marked as checked.
     * If a dice has been checked already it is not used.</p>
     * @param move an array in which move[0] is the source and move[1] is the destination
     * @param b is the board
     * @return retVal: true if there is an unchecked dice for that move, false if not
     */
    public int isMatchFor(int move, Board b) {
        int retVal = Board.WRONG_DIE;
        
        if (!isDoubleRoll()) {
           if (move == dice1 && !checkedDice1) {
                    checkedDice1 = true;
                    retVal = 1;
                }
                else if ( move == dice2 && !checkedDice2) {
                    checkedDice2 = true;
                    retVal = 2;
                }
        } else if (move == dice1){
            if (!checkedDice1) {
                retVal = 1;
                checkedDice1 = true;
            } else if (!checkedDice2) {
                retVal = 2;
                checkedDice2 = true;
            } else if (!checkedDice3) {
                retVal = 3;
                checkedDice3 = true;
            } else if (!checkedDice4) {
                retVal = 4;
                checkedDice4 = true;
            }
        }
      
        return retVal;
    }
    
     /**
      * If the move fails reset the die check to false
      * @param die the die to be reset
      */
    public void resetDieCheck(int die) {
        if (die == 1) {
            checkedDice1 = false;
        } else if (die == 2) {
            checkedDice2 = false;
        } else if (die == 3) {
            checkedDice3 = false;
        } else if (die == 4) {
            checkedDice4 = false;
        }  
    }
}