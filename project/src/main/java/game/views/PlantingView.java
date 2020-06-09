package game.views;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import game.models.FarmModel;
import java.util.HashMap;
import static game.auxiliary.view.*;

public class PlantingView implements ObjectView {

    private Screen screen;
    private FarmModel farmModel;

    public PlantingView(Screen screen, FarmModel farmModel) {
        this.screen = screen;
        this.farmModel = farmModel;
    }

    @Override
    public void draw() {
        HashMap<String, Integer> map = farmModel.getInventory().indexSelection(false);
        int x = farmModel.getFarmer().getPos().getX()+ 1, y = farmModel.getFarmer().getPos().getY()+1, row = 5;

        if(map.containsValue(4)) row = 6;
        if(x > 50) x = x -11;
        if(y > 20) y = y -6;

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(
            new TerminalPosition(x, y),
            new TerminalSize(10, row),
            ' '
        );
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(x+3, y, "PLANT");

        printPicks(graphics, farmModel, x, y);
    }

    private String keyFromValue(HashMap<String, Integer> map, int value) {
        return getString(map, value);
    }

    private void printPicks(TextGraphics graphics, FarmModel farmModel, int x, int y) {
        HashMap<String, Integer> map = farmModel.getInventory().indexSelection(false);
        String string = "";
        if(map.containsValue(0)) {
            string = keyFromValue(map, 0);
            graphics.putString(x + 1, y + 1, "0 " + string);
        }
        if(map.containsValue(1)) {
            string = keyFromValue(map, 1);
            graphics.putString(x + 1, y + 2, "1 " + string);
        }
        if(map.containsValue(2)) {
            string = keyFromValue(map, 2);
            graphics.putString(x + 1, y + 3, "2 " + string);
        }
        if(map.containsValue(3)) {
            string = keyFromValue(map, 3);
            graphics.putString(x + 1, y + 4, "3 " + string);
        }
        if(map.containsValue(4)) {
            string = keyFromValue(map, 4);
            graphics.putString(x + 1, y + 5, "4 " + string);
        }
    }
}
