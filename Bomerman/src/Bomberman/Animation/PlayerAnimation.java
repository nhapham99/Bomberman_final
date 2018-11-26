package Bomberman.Animation;

import Bomberman.Entity.Mob.Player.Player;
import static Bomberman.GameFrame.frame;
import Bomberman.GameWorld.GameSize;
import Bomberman.Graphic.Sprite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerAnimation extends Animation {

    private Player player;
    private boolean dead = false;

    public PlayerAnimation(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        if (!dead) {
            if (player.isMoving()) {
                currentTick++;
                if (currentTick % tickPerImage == 0) {
                    state += add;
                    if (state == 0 || state == Sprite.player_moving[0].length - 1) {
                        add *= -1;
                    }
                }
            } else {
                currentTick = 0;
                state = 0;
                add = 1;
            }
        } else {
            currentTick++;
            if (currentTick % tickPerImage == 0) {
                state += add;
                if (state == Sprite.player_dead.length) {
                    player.remove();
                    JFrame GameOver = new JFrame();
                    frame.setVisible(false);
                    GameOver.setSize(960, 740);
                    GameOver.setMaximumSize(new Dimension(GameSize.SCREEN_WIDTH, GameSize.SCREEN_HEIGHT));
                    GameOver.setMinimumSize(new Dimension(GameSize.SCREEN_WIDTH, GameSize.SCREEN_HEIGHT));
                    GameOver.setPreferredSize(new Dimension(GameSize.SCREEN_WIDTH, GameSize.SCREEN_HEIGHT));
                    JPanel jPanel1 = new javax.swing.JPanel();
                    JLabel jLabel1 = new javax.swing.JLabel();
                    jPanel1.setBackground(new java.awt.Color(0, 0, 0));
                    jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("GAME OVER");
                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(273, 273, 273)
                            .addComponent(jLabel1)
                            .addContainerGap(301, Short.MAX_VALUE))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(200, 200, 200)
                            .addComponent(jLabel1)
                            .addContainerGap(223, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(GameOver.getContentPane());
                    GameOver.getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );

                    GameOver.pack();
                    GameOver.setLocation(480, 200);
                    GameOver.add(jPanel1);
                    GameOver.setVisible(true);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PlayerAnimation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public BufferedImage getImage() {
        if (!dead) {
            if (player.isMoving()) {
                return Sprite.player_moving[player.getDirection()][state];
            }
            return Sprite.player[player.getDirection()];
        }
        if (state < Sprite.player_dead.length) {
            return Sprite.player_dead[state];
        }
        return null;
    }

    public void dead() {
        dead = true;
        state = 0;
        currentTick = 0;
        add = 1;
    }
}
