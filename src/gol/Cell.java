package gol;

import java.io.Serializable;

public record Cell(int x, int y) implements Serializable {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean compare(Cell cell) {
        return cell.getX() == getX() && cell.getY() == getY();
    }

    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
