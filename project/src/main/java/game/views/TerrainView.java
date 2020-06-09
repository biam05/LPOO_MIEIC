package game.views;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class TerrainView implements ObjectView {

    private Screen screen;

    public TerrainView(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void draw() {

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#377511"));

        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(60, 25),
                ' '
        );
    }
}
