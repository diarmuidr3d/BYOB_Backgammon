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
public class BoardTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Board b = new Board();
       
        
        b.setBoard();
        b.makeMove(23, 6);
        b.printBoard();
        
       /* for (int i = 0; i<10; i++){
           a = b.rollDice();
           System.out.println( a[0] + " " + a[1]);
        }*/
        
       
        
       b.makeMove(0, 6);
       b.printBoard();
      
    }
}
    

