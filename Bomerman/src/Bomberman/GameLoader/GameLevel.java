package Bomberman.GameLoader;

import Bomberman.Game.Timer;
import Bomberman.Level.FileLevel;

public class GameLevel extends GameStart {

    private FileLevel level = new FileLevel();
    private Timer timer = new Timer();

    @Override
    public void start() {
        level.loadLevel(LEVEL);
        timer.start();
    }
}
