package Bomberman.Game;

import Bomberman.Entity.Mob.Player.Player;
import Bomberman.GameFrame;
import Bomberman.GameWorld.MobCreation;

public class Timer extends Game {
    
    Player player;
    
    public Timer() {
        running = true;
    }
    
    @Override
    public void start() {
        GameFrame.render.redrawBackground();
        long now = System.nanoTime();
        long last = System.currentTimeMillis();
        double delta = 0;

        int tickCount = 0;
        int frameCount = 0;
        
//        gameSound.getIstance().getAudio(gameSound.PLAYGAME).loop();
        
        while (running) {
            delta -= (now - (now = System.nanoTime())) / MobCreation.NS_PER_TICK;
            while (delta > 1) {
                delta -= 1;
                update();
                tickCount++;
            }

            GameFrame.render.render(0);
            
            if(GameFrame.board.enemies.isEmpty()&&GameFrame.board.portals.isEmpty()){
                GameFrame.board.reset(true);
            }
              
            
            frameCount++;
            if (System.currentTimeMillis() - last >= 1000) {
                last += 1000;
                tickCount = 0;
                frameCount = 0;
            }
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < GameFrame.board.explosions.size(); i++) {
            GameFrame.board.explosions.get(i).update();
            if (GameFrame.board.explosions.get(i).isRemove()) {
                GameFrame.board.explosions.remove(i);
                i--;
            }
        }
        for (int i = 0; i < GameFrame.board.bombs.size(); i++) {
            GameFrame.board.bombs.get(i).update();
            if (GameFrame.board.bombs.get(i).isRemove()) {
                GameFrame.board.bombs.remove(i);
                i--;
            }
        }
        for (int i = 0; i < GameFrame.board.bricks.size(); i++) {
            GameFrame.board.bricks.get(i).update();
            if (GameFrame.board.bricks.get(i).isRemove()) {
                GameFrame.board.bricks.remove(i);
                i--;
            }
        }
        for (int i = 0; i < GameFrame.board.items.size(); i++) {
            GameFrame.board.items.get(i).update();
            if (GameFrame.board.items.get(i).isRemove()) {
                GameFrame.board.items.remove(i);
                i--;
            }
        }
        for (int i = 0; i < GameFrame.board.enemies.size(); i++) {
            GameFrame.board.enemies.get(i).update();
            if (GameFrame.board.enemies.get(i).isRemove()) {
                GameFrame.board.enemies.remove(i);
                i--;
            }
        }
        for (int i = 0; i < GameFrame.board.portals.size(); i++) {
            GameFrame.board.portals.get(i).update();
            if (GameFrame.board.portals.get(i).isRemove()) {
                GameFrame.board.portals.remove(i);
                i--;
            }
        }
        GameFrame.board.players.forEach((player) -> {
            player.update();
        });
    }
}
