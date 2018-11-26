package Bomberman.Entity.Mob.Enemy.types;

import Bomberman.Animation.EnemyAnimation;
import Bomberman.Entity.Mob.Enemy.Enemy;
import Bomberman.GameWorld.Direction;

public class Ballom extends Enemy {

    public Ballom(double x, double y, double speed) {
        super(x, y, speed);
        animation = new EnemyAnimation(Direction.BALLOM_ENEMY);
    }

    @Override
    public void update() {
        super.update();
        if (alive) {
            wander();
        }
        ((EnemyAnimation) animation).update(direction);
    }
}
