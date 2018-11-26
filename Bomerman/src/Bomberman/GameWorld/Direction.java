package Bomberman.GameWorld;

public abstract class Direction {

    public static final int         UP = 0;
    public static final int         RIGHT = 1;
    public static final int         DOWN = 2;
    public static final int         LEFT = 3;
    public static final int         PLANT = 4;

    public static final int         END_UP = -1;
    public static final int         END_RIGHT = -2;
    public static final int         END_DOWN = -3;
    public static final int         END_LEFT = -4;
    public static final int         MIDDLE = -5;

    public static final int         GRASS = 0;
    public static final int         WALL = 1;
    public static final int         BRICK = 2;
    public static final int         EXPLOSION = 3;
    public static final int         EXPLOSION_BRICK = 4;

    public static final int         BOMB_ITEM = 0;
    public static final int         FIRE_ITEM = 1;
    public static final int         SPEED_ITEM = 2;

    public static final char        BALLOM_ENEMY = '1';
    public static final char        ONEAL_ENEMY = '2';
}
