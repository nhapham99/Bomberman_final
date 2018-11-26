package Bomberman.Animation;

import Bomberman.Graphic.Sprite;
import Bomberman.GameWorld.Direction;
import java.awt.image.BufferedImage;

public class ExplosionAnimation extends Animation {

    private int type;

    public ExplosionAnimation(int type) {
        this.type = type;
        tickPerImage = 6;
    }

    @Override
    public void update() {
        currentTick++;
        if (currentTick % tickPerImage == 0) {
            state += add;
        }
    }

    @Override
    public BufferedImage getImage() {
        if (state < 5) {
            switch (type) {
                case Direction.DOWN:
                case Direction.UP:
                    return Sprite.explosion_vertical[state];
                case Direction.RIGHT:
                case Direction.LEFT:
                    return Sprite.explosion_horizontal[state];
                case Direction.END_UP:
                    return Sprite.explosion_end_up[state];
                case Direction.END_DOWN:
                    return Sprite.explosion_end_down[state];
                case Direction.END_RIGHT:
                    return Sprite.explosion_end_right[state];
                case Direction.END_LEFT:
                    return Sprite.explosion_end_left[state];
                case Direction.MIDDLE:
                    return Sprite.explosion_center[state];
            }
        }
        return null;
    }
}
