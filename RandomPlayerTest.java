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
		
		r.getPlay(d, b);
		b.printBoard();
		
	}
}
