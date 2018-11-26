package Bomberman.Animation;

import Bomberman.Graphic.Sprite;
import Bomberman.GameWorld.Direction;
import java.awt.image.BufferedImage;

public class EnemyAnimation extends Animation {

    private int type;
    private int direction;
    private boolean alive = true;

    public EnemyAnimation(int type) {
        this.type = type;
    }

    public void update(int direction) {
        this.direction = direction;
        update();
    }

    @Override
    public void update() {
        currentTick++;
        if (currentTick % tickPerImage == 0) {
            state += add;
            if (alive) {
                if (state == 0 || state == 2) {
                    add *= -1;
                }
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        if (alive) {
            switch (type) {
                case Direction.BALLOM_ENEMY:
                    return Sprite.enemy_ballom_moving[direction / 2][state];
                case Direction.ONEAL_ENEMY:
                    return Sprite.enemy_oneal_moving[direction / 2][state];
            }
        } else {
            if (state == -1) {
                switch (type) {
                    case Direction.BALLOM_ENEMY:
                        return Sprite.enemy_ballom_dead;
                    case Direction.ONEAL_ENEMY:
                        return Sprite.enemy_oneal_dead;
                }
            } else {
                return Sprite.enemy_dead[state];
            }
        }
        return null;
    }

    public void dead() {
        alive = false;
        state = -1;
        add = 1;
        currentTick = 0;
    }
}
