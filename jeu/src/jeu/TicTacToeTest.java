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

	
}
