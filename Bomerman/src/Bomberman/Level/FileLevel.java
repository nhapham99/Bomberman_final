package Bomberman.Level;

import Bomberman.Entity.Mob.Enemy.types.Ballom;
import Bomberman.Entity.Mob.Player.Player;
import Bomberman.Entity.Tile.Map.Brick;
import Bomberman.Entity.Tile.Map.Item;
import Bomberman.GameWorld.MobCreation;
import Bomberman.GameWorld.Direction;
import java.util.List;
import Bomberman.Entity.Tile.Map.Portal;
import Bomberman.GameFrame;

public class FileLevel {

    public int currentLevel = 1;

    public void loadLevel(int level) {
        currentLevel = level;
        String path = "C:\\Users\\NhaPham\\Desktop\\Bomerman\\res\\Level\\";
        
        path += "Level" + Integer.toString(currentLevel) + ".txt";
        
        List<String> list = LevelLoader.load(path);
        
        //GameFrame.board.reset(false);
        GameFrame.board.map = new int[list.size()][list.get(0).length()];
        for (int y = 0; y < list.size(); y++) {
            for (int x = 0; x < list.get(y).length(); x++) {
                GameFrame.board.map[y][x] = Direction.GRASS;
                switch (list.get(y).charAt(x)) {
                    case '*':
                        GameFrame.board.bricks.add(new Brick(x, y));
                        break;
                    case 'p':
                        GameFrame.board.players.add(new Player(x, y, MobCreation.PLAYER_SPEED));
                        break;
                    case '#':
                        GameFrame.board.map[y][x] = Direction.WALL;
                        break;
                    case 'b':
                        GameFrame.board.items.add(new Item(x, y, Direction.BOMB_ITEM));
                        GameFrame.board.bricks.add(new Brick(x, y));
                        break;
                    case 'f':
                        GameFrame.board.items.add(new Item(x, y, Direction.FIRE_ITEM));
                        GameFrame.board.bricks.add(new Brick(x, y));
                        break;
                    case 's':
                        GameFrame.board.items.add(new Item(x, y, Direction.SPEED_ITEM));
                        GameFrame.board.bricks.add(new Brick(x, y));
                        break;
                    case 'x':
                        GameFrame.board.portals.add(new Portal(x, y));
                        GameFrame.board.bricks.add(new Brick(x, y));
                        break;
                    case Direction.BALLOM_ENEMY:
                        GameFrame.board.enemies.add(new Ballom(x, y, MobCreation.BALLOM_SPEED));
                        break;
                }
            }
        }
    }
}
