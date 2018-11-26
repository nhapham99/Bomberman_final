package Bomberman.GameWorld;

public abstract class MobCreation {

    public static final int         TICKS_PER_SECOND = 60;
    public static final double      NS_PER_TICK = 1000000000.0 / TICKS_PER_SECOND;
    public static final int         LOOP_DELAY = 2;

    public static final double      PLAYER_SPEED = 0.015 * GameSize.SCALE;
    public static final double      BOMB_SPEED = 0.02 * GameSize.SCALE;

    public static final double      BALLOM_SPEED = 0.006 * GameSize.SCALE;
    public static final double      ONEAL_SPEED = 0.007 * GameSize.SCALE;
    public static final double      BOYON_SPEED = 0.006 * GameSize.SCALE;
}
