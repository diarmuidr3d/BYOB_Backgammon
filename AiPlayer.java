

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
	 * maximises the score for a board where the AIPlayer has got more hitters than blots
	 * and the adversary has got more blots than hitters.
	 * @param b the board state to be evaluated
	 * @return 
	 */
	private float blotEval(Board b){
		int playerBlots=0, adversaryBlots=0, playerHitters = 0, adversaryHitters = 0;
		float score, A, P;
		int adversary = this.getAdversaryId();
		int player = this.getPlayerId();
		for(int i = 1; i < Board.NUM_PIPS-1; i++){
			if (b.checkers[adversary][i] == 1) adversaryBlots++;
			else if (b.checkers[player][i] == 1) playerBlots++;
			if (b.checkers[adversary][i] > 2) adversaryHitters++;
			else if (b.checkers[player][i] > 2) playerHitters++;
		}
		P = playerHitters - playerBlots; // this is > 0 only if playerHitters > playerBlots
		A = adversaryBlots - adversaryHitters; // this is > 0 only if adversaryBlots > aversaryHitters
		/* // alternative to be tested:
            P = playerHitters - adversaryHitters; // this is > 0 only if playerHitters > adversaryHitters
            A = adversaryBlots - playerBlots;     // this is > 0 only if adversaryBlots > playerBots
		 */
		// try with different percentages
		score = (float) ( 0.5 * A +  0.5 * P );
		//System.out.println("blotScore: " + adversaryBlots + " - " + playerBlots + " = " + score);
		return score;
	}

	private float blockEval(Board b) {
		int playerHome = 0, adversaryHome = 0;
		int player75 = 0, adversary75 = 0;
		int player50 = 0, adversary50 = 0;
		int player25 = 0, adversary25 = 0;
		float score, P, A;
		int player = this.getPlayerId();
		int adversary = this.getAdversaryId();
		if ( player == Board.O_PLAYER_ID){
			for(int i = 19; i < 25; i++){
				if (b.checkers[player][i] == 0) playerHome = 0;
                                else if (b.checkers[player][i] >= 2) playerHome++;
				if (b.checkers[adversary][i] == 0)adversary25 = 0;
                                else if (b.checkers[adversary][i] >= 2)adversary25++;

			}
			for (int i = 13; i < 19; i++){
				if (b.checkers[player][i] == 0) player75 = 0;
                                else if (b.checkers[player][i] >= 2) player75++;
				if (b.checkers[adversary][i] == 0)adversary50 = 0;
                                else if (b.checkers[adversary][i] >= 2)adversary50++;
			}
			for(int i = 7; i < 13; i++){
				if (b.checkers[player][i] == 0) player50 = 0;
                                else if (b.checkers[player][i] >= 2) player50++;
				if (b.checkers[adversary][i] == 0)adversary75 = 0;
                                else if (b.checkers[adversary][i] >= 2)adversary75++;
			}
			for(int i = 1; i < 7; i++){
				if (b.checkers[player][i] == 0) player25= 0;
                                else if (b.checkers[player][i] >= 2) player25++;
				if (b.checkers[adversary][i] == 0)adversaryHome = 0;
                                else if (b.checkers[adversary][i] >= 2)adversaryHome++;
			}
		}
		else if (this.getPlayerId() == Board.X_PLAYER_ID){
			for(int i = 19; i < 25; i++){
				if (b.checkers[player][i] == 0) player25 = 0;
                                else if (b.checkers[player][i] >= 2) player25++;
				if (b.checkers[adversary][i] == 0)adversaryHome = 0;
                                else if (b.checkers[adversary][i] >= 2)adversaryHome++;
			}
			for (int i = 13; i < 19; i++){
				if (b.checkers[player][i] == 0) player50 = 0;
                                else if (b.checkers[player][i] >= 2) player50++;
				if (b.checkers[adversary][i] == 0)adversary75 = 0;
                                else if (b.checkers[adversary][i] >= 2)adversary75++;
			}
			for(int i = 7; i < 13; i++){
				if (b.checkers[player][i] == 0) player75 = 0;
                                else if (b.checkers[player][i] >= 2) player75++;
				if (b.checkers[adversary][i] == 0)adversary50 = 0;
                                else if (b.checkers[adversary][i] >= 2)adversary50++;
			}
			for(int i = 1; i < 7; i++){
				if (b.checkers[player][i] == 0) playerHome = 0;
                                else if (b.checkers[player][i] >= 2) playerHome++;
				if (b.checkers[adversary][i] == 0)adversary25 = 0;
                                else if (b.checkers[adversary][i] >= 2)adversary25++;
			}
		}
		P = (float) playerHome + player75 + player50 + player25;
		A = (float) adversaryHome + adversary75 + adversary50 + adversary25;
		score = P - A;
		//System.out.println("blockEval" + score);

		return score;
	}

	private float runEval(Board b){
		int myScore = 0;
		int opposeScore = 0;
		for (int i = 1; i < 26; i++) {
			if (b.checkers[getPlayerId()][i] > 0) {
				myScore += i;
			}
			if (b.checkers[b.opposingPlayer(getPlayerId())][i] > 0) {
				opposeScore += i;
			}
		}
		return (opposeScore/325) - (myScore/325);
	}

	/**
	 * This function evaluates the number of checkers in each part of the board and
	 * finds the board with the highest probability of bearing of for the AiPlayer.
	 * @param b
	 * @return 
	 */      
	private float bearOffEval(Board b){
		int playerHome = 0, adversaryHome = 0;
		int player75 = 0, adversary75 = 0;
		int player50 = 0, adversary50 = 0;
		int player25 = 0, adversary25 = 0;
		float score, P, A;
		int player = this.getPlayerId();
		int adversary = this.getAdversaryId();
		if ( player == Board.O_PLAYER_ID){
			for(int i = 19; i < 25; i++){
				playerHome+=b.checkers[player][i];
				adversary25+=b.checkers[adversary][i];
			}
			for (int i = 13; i < 19; i++){
				player75+=b.checkers[player][i];
				adversary50+=b.checkers[adversary][i];
			}
			for(int i = 7; i < 13; i++){
				player50+=b.checkers[player][i];
				adversary75+=b.checkers[adversary][i];
			}
			for(int i = 1; i < 7; i++){
				player25+=b.checkers[player][i];
				adversaryHome+=b.checkers[adversary][i];
			}
		}
		else if (this.getPlayerId() == Board.X_PLAYER_ID){
			for(int i = 19; i < 25; i++){
				player25+=b.checkers[player][i];
				adversaryHome+=b.checkers[adversary][i];
			}
			for (int i = 13; i < 19; i++){
				player50+=b.checkers[player][i];
				adversary75+=b.checkers[adversary][i];
			}
			for(int i = 7; i < 13; i++){
				player75+=b.checkers[player][i];
				adversary50+=b.checkers[adversary][i];
			}
			for(int i = 1; i < 7; i++){
				playerHome+=b.checkers[player][i];
				adversary25+=b.checkers[adversary][i];
			}
		}      
		P = (float) (0.75*playerHome + 0.5*player75 + 0.25*player50 + 0.0*player25) + b.checkers[player][0];
		A = (float) (0.75*adversaryHome + 0.5*adversary75 + 0.25*adversary50 + 0.0*adversary25) + b.checkers[adversary][0];
		score = P - A;
		//System.out.println("bearOff" + score);
		return score;
	}

	private float spacingEval(Board b){
		return 0;
	}
	
	/**
	 * This function maximises the score for the boards where the AiPlayer has less checkers 
	 * on the bar and the adversary has more checkers.
	 * @param b
	 * @return 
	 */        
	private float barEval(Board b){
		return (b.checkers[this.getAdversaryId()][25] - b.checkers[this.getPlayerId()][25]);
	}
	
	/**
	 * This function computes the overall heuristic score, taking the bar into account.
	 * @param b
	 * @return 
	 */ 
	private float computeHeuristic(Board b){
		float heuristicScore, blotScore, blockScore, runScore, bearOffScore, spacingScore, barScore;
		float[] w = {1,1,1,1,0,1};
		blotScore = this.blotEval(b);
		blockScore = this.blockEval(b);
		runScore = this.runEval(b);
		bearOffScore = this.bearOffEval(b);
		spacingScore = this.spacingEval(b);
		barScore = this.barEval(b);
		/* sum of all the evaluation functions*/
		heuristicScore = w[0]*blotScore + w[1]*blockScore + w[2]*runScore + w[3]*bearOffScore + w[4]*spacingScore + w[5]*barScore;

		return heuristicScore;
	}

	private int findBestBoard (ArrayList<Board> allBoardsList) {
		int bestBoard = 0, i = 0;
		int size = allBoardsList.size();
		float max;
		float[] boardScores = new float[size];

		for( Board b : allBoardsList ){
			boardScores[i] = this.computeHeuristic(b);
			i++;
		}

		max = boardScores[0];
		for (i=0; i < size; i++){
			if (boardScores[i] > max){
				//System.out.println(i + " " + (boardScores[i]>max));
				max = boardScores[i];
				bestBoard = i;
			}
		}

		//System.out.println(boardScores[bestBoard]);

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

