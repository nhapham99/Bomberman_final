package Bomberman.Entity.Mob.Bomb;

import Bomberman.Animation.BombAnimation;
import Bomberman.Entity.Entity;
import Bomberman.Entity.Mob.Mob;
import Bomberman.Entity.Tile.Map.Explosion;
import Bomberman.GameFrame;
import Bomberman.GameWorld.MobCreation;
import Bomberman.GameWorld.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class Bomb extends Mob {

    protected List<Entity> entityIgnore = new ArrayList<>();
    protected int radius;
    protected boolean moving = false;
    protected boolean superBomb;

    protected Bomb(double x, double y, int radius, boolean superBomb) {
        super(x, y, MobCreation.BOMB_SPEED);
        this.radius = radius;
        this.superBomb = superBomb;
        GameFrame.board.players.stream().filter((player) -> (collide(player))).forEachOrdered((player) -> {
            entityIgnore.add(player);
        });
        GameFrame.board.enemies.stream().filter((enemy) -> (collide(enemy))).forEachOrdered((enemy) -> {
            entityIgnore.add(enemy);
        });
        animation = new BombAnimation();
    }

    @Override
    public void update() {
        if (getMapValue(x, y) == Direction.EXPLOSION) {
            detonate();
        }
        for (int i = 0; i < entityIgnore.size(); i++) {
            if (!entityIgnore.get(i).collide(this)) {
                entityIgnore.remove(i);
                i--;
            }
        }
        animation.update();
    }

    public boolean ignoreEntity(Entity entity) {
        return entityIgnore.contains(entity);
    }

    public void detonate() {
        remove();
        x = getMapX();
        y = getMapY();
        GameFrame.board.explosions.add(new Explosion(x, y, Direction.MIDDLE));
        if (superBomb) {
            expandFullRadiusUp();
            expandFullRadiusRight();
            expandFullRadiusDown();
            expandFullRadiusLeft();
        } else {
            expandUp();
            expandRight();
            expandDown();
            expandLeft();
        }
    }

    private void expandRight() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x + i, y);
            if (value == Direction.WALL || value == Direction.EXPLOSION_BRICK) {
                return;
            }
            if (value == Direction.BRICK) {
                setMapValue(x + i, y, Direction.EXPLOSION_BRICK);
                return;
            }
            int nextValue = getMapValue(x + i + 1, y);
            if (i == radius || nextValue == Direction.WALL || nextValue == Direction.BRICK || nextValue == Direction.EXPLOSION_BRICK) {
                GameFrame.board.explosions.add(new Explosion(x + i, y, Direction.END_RIGHT));
            } else {
                GameFrame.board.explosions.add(new Explosion(x + i, y, Direction.RIGHT));
            }
        }
    }

    private void expandDown() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x, y + i);
            if (value == Direction.WALL || value == Direction.EXPLOSION_BRICK) {
                return;
            }
            if (value == Direction.BRICK) {
                setMapValue(x, y + i, Direction.EXPLOSION_BRICK);
                return;
            }
            int nextValue = getMapValue(x, y + i + 1);
            if (i == radius || nextValue == Direction.WALL || nextValue == Direction.BRICK || nextValue == Direction.EXPLOSION_BRICK) {
                GameFrame.board.explosions.add(new Explosion(x, y + i, Direction.END_DOWN));
            } else {
                GameFrame.board.explosions.add(new Explosion(x, y + i, Direction.DOWN));
            }
        }
    }

    private void expandLeft() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x - i, y);
            if (value == Direction.WALL || value == Direction.EXPLOSION_BRICK) {
                return;
            }
            if (value == Direction.BRICK) {
                setMapValue(x - i, y, Direction.EXPLOSION_BRICK);
                return;
            }
            int nextValue = getMapValue(x - i - 1, y);
            if (i == radius || nextValue == Direction.WALL || nextValue == Direction.BRICK || nextValue == Direction.EXPLOSION_BRICK) {
                GameFrame.board.explosions.add(new Explosion(x - i, y, Direction.END_LEFT));
            } else {
                GameFrame.board.explosions.add(new Explosion(x - i, y, Direction.LEFT));
            }
        }
    }

    private void expandUp() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x, y - i);
            if (value == Direction.WALL || value == Direction.EXPLOSION_BRICK) {
                return;
            }
            if (value == Direction.BRICK) {
                setMapValue(x, y - i, Direction.EXPLOSION_BRICK);
                return;
            }
            int nextValue = getMapValue(x, y - i - 1);
            if (i == radius || nextValue == Direction.WALL || nextValue == Direction.BRICK || nextValue == Direction.EXPLOSION_BRICK) {
                GameFrame.board.explosions.add(new Explosion(x, y - i, Direction.END_UP));
            } else {
                GameFrame.board.explosions.add(new Explosion(x, y - i, Direction.UP));
            }
        }
    }

    private void expandFullRadiusRight() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x + i, y);
            if (value == Direction.WALL) {
                return;
            }
            int nextValue = getMapValue(x + i + 1, y);
            if (i == radius || nextValue == Direction.WALL) {
                GameFrame.board.explosions.add(new Explosion(x + i, y, Direction.END_RIGHT));
            } else {
                GameFrame.board.explosions.add(new Explosion(x + i, y, Direction.RIGHT));
            }
        }
    }

    private void expandFullRadiusDown() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x, y + i);
            if (value == Direction.WALL) {
                return;
            }
            int nextValue = getMapValue(x, y + i + 1);
            if (i == radius || nextValue == Direction.WALL) {
                GameFrame.board.explosions.add(new Explosion(x, y + i, Direction.END_DOWN));
            } else {
                GameFrame.board.explosions.add(new Explosion(x, y + i, Direction.DOWN));
            }
        }
    }

    private void expandFullRadiusLeft() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x - i, y);
            if (value == Direction.WALL) {
                return;
            }
            int nextValue = getMapValue(x - i - 1, y);
            if (i == radius || nextValue == Direction.WALL) {
                GameFrame.board.explosions.add(new Explosion(x - i, y, Direction.END_LEFT));
            } else {
                GameFrame.board.explosions.add(new Explosion(x - i, y, Direction.LEFT));
            }
        }
    }

    private void expandFullRadiusUp() {
        for (int i = 1; i <= radius; i++) {
            int value = getMapValue(x, y - i);
            if (value == Direction.WALL) {
                return;
            }
            int nextValue = getMapValue(x, y - i - 1);
            if (i == radius || nextValue == Direction.WALL) {
                GameFrame.board.explosions.add(new Explosion(x, y - i, Direction.END_UP));
            } else {
                GameFrame.board.explosions.add(new Explosion(x, y - i, Direction.UP));
            }
        }
    }
}
