package Bomberman.Entity.Mob.Enemy;

import Bomberman.Animation.EnemyAnimation;
import Bomberman.Entity.Mob.Mob;
import Bomberman.GameWorld.MobCreation;
import Bomberman.GameWorld.Direction;

import java.util.Random;

public abstract class Enemy extends Mob {

    public static Random random = new Random();
    protected boolean alive = true;
    protected boolean brickIgnore = false;
    protected int tickToRemove = MobCreation.TICKS_PER_SECOND;

    protected Enemy(double x, double y, double speed) {
        super(x, y, speed);
        direction = random.nextInt(4);
    }

    @Override
    public void update() {
        if (alive && ContactExplosion()) {
            gameSound.getIstance().getAudio(gameSound.ENEMY_DIE).play();
            dead();
        }
        if (!alive) {
            tickToRemove--;
            if (tickToRemove == 0) {
                remove();
            }
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void dead() {
        alive = false;
        ((EnemyAnimation) animation).dead();
    }

    protected void wander() {
        double tempX = x;
        double tempY = y;
        switch (direction) {
            case Direction.UP:
                y -= speed;
                break;
            case Direction.DOWN:
                y += speed;
                break;
            case Direction.LEFT:
                x -= speed;
                break;
            case Direction.RIGHT:
                x += speed;
                break;
        }
        if (ContactBomb() || ContactWall()) {
            x = tempX;
            y = tempY;
            Collision();
            direction = random.nextInt(4);
            return;
        }
        if (!brickIgnore && ContactBrick()) {
            x = tempX;
            y = tempY;
            Collision();
            direction = random.nextInt(4);
        }
    }
}
