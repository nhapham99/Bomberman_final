package Bomberman.Graphic;

import Bomberman.GameFrame;
import Bomberman.GameWorld.GameSize;
import Bomberman.GameWorld.Direction;
import java.awt.*;
import java.awt.image.BufferedImage;
import static Bomberman.GameFrame.graphics;

public class Render {

    protected int xOffset;
    protected int yOffset;
    protected BufferedImage background;
    
    public void render(int mainCharacter) {
        calculateOffset(mainCharacter);
        //draw background
        graphics.drawImage(background, -xOffset, -yOffset, null);
        //draw item
        GameFrame.board.items.forEach((temp) -> {
            graphics.drawImage(temp.getImage(),
                    temp.getScreenX() - xOffset,
                    temp.getScreenY() - yOffset,
                    GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
        });
        //draw portal
        GameFrame.board.portals.forEach((temp) -> {
            graphics.drawImage(temp.getImage(),
                    temp.getScreenX() - xOffset,
                    temp.getScreenY() - yOffset,
                    GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
        });
        //draw brick
        GameFrame.board.bricks.forEach((temp) -> {
            graphics.drawImage(temp.getImage(),
                    temp.getScreenX() - xOffset,
                    temp.getScreenY() - yOffset,
                    GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
        });
        //draw bomb
        GameFrame.board.bombs.forEach((temp) -> {
            graphics.drawImage(temp.getImage(),
                    temp.getScreenX() - xOffset,
                    temp.getScreenY() - yOffset,
                    GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
        });
        //draw explosion
        GameFrame.board.explosions.forEach((temp) -> {
            graphics.drawImage(temp.getImage(),
                    temp.getScreenX() - xOffset,
                    temp.getScreenY() - yOffset,
                    GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
        });

        //draw enemy
        GameFrame.board.enemies.forEach((temp) -> {
            graphics.drawImage(temp.getImage(),
                    temp.getScreenX() - xOffset,
                    temp.getScreenY() - yOffset,
                    GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
        });
        //draw player
        GameFrame.board.players.forEach((temp) -> {
            BufferedImage pImage = temp.getImage();
            if (!(pImage == null)) {
                int imageWidth = GameSize.SCALE * pImage.getWidth();
                int imageHeight = GameSize.SCALE * pImage.getHeight();
                graphics.drawImage(pImage,
                        temp.getScreenX() - (imageWidth - GameSize.RTILE_SIZE) / 2 - xOffset,
                        temp.getScreenY() - imageHeight + GameSize.RTILE_SIZE - yOffset,
                        imageWidth, imageHeight, null);
            }
        });
        GameFrame.bufferStrategy.show();
    }

    public void redrawBackground() {
        background = new BufferedImage(GameFrame.board.map[0].length * GameSize.RTILE_SIZE, GameFrame.board.map.length * GameSize.RTILE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics g = background.getGraphics();
        for (int y = 0; y < GameFrame.board.map.length; y++) {
            for (int x = 0; x < GameFrame.board.map[0].length; x++) {
                if (GameFrame.board.map[y][x] == Direction.WALL) {
                    g.drawImage(Sprite.undestroyableObject[0], x * GameSize.RTILE_SIZE, y * GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
                } else {
                    g.drawImage(Sprite.grass, x * GameSize.RTILE_SIZE, y * GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, GameSize.RTILE_SIZE, null);
                }
            }
        }
    }
    
    public void calculateOffset(int mainCharacter) {
        if (GameFrame.board.players.isEmpty()) {
            return;
        }
        int playerX = GameFrame.board.players.get(mainCharacter).getScreenX() + GameSize.RTILE_SIZE / 2;
        if (playerX >= GameSize.SCREEN_WIDTH / 2 && playerX <= background.getWidth() - GameSize.SCREEN_WIDTH / 2) {
            xOffset = playerX - GameSize.SCREEN_WIDTH / 2;
        } else if (playerX > background.getWidth() - GameSize.SCREEN_WIDTH / 2) {
            xOffset = background.getWidth() - GameSize.SCREEN_WIDTH;
        }
    }
}
