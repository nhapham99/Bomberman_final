package Bomberman;

import Bomberman.Entity.Mob.Bomb.Bomb;
import Bomberman.Entity.Mob.Enemy.Enemy;
import Bomberman.Entity.Mob.Player.Player;
import Bomberman.Entity.Tile.Map.Brick;
import Bomberman.Entity.Tile.Map.Explosion;
import Bomberman.Entity.Tile.Map.Item;
import Bomberman.Entity.Tile.Map.Portal;
import Bomberman.Level.FileLevel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    public List<Bomb> bombs = new ArrayList<>();
    public List<Brick> bricks = new ArrayList<>();
    public List<Portal> portals = new ArrayList<>();
    public List<Player> players = new ArrayList<>();
    public List<Explosion> explosions = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    public static FileLevel loader = new FileLevel();
    public int[][] map;
    private int LEVEL = 2;
    public void reset(boolean keepPlayer) {
        bombs.clear();
        bricks.clear();
        portals.clear();
        explosions.clear();
        enemies.clear();
        items.clear();
        players.clear();
        map = null;
        loader.loadLevel(LEVEL);
    }
}
