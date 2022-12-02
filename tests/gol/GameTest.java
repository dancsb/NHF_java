package gol;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    private Game testGame;

    @org.junit.Before
    public void setUp() {
        testGame = new Game(60 ,80);
        testGame.getCells().add(new Cell(3, 7));
    }

    @Test
    public void getCells() {
        ArrayList<Cell> temp = testGame.getCells();
        assertEquals(1, temp.size());
        assertEquals(3, temp.get(0).getX());
        assertEquals(7, temp.get(0).getY());
    }

    @Test
    public void getSize() {
        assertEquals(60, testGame.getSize().width);
        assertEquals(80, testGame.getSize().height);
    }

    @Test
    public void setSize() {
        Dimension temp = new Dimension(20, 50);
        testGame.setSize(temp);
        assertEquals(20, testGame.getSize().width);
        assertEquals(50, testGame.getSize().height);
    }

    @Test
    public void contains() {
        Cell temp = new Cell(3, 7);
        assertNotNull(testGame.contains(temp));
    }

    @Test
    public void remove() {
        int temp = testGame.getCells().size();
        testGame.remove(new Cell(3, 7));
        assertEquals(temp -1, testGame.getCells().size());
    }

    @Test
    public void playPause() {
        assertTrue(testGame.playPause());
        assertFalse(testGame.playPause());
    }
}