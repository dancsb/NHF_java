package gol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static gol.Main.SQUARE_SIZE;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener{
    private final Game game;

    private enum Cursor {
        create,
        destroy
    }

    private Cursor cursor;

    public void resize() {
        setPreferredSize(new Dimension((game.getSize().width * (SQUARE_SIZE + 1)) + 1, (game.getSize().height * (SQUARE_SIZE + 1)) + 1));
    }

    public GamePanel(Game game) {
        setBackground(Color.BLACK);
        this.game = game;
        resize();

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void racsKirajzol(Graphics g) {
        g.setColor(new Color(80, 80, 80));
        for(int i = 0; i <= game.getSize().width; i++)
            g.drawLine(i * (SQUARE_SIZE + 1), 0, i * (SQUARE_SIZE + 1), game.getSize().height * (SQUARE_SIZE + 1));
        for(int i = 0; i <= game.getSize().height; i++)
            g.drawLine(0, i * (SQUARE_SIZE + 1), game.getSize().width * (SQUARE_SIZE + 1), i * (SQUARE_SIZE + 1));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        racsKirajzol(g);
        g.setColor(Color.WHITE);
        for (Cell oneCell : game.getCells()) {
            g.fillRect(oneCell.getX() * (SQUARE_SIZE + 1) + 1, oneCell.getY() * (SQUARE_SIZE + 1) + 1, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    private boolean inBounds(MouseEvent e) {
        return e.getX() < (game.getSize().width * (SQUARE_SIZE + 1)) && e.getY() < (game.getSize().height * (SQUARE_SIZE + 1))
                && e.getX() >= 0 && e.getY() >= 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(inBounds(e)) {
            Cell temp = new Cell(e.getX() / (SQUARE_SIZE + 1), e.getY() / (SQUARE_SIZE + 1));
            if (game.contains(temp) == null) {
                cursor = Cursor.create;
                game.getCells().add(temp);
            } else {
                cursor = Cursor.destroy;
                game.remove(temp);
            }
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(inBounds(e)) {
            Cell temp = new Cell(e.getX() / (SQUARE_SIZE + 1), e.getY() / (SQUARE_SIZE + 1));
            if (cursor == Cursor.create)
                game.getCells().add(temp);
            else
                game.remove(temp);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
