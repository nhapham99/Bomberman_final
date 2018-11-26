package Bomberman.Level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

abstract class LevelLoader {

    static List<String> load(String path) {
        List<String> strings = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.readLine();
            String string = null;
            while ((string = bufferedReader.readLine()) != null) {
                strings.add(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return strings;
    }
}
