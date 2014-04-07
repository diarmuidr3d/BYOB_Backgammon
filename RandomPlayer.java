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
    	List<int[]> possible_moves = b.allPossiblePlays(d,b);
    	int retVal1[][] = new int[2][2];
    	int retVal2[][] = new int[4][2];
    	Random generator = new Random(); 
    	int randomPlay = generator.nextInt(possible_moves.size());
    	int[] play = possible_moves.get(randomPlay);
    	if(play[0] == 0){
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
		    retVal1[0][1] = play[2]-1;
			for (int i =0; i < possible_moves.size(); i++) {
				if (Math.abs(possible_moves.get(i)[1] - possible_moves.get(i)[2]) == Math.abs(play[1] - play[2])) {
			   		possible_moves.remove(i);
			   		i--;
			   	}
		    }
			d.isMatchFor(Math.abs(retVal1[0][1] - retVal1[0][0]), b);
			
			// For Testing Only!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			System.out.println("\n\nRound 2\n");
	    	for (int i = 0; i < possible_moves.size(); i++) {
	    		System.out.println("Play option "+possible_moves.get(i)[0]+": "+possible_moves.get(i)[1]+"-"+possible_moves.get(i)[2]);
	    	}
	    	
	    	
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
		    retVal1[1][1] = play[2]-1;
		    d.isMatchFor(Math.abs(retVal1[1][1] - retVal1[1][0]), b);
    	}
    	else{
		   	for(int i=0;i<4;i++){
			   	retVal2[i][0] = play[1]-1;
			    retVal2[i][1] = play[2]-1;
			    d.isMatchFor(Math.abs(retVal2[i][0]-retVal2[i][1]), b);
			    possible_moves.remove(randomPlay);
				randomPlay = generator.nextInt(possible_moves.size());
		    	play = possible_moves.get(randomPlay);
		    	if(play[0] == 0){
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
    	
    	// For Testing Only!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    	if(!d.isDoubleRoll()){
    		System.out.println("\n\nsource: "+retVal1[0][0]+" destination: "+retVal1[0][1]+"\n\n");
        	System.out.println("\n\nsource: "+retVal1[1][0]+" destination: "+retVal1[1][1]+"\n\n");
        	return retVal1;
    	}
    	else{
    		System.out.println("\n\nsource: "+retVal2[0][0]+" destination: "+retVal2[0][1]+"\n\n");
        	System.out.println("\n\nsource: "+retVal2[1][0]+" destination: "+retVal2[1][1]+"\n\n");
        	System.out.println("\n\nsource: "+retVal2[2][0]+" destination: "+retVal2[2][1]+"\n\n");
        	System.out.println("\n\nsource: "+retVal2[3][0]+" destination: "+retVal2[3][1]+"\n\n");
    		return retVal2;
    	}
    }
}
