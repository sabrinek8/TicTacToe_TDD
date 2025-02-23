package jeu;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeTest {
	private TicTacToe game;

    @Before
   public void setUp() {
        game = new TicTacToe();
    }
    @Test
    public void testInvalidCoordinates() {
        try {
            game.play(-1, -1); 
        } catch (RuntimeException ex) {
            assertEquals("Coordonnées hors limite!", ex.getMessage());
        }
    }

    @Test
    public void testCellAlreadyOccupied() {
    	try {
    		game.play(0, 0);
    		game.play(0,0);
    	}
		catch(RuntimeException exception)
    	{
		assertEquals("Case déjà occupée",exception.getMessage());
    	}
	}
    
    @Test
    public void testPlayerAlternation() {
        assertEquals('X', game.getCurrentPlayer());
        game.play(0, 0);
        assertEquals('O', game.getCurrentPlayer());
        game.play(0, 1);
        assertEquals('X', game.getCurrentPlayer());
            
    }
    
    @Test
    public void  testHorizontalWin() {
    	game.play(0, 0);//X
    	game.play(0, 1);//O
    	game.play(1, 0);//X
    	game.play(0, 2);//O
    	game.play(2, 0);//X
    }
	
}
