package game.views;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class MarketView implements ObjectView {

    private Screen screen;

    public MarketView(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void draw() {

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
        graphics.fillRectangle(
                new TerminalPosition(49, 4),
                new TerminalSize(4, 1),
                ' '
        );
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.fillRectangle(
                new TerminalPosition(47, 0),
                new TerminalSize(8, 4),
                'M'
        );
    }
}
