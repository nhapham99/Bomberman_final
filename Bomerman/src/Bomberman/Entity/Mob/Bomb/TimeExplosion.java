package Bomberman.Entity.Mob.Bomb;

import Bomberman.GameWorld.MobCreation;

public class TimeExplosion extends Bomb {

    private int tickToDetonate;

    public TimeExplosion(double x, double y, int radius, boolean superBomb) {
        super(x, y, radius, superBomb);
        tickToDetonate = MobCreation.TICKS_PER_SECOND * 2;
    }

    @Override
    public void update() {
        super.update();
        tickToDetonate--;
        if (tickToDetonate == 0) {
            detonate();
        }
    }
}
