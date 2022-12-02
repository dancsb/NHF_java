package gol;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static gol.Main.SQUARE_SIZE;
import static org.junit.Assert.*;

public class GamePanelTest {

    private Game testGame;
    private GamePanel testGamePanel;

    @Before
    public void setUp() {
        testGame = new Game(60, 90);
        testGamePanel = new GamePanel(testGame);
    }

    @Test
    public void resize() {
        assertEquals(new Dimension((60 * (SQUARE_SIZE + 1)) + 1, (90 * (SQUARE_SIZE + 1)) + 1), testGamePanel.getPreferredSize());
        testGame.setSize(new Dimension(10, 20));
        testGamePanel.resize();
        assertEquals(new Dimension((10 * (SQUARE_SIZE + 1)) + 1, (20 * (SQUARE_SIZE + 1)) + 1), testGamePanel.getPreferredSize());
    }
}