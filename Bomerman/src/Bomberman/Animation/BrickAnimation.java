package Bomberman.Animation;

import Bomberman.Graphic.Sprite;
import java.awt.image.BufferedImage;

public class BrickAnimation extends Animation {

    private boolean destroy = false;

    public BrickAnimation() {
        tickPerImage = 6;
    }

    @Override
    public BufferedImage getImage() {
        if (state < 6) {
            return Sprite.destroyableObject[3][state];
        }
        return null;
    }

    @Override
    public void update() {
        if (destroy) {
            currentTick++;
            if (currentTick % tickPerImage == 0) {
                state += add;
            }
        }
    }

    public void destroy() {
        destroy = true;
        state = 1;
    }
}
