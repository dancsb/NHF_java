package gol;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private final ArrayList<Cell> cells;
    private Dimension size;
    private boolean playState;

    public Game(int width, int height) {
        cells = new ArrayList<>();
        size = new Dimension(width, height);
        playState = false;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        for(int x = 0; x < this.size.width; x++)
            for(int y = 0; y < this.size.height; y++) {
                if(!(x < size.width && y < size.height)) {
                    Cell temp = new Cell(x, y);
                    if (contains(temp) != null)
                        remove(temp);
                }
            }
        this.size = size;
    }

    public Cell contains(Cell cell) {
        for(Cell oneCell : cells)
            if(cell.compare(oneCell))
                return oneCell;
        return null;
    }

    public void remove(Cell cell) {
        cells.removeIf(cell::compare);
    }

    public boolean playPause() {
        playState = ! playState;
        return playState;
    }

    public void load(Game game) {
        cells.clear();
        cells.addAll(game.getCells());
        size = game.size;
        playState = false;
    }
}
