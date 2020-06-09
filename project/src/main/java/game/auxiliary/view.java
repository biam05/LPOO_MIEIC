package game.auxiliary;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.HashMap;

public class view {

    static public String getString(HashMap<String, Integer> map, int value) {
        if (map.get("BUSH") == value) return "BUSH";
        else if (map.get("SEED") == value) return "SEED";
        else if (map.get("TREE") == value) return "TREE";
        else if (map.get("FLOWER") == value) return "FLOWER";
        else if (map.get("FENCE") == value) return "FENCE";
        else if (map.get("FISH") == value) return "FISH";
        return "";
    }
    static public TextGraphics backgroundRectangle(Screen screen, int c1, int r1, int c2, int r2) {

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                new TerminalPosition(c1, r1),
                new TerminalSize(c2, r2),
                ' '
        );
        return graphics;
    }
}
