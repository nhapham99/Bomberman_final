package Bomberman.Entity.Tile.Map;

import Bomberman.Entity.Mob.Player.Player;
import Bomberman.Entity.Tile.Tile;
import Bomberman.Graphic.Sprite;
import java.awt.image.BufferedImage;

public class Portal extends Tile {
    
    public Portal(double x, double y) {
        super(x, y);
    }
    
    public void activate(Player player) {
        if (!isRemove()) {
            remove();
        }
    }
    
    @Override
    public BufferedImage getImage() {
        return Sprite.portal;
    }

    @Override
    public void update() {
          
    }
    
}
