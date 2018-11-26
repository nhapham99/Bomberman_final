package Bomberman.Game;

import Bomberman.GameFrame;
import gameSound.GameSound;

public abstract class Game{

    protected boolean running;
    
    protected GameSound gameSound;

    public abstract void start();
    public abstract void update();

    public boolean checkLose() {
        if (!GameFrame.board.players.stream().noneMatch((player) -> (player.isAlive()))) {
            return false;
        }
        return true;
    }
}
