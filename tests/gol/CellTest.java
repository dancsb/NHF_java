package gol;

import static org.junit.Assert.*;

public class CellTest {

    private Cell testCell;

    @org.junit.Before
    public void setUp() {
        testCell = new Cell(5, 8);
    }

    @org.junit.Test
    public void getX() {
        assertEquals(5, testCell.getX());
    }

    @org.junit.Test
    public void getY() {
        assertEquals(8, testCell.getY());
    }

    @org.junit.Test
    public void compare() {
        assertTrue(testCell.compare(new Cell(5, 8)));
    }
}