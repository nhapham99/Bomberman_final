package Bomberman.Animation;

import Bomberman.Graphic.Sprite;

import java.awt.image.BufferedImage;

public class BombAnimation extends Animation {

    @Override
    public void update() {
        currentTick++;
        if (currentTick % tickPerImage == 0) {
            state += add;
            if (state == 0 || state == Sprite.bomb.length - 1) {
                add *= -1;
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        return Sprite.bomb[state];
    }
}
