package Bomberman.Entity.Mob.Player;

import Bomberman.Animation.PlayerAnimation;
import Bomberman.Entity.Mob.Bomb.Bomb;
import Bomberman.Entity.Mob.Bomb.TimeExplosion;
import Bomberman.Entity.Mob.Mob;
import Bomberman.Entity.Tile.Map.Portal;
import Bomberman.GameFrame;
import Bomberman.GameWorld.MobCreation;
import Bomberman.GameWorld.Direction;
import java.util.ArrayList;
import java.util.List;

public class Player extends Mob {

    private boolean isAlive = true;
    private boolean isMoving = false;
    private boolean superBomb = false;
    private boolean brickIgnore = false;
    private boolean bombIgnore = false;
    private boolean portalIgnore = false;
    private int bombCapacity = 1;
    private int bombRadius = 1;
    private int plantDelay = 0;
    
    private boolean[] keys = GameFrame.control.keys;
    private List<Bomb> bombs = new ArrayList<>();

    public Player(double x, double y, double speed) {
        super(x, y, speed);
        direction = Direction.DOWN;
        animation = new PlayerAnimation(this);
    }

    @Override
    public void update() {
        if (isAlive) {
            for (int i = 0; i < bombs.size(); i++) {
                if (!GameFrame.board.bombs.contains(bombs.get(i))) {
                    bombs.remove(i);
                    i--;
                }
            }
            GameFrame.board.items.stream().filter((item) -> (collide(item))).forEachOrdered((item) -> {
                item.activate(this);
            });
            
            GameFrame.board.portals.stream().filter((portal) -> (collideWithPortal(portal))).forEachOrdered((portal) -> {
                portal.activate(this);
            });
          
            if (halfCollideWithExplosion() || collideWithEnemy()) {
//                gameSound.getIstance().getAudio(gameSound.PLAYGAME).stop();
//                gameSound.getIstance().getAudio(gameSound.BOMBER_DIE).play();
                dead();
                return;
            }
            action();
        }
        animation.update();
    }

    public void update(boolean[] keys) {
        this.keys = keys;
        update();
    }

    private void action() {
        double tempX = x;
        double tempY = y;
        isMoving = false;
        if (keys[Direction.UP]) {
            y -= speed;
            xGlide();
            direction = Direction.UP;
            isMoving = true;
        } else if (keys[Direction.RIGHT]) {
            x += speed;
            yGlide();
            direction = Direction.RIGHT;
            isMoving = true;
        } else if (keys[Direction.DOWN]) {
            y += speed;
            xGlide();
            direction = Direction.DOWN;
            isMoving = true;
        } else if (keys[Direction.LEFT]) {
            x -= speed;
            yGlide();
            direction = Direction.LEFT;
            isMoving = true;
        }
        if (keys[Direction.PLANT]) {
            plant();
        }

        if (ContactWall()) {
            x = tempX;
            y = tempY;
            Collision();
            return;
        }
        if (!bombIgnore) {
            for (Bomb bomb : GameFrame.board.bombs) {
                if (!bomb.ignoreEntity(this) && collide(bomb)) {
                    x = tempX;
                    y = tempY;
                    Collision();
                    return;
                }
            }
        }
        if (!brickIgnore && ContactBrick()) {
            x = tempX;
            y = tempY;
            Collision();
            return;
        }
        if (!portalIgnore && levelClear()){
            x = tempX;
            y = tempY;
            Collision();
        }
    }

    private void plant() {
        int value = getMapValue(x, y);
        if (plantDelay > 0 || value == Direction.BRICK || value == Direction.EXPLOSION_BRICK || bombs.size() == bombCapacity)
            return;
        int tempX = getMapX();
        int tempY = getMapY();
        for (Bomb bomb : GameFrame.board.bombs) {
            if (bomb.samePosition(tempX, tempY)) {
                return;
            }
        }
        TimeExplosion bomb = new TimeExplosion(tempX, tempY, bombRadius, superBomb);
        GameFrame.board.bombs.add(bomb);
        bombs.add(bomb);
    }

    public boolean isMoving() {
        return isMoving;
    }

    private void xGlide() {
        if (Math.abs(Math.round(x) - x) < 0.5) {
            x = Math.round(x);
        }
    }

    private void yGlide() {
        if (Math.abs(Math.round(y) - y) < 0.5) {
            y = Math.round(y);
        }
    }

    private boolean collideWithEnemy() {
        if (GameFrame.board.enemies.stream().anyMatch((enemy) -> (enemy.isAlive() && collide(enemy)))) {
            return true;
        }
        return false;
    }

    private void dead() {
        isAlive = false;
        ((PlayerAnimation) animation).dead();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean levelClear() {
        if (!GameFrame.board.enemies.isEmpty()) {
            for (Portal portal : GameFrame.board.portals) {
                if (collide(portal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void increaseBombCapacity() {
        bombCapacity++;
    }

    public void increaseBombRadius() {
        bombRadius++;
    }

    public void increaseSpeed() {
        speed += MobCreation.PLAYER_SPEED / 5;
    }

    public void enableBrickIgnore() {
        brickIgnore = true;
    }

    public void enableBombIgnore() {
        bombIgnore = true;
    }

    public void enableSuperBomb() {
        superBomb = true;
    }
}
