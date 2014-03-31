/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */

package backgammon;

import java.io.IOException;

/**
 *
 * @author BYOB
 */
public class BgTwoPlayersTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BgTwoPlayers table = new BgTwoPlayers(1);
        
        table.game();

    }
    
}
