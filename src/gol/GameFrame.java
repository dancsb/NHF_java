package gol;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameFrame extends JFrame  {

    public GameFrame(Game game) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        GamePanel panel = new GamePanel(game);
        JPanel gombokPanel = new JPanel();
        gombokPanel.setBackground(Color.BLACK);
        gombokPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Insets insets1 = new Insets(20, 10, 20, 10);
        Insets insets2 = new Insets(20, 10, 2, 10);
        Insets insets3 = new Insets(2, 10, 2, 10);
        Insets insets4 = new Insets(2, 10, 20, 10);

        JLabel speedLabel = new JLabel("Speed");
        speedLabel.setForeground(Color.WHITE);
        String[] speedOptions = {"1", "2", "3", "4" ,"5", "8", "10", "20", "100", "144"};
        Arrays.setAll(speedOptions, i -> speedOptions[i].concat(" steps/sec"));
        int[] speedValues = {1, 2, 3 ,4, 5, 8, 10, 20, 100, 144};
        JComboBox<String> speed = new JComboBox<>(speedOptions);
        speed.setSelectedIndex(6);

        JPanel widthPanel = new JPanel();
        widthPanel.setBackground(Color.BLACK);
        widthPanel.setLayout(new GridLayout());
        JLabel widthLabel = new JLabel("Width:   ");
        widthLabel.setForeground(Color.WHITE);
        JTextField widthField = new JTextField();
        widthField.setText(String.valueOf(game.getSize().width));
        widthPanel.add(widthLabel);
        widthPanel.add(widthField);

        JPanel heightPanel = new JPanel();
        heightPanel.setBackground(Color.BLACK);
        heightPanel.setLayout(new GridLayout());
        JLabel heightLabel = new JLabel("Height:   ");
        heightLabel.setForeground(Color.WHITE);
        JTextField heightField = new JTextField();
        heightField.setText(String.valueOf(game.getSize().height));
        heightPanel.add(heightLabel);
        heightPanel.add(heightField);

        JTextField fileName = new JTextField();

        JButton play = new JButton("Play");
        play.setBackground(Color.GREEN);
        JButton resize = new JButton("Resize");
        resize.setBackground(new Color(255, 127, 0));
        JButton imports = new JButton("Import");
        imports.setBackground(Color.BLUE);
        JButton export = new JButton("Export");
        export.setBackground(Color.RED);
        JButton clear = new JButton("Clear");
        clear.setBackground(Color.YELLOW);
        JButton quit = new JButton("Quit");
        quit.setBackground(Color.RED);

        Timer timer = new Timer(100, e -> {
            Logic.step(game);
            panel.repaint();
        });

        play.addActionListener(e -> {
            boolean b = game.playPause();
            play.setBackground(b ? Color.RED : Color.GREEN);
            play.setText(b ? "Pause" : "Play");
            if (b)
                timer.start();
            else
                timer.stop();
        });

        speed.addActionListener(e -> timer.setDelay(1000 / speedValues[speed.getSelectedIndex()]));

        resize.addActionListener(e -> {
            game.setSize(new Dimension(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText())));
            panel.resize();
            pack();
        });

        imports.addActionListener(e -> {
            Main.load(game, fileName.getText().concat(".game"));
            panel.resize();
            panel.repaint();
            widthField.setText(String.valueOf(game.getSize().width));
            heightField.setText(String.valueOf(game.getSize().height));
            pack();
        });

        export.addActionListener(e -> Main.save(game, fileName.getText().concat(".game")));

        clear.addActionListener(e -> {
            game.getCells().clear();
            panel.repaint();
        });

        quit.addActionListener(e -> System.exit(0));

        gbc.insets = insets1;
        gombokPanel.add(play, gbc);
        gbc.insets = insets2;
        gombokPanel.add(speedLabel, gbc);
        gbc.insets = insets4;
        gombokPanel.add(speed, gbc);
        gbc.insets = insets2;
        gombokPanel.add(widthPanel, gbc);
        gbc.insets = insets3;
        gombokPanel.add(heightPanel, gbc);
        gbc.insets = insets4;
        gombokPanel.add(resize, gbc);
        gbc.insets = insets2;
        gombokPanel.add(imports, gbc);
        gbc.insets = insets3;
        gombokPanel.add(export, gbc);
        gbc.insets = insets4;
        gombokPanel.add(fileName, gbc);
        gbc.insets = insets1;
        gombokPanel.add(clear, gbc);
        gombokPanel.add(quit, gbc);

        add(panel, BorderLayout.CENTER);
        add(gombokPanel, BorderLayout.EAST);
        pack();
    }
}
