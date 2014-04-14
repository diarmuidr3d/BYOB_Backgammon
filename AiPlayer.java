package backgammon;

//
// Team Name: BYOB
// Version:
//

import java.util.ArrayList;
import java.util.PriorityQueue;

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

    private int blotEval(Board b){
    	int playerBlots=0, adversaryBlots=0, score;

    	for(int i = 0; i < Board.NUM_PIPS; i++){
    		if (b.checkers[this.getAdversaryId()][i] == 1) adversaryBlots++;
    		else if (b.checkers[this.getPlayerId()][i] == 1) playerBlots++;
    	}

    	score = adversaryBlots - playerBlots;

    	System.out.println("blotScore: " + adversaryBlots + " - " + playerBlots + " = " + score);

    	return score;
    }
    
    private float blockEval(Board b) {
    	float blocksInRow = 0;
    	float blockScore = 0;
    	boolean opponentFoundOverAll = false, opponentFoundThisTurn;
    	float weight = (float) 0.50;
    	float retVal;
    	for (int i = 1; i < 25; i++) {
    		opponentFoundThisTurn = false;
    		if (b.checkers[b.opposingPlayer(getPlayerId())][i] > 0) {
    			weight = 1;
    			opponentFoundThisTurn = true;
    		}
    		if (!opponentFoundThisTurn) {
    			if (weight == 0.5) {
    				if (blocksInRow < 3) {
    					blocksInRow += weight;
    				}
    			} else if (blocksInRow < 6){
    				blocksInRow += weight;
    			}
    		} else {
    			blockScore += blocksInRow;
    			blocksInRow = 0;
    		}
    	}
    	return blockScore;
    }

    private int runEval(Board b){
    	return 0;
    }

    private int bearOffEval(Board b){
    	return 0;
    }

    private int spacingEval(Board b){
    	return 0;
    }

    private int computeHeuristic(Board b){
    	int heuristicScore, blotScore, blockScore, runScore, bearOffScore, spacingScore;

    	blotScore = this.blotEval(b);
    	blockScore = this.blockEval(b);
    	runScore = this.runEval(b);
    	bearOffScore = this.bearOffEval(b);
    	spacingScore = this.spacingEval(b);

    	heuristicScore = blotScore + blockScore + runScore + bearOffScore + spacingScore;

    	return heuristicScore;
    }

    private int findBestBoard (ArrayList<Board> allBoardsList) {
		int bestBoard = 0, i = 0, max;
                int size = allBoardsList.size();
                int[] boardScores = new int[size];
                
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
