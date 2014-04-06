/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */

package backgammon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class RandomPlayer{

    private char playerColour;


    public RandomPlayer() {

    }
    
    /**
     * Sets the player colour
     *
     * @param c is the colour to be set
     */
    public void setPlayerColour(char c) {
        playerColour = c;
    }
    
    public int getPlay(Dice d, Board b) throws FileNotFoundException, IOException{
    	List<int[]> possible_moves;
    	possible_moves = b.allPossiblePlays(d,b);
    	int random_play = 0;

    	if(!d.isDoubleRoll()){
    		int moves1[][] = new int [2][2];
    		for(int i= 0; i<10;i++){
    			random_play = (int) (Math.random()*possible_moves.size());
        		System.out.println("\n The Random play is : "+random_play+"\n\n");
    		}
    		//player.computeMoves(moves1[][], b);
    	}
    	else{
    		int moves1[][] = new int [4][2];
    		
    		//player.computeMoves(moves1[][], b);
    	}
    	
    	return 0;
    }
}
