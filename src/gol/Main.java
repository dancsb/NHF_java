package gol;

import java.io.*;

public class Main {

    public static int SQUARE_SIZE = 10;

    public static void main(String[] args) {
        Game game = new Game(60, 60);
        GameFrame frame = new GameFrame(game);
        frame.setVisible(true);
    }

    public static void save(Game game, String fileName) {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(Game game, String fileName) {
        try {
            FileInputStream f = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(f);
            game.load((Game) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
