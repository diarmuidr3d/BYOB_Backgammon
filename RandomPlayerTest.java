/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
*
* @author BYOB
*/
public class RandomPlayerTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Board b = new Board();
		Dice d = new Dice();
		
		b.setBoard();
		b.playerTurn = 'W';
		d.rollDice();
		HumanPlayer p = new HumanPlayer();
		RandomPlayer r = new RandomPlayer();
		if(d.isDoubleRoll()){
			int entry[][] = new int[4][2];
			entry = r.getPlay(d, b);
			for(int i=0;i<4;i++){
				b.makeMove(entry[i][0], entry[i][1]);
			}
		}
		else{
			int entry[][] = new int[2][2];
			entry = r.getPlay(d, b);
			for(int i=0;i<2;i++){
				b.makeMove(entry[i][0], entry[i][1]);
			}
		}
		b.printBoard();
		
	}
}
