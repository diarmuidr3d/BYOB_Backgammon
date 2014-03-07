/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon;

import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author BYOB
 */
public class Dice {
    private int dice1;
    private int dice2;
    private boolean checkedDice1, checkedDice2, checkedDice3, checkedDice4;
    private Random randomGenerator = new Random();
    
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
        dice1 = randomGenerator.nextInt(6) +1;
        dice2 = randomGenerator.nextInt(6) +1;
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
     * @return retVal: true if there is an unchecked dice for that move, false if not
     */
    public int isMatchFor(int[] move) {
        int retVal = -1;
        if (move[0] == Board.BLACK_BAR) {
            move[0] = 24;
        } else if (move [0] == Board.WHITE_BAR) {
            move[0] = -1;
        }
        if (move[1] == Board.BLACK_OFF) {
            move[1] = -1;
            
        } else if (move[1] == Board.WHITE_OFF) {
            move[1] = 24;
        } else if (!isDoubleRoll()) {
            if ((abs(move[0] - move[1]) == dice1) && (!checkedDice1)) {
                checkedDice1 = true;
                retVal = 1;
            }
            else if ((abs(move[0] - move[1]) == dice2) && (!checkedDice2)) {
                checkedDice2 = true;
                retVal = 2;
            }
        } else if (abs(move[0] - move[1]) == dice1){
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
        if (move[0] == 24) {
            move[0] = Board.BLACK_BAR;
        } else if (move [0] == -1) {
            move[0] = Board.WHITE_BAR;
        }
        if (move[1] == -1) {
            move[1] = Board.BLACK_OFF;
        } else if (move[1] == 24) {
            move[1] = Board.WHITE_OFF;
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
