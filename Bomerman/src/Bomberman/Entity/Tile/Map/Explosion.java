package Bomberman.Entity.Tile.Map;

import Bomberman.Animation.ExplosionAnimation;
import Bomberman.Entity.Tile.Tile;
import Bomberman.GameWorld.MobCreation;
import Bomberman.GameWorld.Direction;

import java.awt.image.BufferedImage;

public class Explosion extends Tile {

    private int tick = MobCreation.TICKS_PER_SECOND / 2;
    private ExplosionAnimation animation;

    public Explosion(double x, double y, int type) {
        super(x, y);
        if (getMapValue() == Direction.BRICK) {
            setMapValue(x, y, Direction.EXPLOSION_BRICK);
        } else if (getMapValue() == Direction.GRASS){
            setMapValue(x, y, Direction.EXPLOSION);
        }
        animation = new ExplosionAnimation(type);
    }

    @Override
    public void update() {
        tick--;
        if (tick == 0) {
            setMapValue(x, y, Direction.GRASS);
            remove();
        }
        animation.update();
    }

    @Override
    public BufferedImage getImage() {
        return animation.getImage();
    }
}
