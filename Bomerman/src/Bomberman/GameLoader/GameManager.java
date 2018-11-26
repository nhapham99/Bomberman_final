package Bomberman.GameLoader;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<GameStart> stateList = new ArrayList<>();

    public GameManager() {
        stateList.add(new GameLevel());
    }
    public void start() {
        stateList.get(0).start();
    }
}
