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
    
    public int getFirstDice() {
        return dice1;
    }
    
    public int getSecondDice() {
        return dice2;
    }
    
    public void rollDice() {
        dice1 = randomGenerator.nextInt(6) +1;
        dice2 = randomGenerator.nextInt(6) +1;
        checkedDice1 = false;
        checkedDice2 = false;
    }
    
    public boolean isDoubleRoll() {
        return dice1 == dice2;
    }
    
    public int valueAt(int index) {
        int retVal = -1;
        if (index > 4) throw new OutOfBoundsException();
        if (index == 1) retVal = dice1;
        else if (index == 2) retVal = dice2;
        else if (index > 2) retVal = dice1;
        return retVal;
    }
    
    public boolean isMatchFor(int[] move) {
        boolean retVal = false;
        if (!isDoubleRoll()) {
            if ((abs(move[0] - move[1]) == dice1) && (!checkedDice1)) {
                checkedDice1 = true;
                retVal = true;
            }
            else if ((abs(move[0] - move[1]) == dice2) && (!checkedDice2)) {
                checkedDice2 = true;
                retVal = true;
            }
        } else if (abs(move[0] - move[1]) == dice1){
            if (!checkedDice1) {
                retVal = true;
                checkedDice1 = true;
            } else if (!checkedDice2) {
                retVal = true;
                checkedDice2 = true;
            } else if (!checkedDice3) {
                retVal = true;
                checkedDice3 = true;
            } else if (!checkedDice4) {
                retVal = true;
                checkedDice4 = true;
            }
        }
        return retVal;
    }
}
