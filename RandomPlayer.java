/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */

package backgammon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
    
  /**
     * Calls the allPossiblePlays method, selects one of these plays at 
random and returns it to the calling method
     * @param d
     * @param b
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int[][] getPlay(Dice d, Board b) throws FileNotFoundException, IOException{
    	int retVal1[][] = new int[2][2];
    	int retVal2[][] = new int[4][2];
    	Random generator = new Random();
    	Board boardCopy = b.copy();
    	Dice diceCopy = d.copy();
    	List<int[]> possible_moves = boardCopy.allPossiblePlays(diceCopy,boardCopy);
    	int randomPlay = generator.nextInt(Math.abs(possible_moves.size()));
    	int[] play = possible_moves.get(randomPlay);
    	if(play[0] == possible_moves.get(0)[0]){
    		play = possible_moves.get(0);
    	}
    	else{
    		while(possible_moves.get(randomPlay-1)[0] == play[0]) {
        		randomPlay--;
        		play = possible_moves.get(randomPlay);
        	}
    	}
    	if(!d.isDoubleRoll()){
		   	retVal1[0][0] = play[1]-1;
		   	if (retVal1[0][1] != Board.BLACK_OFF) retVal1[0][1] = play[2]-1;
		   	else retVal1[0][1] = play[2];
			diceCopy.isMatchFor(Math.abs(retVal1[0][1] - retVal1[0][0]), boardCopy);
			boardCopy.makeMove(retVal1[0][0], retVal1[0][1]);
			possible_moves = boardCopy.allPossiblePlays(diceCopy,boardCopy);
			randomPlay = generator.nextInt(possible_moves.size());
			play = possible_moves.get(randomPlay);
			if(play[0] == possible_moves.get(0)[0]){
				play = possible_moves.get(0);
			}
			else{
				while(possible_moves.get(randomPlay-1)[0] == play[0]) {
					randomPlay--;
					play = possible_moves.get(randomPlay);
				}
			}
			retVal1[1][0] = play[1]-1;
			if (retVal1[0][1] != Board.BLACK_OFF) retVal1[1][1] = play[2]-1;
			else retVal1[1][1] = play[2];
			diceCopy.isMatchFor(Math.abs(retVal1[0][1] - retVal1[0][0]), boardCopy);
			boardCopy.makeMove(retVal1[1][0], retVal1[1][1]);
			possible_moves = boardCopy.allPossiblePlays(diceCopy,boardCopy);
    	}
    	else{
		   	for(int i=0;i<4;i++){
			   	retVal2[i][0] = play[1]-1;
			    retVal2[i][1] = play[2]-1;
			    diceCopy.isMatchFor(Math.abs(retVal1[0][1] - retVal1[0][0]), boardCopy);
				boardCopy.makeMove(retVal1[0][0], retVal1[0][1]);
				possible_moves = boardCopy.allPossiblePlays(diceCopy,boardCopy);
				randomPlay = generator.nextInt(possible_moves.size());
		    	play = possible_moves.get(randomPlay);
		    	if(play[0] == possible_moves.get(0)[0]){
		    		play = possible_moves.get(0);
		    	}
		    	else{
		    		while(possible_moves.get(randomPlay-1)[0] == play[0]) {
		        		randomPlay--;
		        		play = possible_moves.get(randomPlay);
		        	}
		    	}
		   	}
    	}
    	if(!d.isDoubleRoll()){
        	return retVal1;
    	}
    	else{
    		return retVal2;
    	}
    }
}
