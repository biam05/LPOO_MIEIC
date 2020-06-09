package game.views;

import game.auxiliary.view;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class MenuView implements ObjectView {

    private Screen screen;

    public MenuView(Screen screen) {this.screen = screen;}

    @Override
    public void draw() {
        TextGraphics graphics = view.backgroundRectangle(screen,15,6,30,12);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.putString(21, 3, "STARDEW MOUNTAIN");

        graphics.putString(21, 13, "1 Start new game");
        graphics.putString(15, 15, "2 Load from last saving point");
        graphics.putString(25, 17, "3 Help");
    }
}
