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
public class Backgammon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Board b = new Board();
       
        
        b.setBoard();
        b.printBoard();
        
        int[] a;
        
        for (int i = 0; i<10; i++){
           a = b.rollDice();
           System.out.println( a[0] + " " + a[1]);
        }
    }
      
    }
    

