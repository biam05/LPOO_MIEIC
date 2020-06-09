package game.views;

import game.auxiliary.view;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class HelpView implements ObjectView {

    private Screen screen;

    public HelpView(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void draw() {
        TextGraphics graphics = view.backgroundRectangle(screen,7,3,45,18);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(7+ 20, 3, "HELP");
        helpCommands(graphics);
    }

    private void helpCommands(TextGraphics graphics){
        graphics.putString(9, 5, "Arrows: move the farmer");
        graphics.putString(9, 6, "esc: quit game and windows");
        graphics.putString(9, 7, "p: plant something from inventory");
        graphics.putString(9, 8, "e: store in inventory");
        graphics.putString(9, 9, "s: save the game");
        graphics.putString(9, 10, "r: restore last saving point");
        graphics.putString(9, 11, "i: access inventory");
        graphics.putString(9, 12, "h: help menu");
        graphics.putString(9, 13, "m: market menu (access through the rug)");
        graphics.putString(9, 14, "l: lake (access through the deck)");
    }
}
