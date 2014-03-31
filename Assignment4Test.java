/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */
package backgammon;

/**
 *
 * @author BYOB
 */
public class Assignment4Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board();
        b.setBoard();
        
        Board newBoard = b.copy();
        
        b.makeMove(0, 4);
        newBoard.makeMove(23, 3);
        
        b.printBoard();
        newBoard.printBoard();
    }
    
}
