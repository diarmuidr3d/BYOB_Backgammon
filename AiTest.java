package backgammon;


public class AiTest {
	private static final int NUMBER_OF_GAMES = 1;
	private static final int RANDOM_ID = Board.X_PLAYER_ID;
	private static final int AI_ID = Board.O_PLAYER_ID;

	public static void main (String[] args) {
		float random_wins = 0;
		float ai_wins = 0;
		float percentage_win_Random = 0;
		float percentage_win_Ai = 0;
		float maxPercentageWinAi = 0;
		float[] bestConfiguration = new float[6];
		System.out.println("Games Started");
		for (float a=(float) 0.1; a < 10; a = a+a) {
			for (float b=(float) 0.1; b < 10; b = b+b) {
				for (float c=(float) 0.1; c < 10; c = c+c) {
					for (float d=(float) 0.1; d < 10; d = d+d) {
						for (float e=(float) 0.1; e < 10; e = e+e) {
							for (float f=(float) 0.1; f < 10; f = f+f) {
								for(int i=0; i<NUMBER_OF_GAMES;i++){
									Board gameBoard = new Board();
									Dice gameDice = new Dice();
									Play currentPlay = new Play();
									RandomPlayer random = new RandomPlayer(RANDOM_ID, gameBoard, gameDice);
									AiPlayer bot = new AiPlayer(AI_ID, gameBoard, gameDice);
									int winner;	
									boolean finished = false, firstMove = true;
									do {
										winner = gameDice.rollDieEach();
										//gameDice.displayDieEach();
									} while (winner == Dice.DRAW);

									do {
										/*if (firstMove) {
											gameBoard.displayBoard(RANDOM_ID);
										}*/
										float[] wb = {a,b,c,d,e,f};
										if ( (!firstMove) || (firstMove && (winner == AI_ID)) ) {
											gameDice.rollDice();
											//gameDice.displayDice(AI_ID);
											currentPlay = bot.getPlay(wb);
											//currentPlay.displayPlay(AI_ID);
											finished = gameBoard.doPlay(AI_ID, currentPlay);
											//gameBoard.displayBoard(RANDOM_ID);
										}
										if (!finished) {
											gameDice.rollDice();
											//gameDice.displayDice(RANDOM_ID);
											currentPlay = random.getPlay();
											//currentPlay.displayPlay(RANDOM_ID);
											finished = gameBoard.doPlay(RANDOM_ID, currentPlay);
											//gameBoard.displayBoard(AI_ID);
										}
										firstMove = false;
									} while (!finished);

									if(gameBoard.returnWinner()==0){
										random_wins++;

									}
									else{
										ai_wins++;
									}
									//System.out.println(random_wins + " " + ai_wins);
									//gameBoard.displayResult();
									//System.out.println("Game Complete");
								}
								percentage_win_Random = (random_wins/NUMBER_OF_GAMES)*100;
								percentage_win_Ai = (ai_wins/NUMBER_OF_GAMES)*100;
								System.out.println("The Random Player won "+percentage_win_Random+"% of the games\n");
								System.out.println("The Ai Player won "+percentage_win_Ai+"% of the games\n");
								if (percentage_win_Ai > maxPercentageWinAi) {
									maxPercentageWinAi = percentage_win_Ai;
									bestConfiguration[0] = a;
									bestConfiguration[1] = b;
									bestConfiguration[2] = c;
									bestConfiguration[3] = d;
									bestConfiguration[4] = e;
									bestConfiguration[5] = f;
								}
							}}}}}}
		System.out.println("Best configuration: "+bestConfiguration[0]+", "+bestConfiguration[1]+", "+bestConfiguration[2]+", "+bestConfiguration[3]+", "+bestConfiguration[4]+", "+bestConfiguration[5]);
	}
}

