package gol;

import java.awt.*;
import java.util.Arrays;

public abstract class Logic {

    private enum Status {
        elo,
        halott,
        uj,
        jelolt
    }

    private static int neighboorCount(Dimension size, Status[][] cells, Cell cell) {
        int count = 0;
        for(int i = -1; i <= 1; i++)
            for(int j = -1; j <= 1; j++)
                if((i != 0 || j != 0) && (cell.getX() + i >= 0 && cell.getX() + i < size.width) && (cell.getY() + j >= 0 && cell.getY() + j < size.height) && (cells[cell.getX() + i][cell.getY() + j] == Status.elo || cells[cell.getX() + i][cell.getY() + j] == Status.jelolt))
                    count++;
        return count;
    }

    public static void step(Game game) {
        Status[][] newCells = new Status[game.getSize().width][game.getSize().height];
        for(Status[] row : newCells)
            Arrays.fill(row, Status.halott);

        for(Cell oneCell : game.getCells())
            newCells[oneCell.getX()][oneCell.getY()] = Status.elo;

        game.getCells().clear();

        for(int x = 0; x < game.getSize().width; x++)
            for(int y = 0; y < game.getSize().height; y++) {
                int n = neighboorCount(game.getSize(), newCells, new Cell(x, y));
                if(newCells[x][y] == Status.elo && (n < 2 || n > 3))
                    newCells[x][y] = Status.jelolt;
                if(newCells[x][y] == Status.halott && n == 3)
                    newCells[x][y] = Status.uj;
            }

        for(int x = 0; x < game.getSize().width; x++)
            for(int y = 0; y < game.getSize().height; y++) {
                if(newCells[x][y] == Status.uj)
                    newCells[x][y] = Status.elo;
                if(newCells[x][y] == Status.jelolt)
                    newCells[x][y] = Status.halott;
                if(newCells[x][y] == Status.elo)
                    game.getCells().add(new Cell(x, y));
            }
    }

}
