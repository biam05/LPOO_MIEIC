package game.views;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import game.models.*;
import game.state.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FarmView {

    private Screen screen;

    public FarmView(int width, int height) throws IOException{

        AWTTerminalFontConfiguration fontConfiguration = AWTTerminalFontConfiguration.newInstance(new
                Font("VL Gothic Regular", Font.PLAIN, 30));
        Terminal terminal = new DefaultTerminalFactory().setForceAWTOverSwing(true).setTerminalEmulatorFontConfiguration(fontConfiguration)
                .setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public Screen getScreen() {
        return screen;
    }
    public ArrayList<ObjectView> getView(FarmModel farmModel, State state){

        ArrayList<ObjectView> views = new ArrayList<>();

        if(state instanceof MenuState) views.add(new MenuView(screen));
        else if(state instanceof HelpMenuState) {
            views.add(new MenuView(screen));
            views.add(new HelpView(screen));
        }
        else{
            views.add(new TerrainView(screen));
            views.add(new MarketView(screen));
            views.add(new ElementsView(screen, farmModel));
            if(state instanceof InventoryState) views.add(new InventoryView(screen, farmModel));
            else if(state instanceof MarketState) views.add(new StallsView(screen, farmModel));
            else if(state instanceof FishingState) views.add(new FishingView(screen, farmModel));
            else if(state instanceof PlantingState) views.add(new PlantingView(screen, farmModel));
            else if(state instanceof HelpGameState) views.add(new HelpView(screen));
        }
        return views;
    }

    public void drawGame(FarmModel farmModel, State state) throws IOException {
        screen.clear();
        for(ObjectView view : getView(farmModel, state)) view.draw();
        screen.refresh();
    }
}
