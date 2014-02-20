/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import backgammon.Point;
/**
 *
 * @author BYOB
 */
public class HumanPlayerTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
                //Testing the readMoves() method
            /*
                PrintStream psOut = null;
                PrintStream stdOut = System.out;
                InputStream stdIn = System.in;
                
                FileInputStream sIn = null;
                boolean test = true;
                HumanPlayer p = new HumanPlayer();
                int i;
                
                if (test){
                    psOut = new PrintStream("./test/outputTest.txt");
                    System.setOut(psOut);
                }
                for (i=0; i<3; i++){
                    sIn = new FileInputStream("./test/inputTest"+i+".txt");
                    System.out.println("test " + i);
                    System.setIn(sIn); 
                    int moves[][] = p.readMoves();
                    if(moves!=null)
                    for (int move[] : moves){
                        System.out.println(move[0] + " " + move[1]);
                    }
                }
                
                if(psOut != null) psOut.close();
                if (sIn != null) sIn.close();
                
                System.setOut(stdOut);
                System.setIn(stdIn);
                
                Scanner actualOut = new Scanner(new BufferedReader(new FileReader("./test/outputTest.txt")));
                Scanner correctOut = new Scanner(new BufferedReader(new FileReader("./test/outputCheck.txt")));

                String s1, s2;
                int k = 0;
                while (correctOut.hasNextLine() || actualOut.hasNextLine()){
                    k++;
                    s1 = correctOut.nextLine();
                    s2 = actualOut.nextLine();
                    if (!s1.equals(s2)) 
                        System.err.println("test failed at line " + k + " actualOutput: " + s2 + " correctOutput: " + s1);
                    else{
                        System.out.println("line OK " + k + " output: " + s1);
                    }
                }
                
                if (k == 14) System.out.println("readMoves(): Test Passed!");
                
                //end of the readMoves() method test
                */
                Board b = new Board();
                HumanPlayer p = new HumanPlayer();
                b.setBoard();
                p.playerMove(b, 'W');
    }
}
