package Bomberman.Entity.Mob;

import Bomberman.Animation.Animation;
import Bomberman.Entity.Entity;
import java.awt.image.BufferedImage;

public abstract class Mob extends Entity {

    protected double speed;
    protected int direction;
    protected Animation animation;

    protected Mob(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void Collision() {
        if (Math.abs(Math.round(x) - x) < speed) {
            x = Math.round(x);
        }
        if (Math.abs(Math.round(y) - y) < speed) {
            y = Math.round(y);
        }
    }

    @Override
    public BufferedImage getImage() {
        return animation.getImage();
    }
}
