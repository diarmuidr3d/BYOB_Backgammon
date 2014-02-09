/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon;

/**
 *
 * @author BYOB
 */
public class BoardTest {
	public static void main(String[] args) {
		Board b = new Board();
		System.out.println("checks the display of the board");
		b.setBoard();
		b.printBoard();
		
		System.out.println("checks if blacks can move onto each other");
		b.makeMove(12,5);
		b.printBoard();
		
		System.out.println("checks whites can move onto each other");
		b.makeMove(16,18);
		b.printBoard();
		
		System.out.println("checks if whites can move onto a set of blacks(nothing should happen)");
		b.makeMove(0,5);
		b.printBoard();
		
		System.out.println("checks if blacks can move onto a set of whites(nothing should happen)");
		b.makeMove(23,18);
		b.printBoard();
		
		System.out.println("checks if whites can move onto an open pin");
		b.makeMove(0,1);
		b.printBoard();
		
		System.out.println("checks if blacks can move onto an open pin");
		b.makeMove(23,22);
		b.printBoard();
		
		System.out.println("checks if white piece can be knocked onto the bar");
		b.makeMove(5,0);
		b.printBoard();
		
		System.out.println("checks if black piece can be knocked onto the bar");
		b.makeMove(18,23);
		b.printBoard(); 
		
		System.out.println("checks if white piece can move from the bar");
		b.makeMove(Board.WHITE_BAR,21);
		b.printBoard();
		
		System.out.println("checks if black piece can move from the bar");
		b.makeMove(Board.BLACK_BAR,4);
		b.printBoard();
		
		System.out.println("checks if white piece can bear off");
		b.makeMove(18,Board.WHITE_OFF);
		b.printBoard();
		
		System.out.println("checks if black piece can bear off");
		b.makeMove(5,Board.BLACK_OFF);
		b.printBoard();
		
		System.out.println("checks if resets Board");
		b.setBoard();
		b.printBoard();
		
		int a[];
		System.out.println("checks rollDice method");
		a = b.rollDice();
		for(int i=0;i<2;i++) {
		System.out.println("Dice"+(i+1)+" "+a[i]);
		}
	}
}