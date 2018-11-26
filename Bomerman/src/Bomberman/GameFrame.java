package Bomberman;

import Bomberman.Entity.Mob.Player.Player;
import Bomberman.GameLoader.GameManager;
import Bomberman.Graphic.Render;
import Bomberman.Input.Controller;
import Bomberman.GameWorld.GameSize;
import Bomberman.Level.FileLevel;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameFrame extends Canvas{
    public static Controller control = new Controller();
    public static JFrame frame = new JFrame();
    public static GameManager gameManager = new GameManager();
    public static Board board = new Board();
    public static BufferStrategy bufferStrategy;
    public static Graphics graphics;
    public static Render render = new Render();
    private static int state = 0;
    GameFrame() {
        frame.setLayout(new BorderLayout());
        frame.setMaximumSize(new Dimension(GameSize.SCREEN_WIDTH, GameSize.SCREEN_HEIGHT));
        frame.setMinimumSize(new Dimension(GameSize.SCREEN_WIDTH, GameSize.SCREEN_HEIGHT));
        frame.setPreferredSize(new Dimension(GameSize.SCREEN_WIDTH, GameSize.SCREEN_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bomberman");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        addKeyListener(control);
        if (getBufferStrategy() == null) {
            createBufferStrategy(3);
        }
        bufferStrategy = getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
        requestFocus();

        while (true) {
            gameManager.start();
            
        }
    }
}
