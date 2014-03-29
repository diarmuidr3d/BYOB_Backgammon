/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Stefano
 */
public class HumanPlayerTest {

    public static void readMovesTest() throws FileNotFoundException, IOException {
        //Testing the readMoves() method
        PrintStream psOut = null;
        PrintStream stdOut = System.out;
        InputStream stdIn = System.in;
        Board b = new Board();
        b.setBoard();
        b.setTurn('W');

        FileInputStream sIn = null;
        boolean test = true;
        HumanPlayer p = new HumanPlayer();
        int i;

        if (test) {
            psOut = new PrintStream(".\\test\\outputTest.txt");
            System.setOut(psOut);
        }
        for (i = 0; i < 3; i++) {
            sIn = new FileInputStream(".\\test\\inputTest" + i + ".txt");
            System.out.println("test " + i);
            System.setIn(sIn);
            int moves[][] = p.readMoves(b);
            if (moves != null) {
                for (int move[] : moves) {
                    System.out.println(move[0] + " " + move[1]);
                }
            }
        }

        if (psOut != null) {
            psOut.close();
        }
        if (sIn != null) {
            sIn.close();
        }

        System.setOut(stdOut);
        System.setIn(stdIn);

        Scanner actualOut = new Scanner(new BufferedReader(new FileReader(".\\test\\outputTest.txt")));
        Scanner correctOut = new Scanner(new BufferedReader(new FileReader(".\\test\\outputCheck.txt")));

        String s1, s2;
        int k = 0;
        while (correctOut.hasNextLine() || actualOut.hasNextLine()) {
            k++;
            s1 = correctOut.nextLine();
            s2 = actualOut.nextLine();
            if (!s1.equals(s2)) {
                System.err.println("test failed at line " + k + " actualOutput: " + s2 + " correctOutput: " + s1);
            } else {
                System.out.println("line OK " + k + " output: " + s1);
            }
        }

        if (k == 16) {
            System.out.println("readMoves(): Test Passed!");
        }

        //end of the readMoves() method test        
    }
    
    public static void playerMovesTest() throws IOException {
        /*allows you to continuely loop and make moves*/
        Board b = new Board();
        HumanPlayer p = new HumanPlayer();
        Dice d = new Dice();
        b.setBoard();
        b.setTurn('W');

        d.rollDice();
        for (int j = 0; j < 2; j++) {
            System.out.println("The " + j + " Di gave: " + d.valueAt(j));
        }
        System.out.println("\nFor Testing purposes it is easiest to test in this order:");
        System.out.println("(1)enter to many moves");
        System.out.println("(2)Try to move onto a block of the opposite colour");
        System.out.println("(3)enter the wrong syntax");
        System.out.println("(4)try to move from a empty space");
        System.out.println("(5)enter one valid and one invalid move (do 1-5 1-1)\n");
        System.out.println("(6)move 19-1");
        b.printBoard();
        p.playerMove(b, d);
        b.printBoard();
        System.out.println("(7)Don't pass turn");
        /*checks the passTurn method*/

        d.rollDice();
        for (int j = 0; j < 2; j++) {
            System.out.println("The " + j + " Di gave: " + d.valueAt(j));
        }
        System.out.println("\nIf you have followed the steps up to now please continue with steps:");
        System.out.println("checkers will move in opposite direction if it's blacks turn");
        System.out.println("(8)move 24-4 to take a white onto the bar");
        System.out.println("(9)move 6-6 to bear off with black\n");
        p.playerMove(b, d);
        b.printBoard();

        System.out.println("(10)Don't pass turn");
        
        d.rollDice();
        for (int j = 0; j < 2; j++) {
            System.out.println("The " + j + " Di gave: " + d.valueAt(j));
        }
        System.out.println("\n(11)to move from the white bar enter 25-3");
        System.out.println("(12)move 17-3 to take a black onto the bar\n");
        p.playerMove(b, d);
        b.printBoard();

        System.out.println("(13)Don't pass turn");
        
        d.rollDice();
        for (int j = 0; j < 2; j++) {
            System.out.println("The " + j + " Di gave: " + d.valueAt(j));
        }
        System.out.println("\n(14)to move from the black bar enter 25-3");
        System.out.println("(15) enter 13-2\n");
        p.playerMove(b, d);
        b.printBoard();

        System.out.println("(16)Don't pass turn");
        
        d.rollDice();
        for (int j = 0; j < 2; j++) {
            System.out.println("The " + j + " Di gave: " + d.valueAt(j));
        }
        System.out.println("\n(17)Finally move 19-6 to bear off with white and enter some other move\n");
        p.playerMove(b, d);
        b.printBoard();

        System.out.println("\nProgram now enters a loop where it keeps going turn by turn until you choose to quit");
        System.out.println("You can test out the quitGame() methos and passTurn() methods effectively here\n");
        int k = 0;
        while (k == 0) {
            
            d.rollDice();
            for (int j = 0; j < 2; j++) {
                System.out.println("The " + j + " Di gave: " + d.valueAt(j));
            }
            p.playerMove(b, d);
            b.printBoard();
            /*checks the quit game method*/
            
        }
    }
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //readMovesTest();
        playerMovesTest();
    }
}

