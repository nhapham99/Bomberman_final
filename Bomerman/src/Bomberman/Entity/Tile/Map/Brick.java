package Bomberman.Entity.Tile.Map;

import Bomberman.Animation.BrickAnimation;
import Bomberman.Entity.Tile.Tile;
import Bomberman.GameWorld.MobCreation;
import Bomberman.GameWorld.Direction;
import java.awt.image.BufferedImage;

public class Brick extends Tile {

    private int tick = MobCreation.TICKS_PER_SECOND / 2;
    private boolean destroy = false;
    private BrickAnimation animation = new BrickAnimation();

    public Brick(double x, double y) {
        super(x, y);
        setMapValue(Direction.BRICK);
    }

    @Override
    public void update() {
        if (!destroy && getMapValue() == Direction.EXPLOSION_BRICK) {
            destroy();
        }
        if (destroy) {
            tick--;
            if (tick == 0) {
                remove();
                setMapValue(Direction.GRASS);
            }
        }
        animation.update();
    }

    @Override
    public BufferedImage getImage() {
        return animation.getImage();
    }

    public void destroy() {
        destroy = true;
        animation.destroy();
    }
}
