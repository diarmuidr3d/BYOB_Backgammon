package backgammon;


//
// Team Name: BYOB
// Version:
//

import java.util.ArrayList;

public class AiPlayer {

	private int playerId;
	private Board gameBoard;
	private Dice gameDice;
	
	
	AiPlayer (int setPlayerId, Board setBoard, Dice setDice) {
		playerId = setPlayerId;
		gameBoard = setBoard;
		gameDice = setDice;
	    
	}
	
	
	public int getPlayerId () {
		return playerId;
	}
        
    private int getAdversaryId(){
    	if (playerId == Board.O_PLAYER_ID) return Board.X_PLAYER_ID;
    	else return Board.O_PLAYER_ID;
    }
	
	        /**
         * This function evaluates the number of blots and hitters for both players and 
         * try to maximise the score for a board where the AIPlayer has got more hitters
         * than blots and the adversary has got more blots than hitters.
         * @param b the board to be evaluated
         * @return 
         */
	private float blotEval(Board b){
            int playerBlots=0, adversaryBlots=0, playerHitters = 0, adversaryHitters = 0, score;
            int adversary = this.getAdversaryId();
            int player = this.getPlayerId();
            
            for(int i = 0; i < Board.NUM_PIPS; i++){
                if (b.checkers[adversary][i] == 1) adversaryBlots++;
                else if (b.checkers[player][i] == 1) playerBlots++;
                
                if (b.checkers[adversary][i] > 2) adversaryHitters++;
                else if (b.checkers[player][i] > 2) playerHitters++;
                
            }
            
            score = (adversaryBlots - playerBlots);
            
            //System.out.println("blotScore: " + adversaryBlots + " - " + playerBlots + " = " + score);
            
            return score;
        }
        
        private int blockEval(Board b){
            ArrayList<Play> plays = b.allPossiblePlays(getPlayerId(), gameDice);
            Play p = new Play();
            for (int i = 0; i < plays.size(); i++) {
            	p = plays.get(i);
            	for (int j = 0; j < p.length(); j++)
            		p.getMove(j).getFromPip();
            }
        	return 0;
        }
        
        private float runEval(Board b){
            return 0;
        }
        
        private float bearOffEval(Board b){
            return 0;
        }
        
        private float spacingEval(Board b){
            return 0;
        }
        
        private float computeHeuristic(Board b){
            float heuristicScore, blotScore, blockScore, runScore, bearOffScore, spacingScore;
            
            blotScore = this.blotEval(b);
            blockScore = this.blockEval(b);
            runScore = this.runEval(b);
            bearOffScore = this.bearOffEval(b);
            spacingScore = this.spacingEval(b);
            
            heuristicScore = blotScore + blockScore + runScore + bearOffScore + spacingScore;
            
            return heuristicScore;
        }
        
	private int findBestBoard (ArrayList<Board> allBoardsList) {
		int bestBoard = 0, i = 0;
		float max;
                int size = allBoardsList.size();
                float[] boardScores = new  float[size];
                
                for( Board b : allBoardsList ){
                    boardScores[i] = this.computeHeuristic(b);
                    i++;
                }
               
                max = boardScores[0];
                for (i=0; i < size; i++){
                    if (boardScores[i] > max){
                        System.out.println(boardScores[i]>max);
                        max = boardScores[i];
                        bestBoard = i;
                    }
                }
                
                System.out.println(boardScores[bestBoard]);
                
		return bestBoard;
	}
	
	
	public Play getPlay () {
		ArrayList<Play> allPlayList;
		ArrayList<Board> allBoardsList = new ArrayList<Board>();
		int bestBoard;
		Play chosenPlay;
		
		allPlayList = gameBoard.allPossiblePlays (playerId, gameDice);
		if (!allPlayList.isEmpty()) {
			for (int i=0; i<allPlayList.size(); i++) {
				allBoardsList.add(new Board(gameBoard));
				allBoardsList.get(i).doPlay(playerId, allPlayList.get(i));
			}
			bestBoard = findBestBoard(allBoardsList);
			chosenPlay = allPlayList.get(bestBoard);
		}
		else {
			chosenPlay = new Play();
		}
		
		return chosenPlay;
	}
		
	
}
