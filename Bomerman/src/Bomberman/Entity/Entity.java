package Bomberman.Entity;

import Bomberman.Entity.Tile.Map.Brick;
import Bomberman.GameFrame;
import Bomberman.GameWorld.GameSize;
import Bomberman.GameWorld.Direction;
import gameSound.GameSound;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Entity implements Serializable {

    protected double x;
    protected double y;
    protected GameSound gameSound;
    private boolean remove;

    protected Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();
    public abstract BufferedImage getImage();

    //getter
    public int getMapValue(double x, double y) {
        return GameFrame.board.map[(int) Math.round(y)][(int) Math.round(x)];
    }

    public int getMapValue() {
        return GameFrame.board.map[(int) Math.round(y)][(int) Math.round(x)];
    }

    public int getScreenX() {
        return (int) (x * GameSize.RTILE_SIZE);
    }

    public int getScreenY() {
        return (int) (y * GameSize.RTILE_SIZE);
    }

    public int getMapX() {
        return (int) Math.round(x);
    }

    public int getMapY() {
        return (int) Math.round(y);
    }

    //setter
    public void setMapValue(double x, double y, int value) {
        GameFrame.board.map[(int) Math.round(y)][(int) Math.round(x)] = value;
    }

    public void setMapValue(int value) {
        GameFrame.board.map[(int) Math.round(y)][(int) Math.round(x)] = value;
    }

    public void remove() {
        remove = true;
    }

    //kiểm tra va chạm với vật thể khác
    public boolean collide(Entity entity) {
        return (Math.abs(this.x - entity.x) < 1 && Math.abs(this.y - entity.y) < 1);
    }
    
    //Kiểm tra va chạm với portal để chuyển map
    public boolean collideWithPortal(Entity entity) {
        return (Math.abs(this.x - entity.x) < 0.3 && Math.abs(this.y - entity.y) < 0.3);
    }

    public boolean collide(double x, double y) {
        return (Math.abs(this.x - x) < 1 && Math.abs(this.y - y) < 1);
    }

    public boolean samePosition(Entity entity) {
        return (this.x == entity.x && this.y == entity.y);
    }
    public boolean samePosition(double x, double y) {
        return (this.x == x && this.y == y);
    }

    public double distance(Entity entity) {
        return (entity.x - x) * (entity.x - x) + (entity.y - y) * (entity.y - y);
    }

    //Kiểm tra va chạm với bom
    public boolean ContactBomb() {
        if (GameFrame.board.bombs.stream().anyMatch((bomb) -> (!bomb.ignoreEntity(this) && collide(bomb)))) {
            return true;
        }
        return false;
    }


    //Kiểm tra va chạm vật thể có size 1
    public boolean ContactExplosion() {
        int width = 1;
        int height = 1;
        if (x != (int)x) {
            width++;
        }
        if (y != (int)y) {
            height++;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = getMapValue((int) x + j, (int) y + i);
                if (value == Direction.EXPLOSION || value == Direction.EXPLOSION_BRICK) {
                    return true;
                }
            }
        }
        return false;
    }


    //Kiểm tra va chạm vật thể có size 1 nhưng chỉ xét va chạm kích thước 0.4
    public boolean halfCollideWithExplosion() {
        int width = 1;
        int height = 1;
        double tempX = x + 0.3;
        double tempY = y + 0.3;
        if ((int)tempX + 0.4 < tempX) {
            width++;
        }
        if ((int)tempY + 0.4 < tempY) {
            height++;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = getMapValue((int) tempX + j, (int) tempY + i);
                if (value == Direction.EXPLOSION || value == Direction.EXPLOSION_BRICK) {
                    return true;
                }
            }
        }
        return false;
    }    
    
    //kiểm tra va chạm với gạch
    public boolean ContactBrick() {
        for (Brick brick : GameFrame.board.bricks) {
            if (collide(brick)) {
                return true;
            }
        }
        return false;
    }
 

    //kiểm tra va chạm với tường
    public boolean ContactWall() {
        int width = 1;
        int height = 1;
        if (x != (int)x) {
            width++;
        }
        if (y != (int)y) {
            height++;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = getMapValue((int) x + j, (int) y + i);
                if (value == Direction.WALL) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRemove() {
        return remove;
    }
}
