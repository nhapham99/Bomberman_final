package Bomberman.Entity.Tile.Map;

import Bomberman.Entity.Mob.Player.Player;
import Bomberman.Entity.Tile.Tile;
import Bomberman.Graphic.Sprite;
import Bomberman.GameWorld.Direction;
import java.awt.image.BufferedImage;

public class Item extends Tile {

    private int type;

    public Item(double x, double y, int type) {
        super(x, y);
        this.type = type;
    }

    public void activate(Player player) {
        if (!isRemove()) {
            switch (type) {
                case Direction.BOMB_ITEM:
                    gameSound.getIstance().getAudio(gameSound.ITEM).play();
                    player.increaseBombCapacity();
                    break;
                case Direction.FIRE_ITEM:
                    gameSound.getIstance().getAudio(gameSound.ITEM).play();
                    player.increaseBombRadius();
                    break;
                case Direction.SPEED_ITEM:
                    gameSound.getIstance().getAudio(gameSound.ITEM).play();
                    player.increaseSpeed();
                    break;
            }
            remove();
        }
    }

    @Override
    public void update() {
        if (getMapValue(x, y) == Direction.EXPLOSION) {
            remove();
        }
    }

    @Override
    public BufferedImage getImage() {
        return Sprite.item[type];
    }
}
