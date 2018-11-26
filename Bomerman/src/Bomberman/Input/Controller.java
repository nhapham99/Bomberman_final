package Bomberman.Input;

import Bomberman.GameWorld.Direction;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    public boolean[] keys = new boolean[7];
    public boolean[] keyRelease = new boolean[5];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                keys[Direction.PLANT] = true;
                break;
            case KeyEvent.VK_UP:
                keys[Direction.UP] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keys[Direction.RIGHT] = true;
                break;
            case KeyEvent.VK_DOWN:
                keys[Direction.DOWN] = true;
                break;
            case KeyEvent.VK_LEFT:
                keys[Direction.LEFT] = true;
                break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                keys[Direction.PLANT] = false;
                keyRelease[Direction.PLANT] = true;
                break;
            case KeyEvent.VK_UP:
                keys[Direction.UP] = false;
                keyRelease[Direction.UP] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keys[Direction.RIGHT] = false;
                keyRelease[Direction.RIGHT] = true;
                break;
            case KeyEvent.VK_DOWN:
                keys[Direction.DOWN] = false;
                keyRelease[Direction.DOWN] = true;
                break;
            case KeyEvent.VK_LEFT:
                keys[Direction.LEFT] = false;
                keyRelease[Direction.LEFT] = true;
                break;
            default: break;
        }
    }
}
