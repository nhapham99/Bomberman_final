package Bomberman.Graphic;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Sprite {

    private static final int TILE_SIZE = 16;
    private static final int PLAYER_WIDTH = 17;
    private static final int PLAYER_HEIGHT = 20;
    private static final int PLAYER_DEAD_WIDTH = 23;
    private static final int PLAYER_DEAD_HEIGHT = 22;

    private static BufferedImage image;
    private static Sprite sprite = new Sprite("/Texture/bomberman.png");

    //Player start
    public static BufferedImage[] player;
    public static BufferedImage[][] player_moving;
    public static BufferedImage[] player_dead;

    public static BufferedImage[] bomb;

    public static BufferedImage[] explosion_center;
    public static BufferedImage[] explosion_vertical;
    public static BufferedImage[] explosion_horizontal;
    public static BufferedImage[] explosion_end_left;
    public static BufferedImage[] explosion_end_right;
    public static BufferedImage[] explosion_end_up;
    public static BufferedImage[] explosion_end_down;

    public static BufferedImage[] item;

    public static BufferedImage portal;

    public static BufferedImage grass;

    public static BufferedImage[][] destroyableObject;
    public static BufferedImage[] undestroyableObject;

    public static BufferedImage[][] enemy_ballom_moving;
    public static BufferedImage enemy_ballom_dead;
    public static BufferedImage[][] enemy_oneal_moving;
    public static BufferedImage enemy_oneal_dead;
    public static BufferedImage[][] enemy_boyon_moving;
    public static BufferedImage enemy_boyon_dead;

    public static BufferedImage[] enemy_dead;

    private Sprite(String path) {
        // Read from file
        try {
            image = ImageIO.read(Sprite.class.getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        //-------------------------
        //      Player
        //-------------------------
        player = new BufferedImage[4];
        player[0] = image.getSubimage(2 * PLAYER_WIDTH, 12 * TILE_SIZE, PLAYER_WIDTH, PLAYER_HEIGHT);
        player[3] = image.getSubimage(4 * PLAYER_WIDTH, 12 * TILE_SIZE, PLAYER_WIDTH + 1, PLAYER_HEIGHT + 1);
        player[1] = horizontalFlip(player[3]);
        player[2] = image.getSubimage(0, 12 * TILE_SIZE, PLAYER_WIDTH, PLAYER_HEIGHT);
        player_moving = new BufferedImage[4][3];
        player_moving[0][0] = image.getSubimage(3 * PLAYER_WIDTH, 12 * TILE_SIZE, PLAYER_WIDTH, PLAYER_HEIGHT);
        player_moving[0][1] = player[0];
        player_moving[0][2] = horizontalFlip(player_moving[0][0]);
        player_moving[3][0] = image.getSubimage(5 * PLAYER_WIDTH + 1, 12 * TILE_SIZE, PLAYER_WIDTH, PLAYER_HEIGHT + 1);
        player_moving[3][1] = player[3];
        player_moving[3][2] = image.getSubimage(6 * PLAYER_WIDTH + 1, 12 * TILE_SIZE, PLAYER_WIDTH + 1, PLAYER_HEIGHT + 1);
        player_moving[1][0] = horizontalFlip(player_moving[3][0]);
        player_moving[1][1] = player[1];
        player_moving[1][2] = horizontalFlip(player_moving[3][2]);
        player_moving[2][0] = image.getSubimage(PLAYER_WIDTH, 12 * TILE_SIZE, PLAYER_WIDTH, PLAYER_HEIGHT);
        player_moving[2][1] = player[2];
        player_moving[2][2] = horizontalFlip(player_moving[2][0]);
        player_dead = new BufferedImage[7];
        for (int i = 0; i < 7; i++) {
            player_dead[i] = image.getSubimage(i * PLAYER_DEAD_WIDTH, 12 * TILE_SIZE + PLAYER_HEIGHT + 1, PLAYER_DEAD_WIDTH, PLAYER_DEAD_HEIGHT);
        }


        //-------------------------
        //      Destroyable
        //-------------------------
        destroyableObject = new BufferedImage[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                destroyableObject[i][j] = image.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }


        //-------------------------
        //      Undestroyable
        //-------------------------
        undestroyableObject = new BufferedImage[5];
        for (int i = 0; i < 5; i++) {
            undestroyableObject[i] = image.getSubimage(i * TILE_SIZE, 6 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        //-------------------------
        //      Portal
        //-------------------------
        portal = image.getSubimage(5 * TILE_SIZE, 6 * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        //-------------------------
        //      Explosion
        //-------------------------
        explosion_center = new BufferedImage[5];
        explosion_vertical = new BufferedImage[5];
        explosion_horizontal = new BufferedImage[5];
        explosion_end_down = new BufferedImage[5];
        explosion_end_left = new BufferedImage[5];
        explosion_end_right = new BufferedImage[5];
        explosion_end_up = new BufferedImage[5];
        for (int i = 0; i < 5; i++) {
            explosion_center[i] = image.getSubimage(0, (7 + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            explosion_horizontal[i] = image.getSubimage(TILE_SIZE, (7 + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            explosion_end_right[i] = image.getSubimage(2 * TILE_SIZE, (7 + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            explosion_end_left[i] = horizontalFlip(explosion_end_right[i]);
            explosion_vertical[i] = image.getSubimage(3 * TILE_SIZE, (7 + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            explosion_end_up[i] = image.getSubimage(4 * TILE_SIZE, (7 + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            explosion_end_down[i] = verticalFlip(explosion_end_up[i]);
        }

        //-------------------------
        //      Item
        //-------------------------
        item = new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            item[i] = image.getSubimage((6 + i) * TILE_SIZE, 7 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        //-------------------------
        //      Bomb
        //-------------------------
        bomb = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            bomb[i] = image.getSubimage(5 * TILE_SIZE, (7 + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        //-------------------------
        //      Enemy
        //-------------------------
        enemy_dead = new BufferedImage[5];
        for (int i = 0; i < 5; i++) {
            enemy_dead[i] = image.getSubimage((9 + i) * TILE_SIZE, 6 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
        enemy_ballom_dead = image.getSubimage(9 * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
        enemy_ballom_moving = new BufferedImage[2][3];
        for (int i = 0; i < 3; i++) {
            enemy_ballom_moving[0][i] = image.getSubimage((6 + i) * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
            enemy_ballom_moving[1][i] = horizontalFlip(enemy_ballom_moving[0][i]);
        }
        enemy_oneal_dead = image.getSubimage(9 * TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE);
        enemy_oneal_moving = new BufferedImage[2][3];
        for (int i = 0; i < 3; i++) {
            enemy_oneal_moving[1][i] = image.getSubimage((6 + i) * TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE);
            enemy_oneal_moving[0][i] = horizontalFlip(enemy_oneal_moving[1][i]);
        }
        enemy_boyon_dead = image.getSubimage(9 * TILE_SIZE, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        enemy_boyon_moving = new BufferedImage[2][3];
        for (int i = 0; i < 3; i++) {
            enemy_boyon_moving[0][i] = image.getSubimage((6 + i) * TILE_SIZE, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            enemy_boyon_moving[1][i] = enemy_boyon_moving[0][i];
        }

        //-------------------------
        //      Grass
        //-------------------------
        grass = image.getSubimage(6 * TILE_SIZE, 8 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    private static BufferedImage horizontalFlip(BufferedImage bufferedImage) {
        AffineTransform affineTransform = AffineTransform.getScaleInstance(-1, 1);
        affineTransform.translate(-bufferedImage.getWidth(null), 0);
        return new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter(bufferedImage, null);
    }

    private static BufferedImage verticalFlip(BufferedImage bufferedImage) {
        AffineTransform affineTransform = AffineTransform.getScaleInstance(1, -1);
        affineTransform.translate(0, -bufferedImage.getHeight(null));
        return new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter(bufferedImage, null);
    }
}
