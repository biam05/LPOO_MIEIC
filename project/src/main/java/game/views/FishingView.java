package game.views;

import game.auxiliary.view;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import game.models.FarmModel;

import static java.lang.String.valueOf;

public class FishingView implements ObjectView {

    private Screen screen;
    private FarmModel farmModel;

    public FishingView(Screen screen, FarmModel farmModel) {
        this.screen = screen;
        this.farmModel = farmModel;
    }

    @Override
    public void draw() {

        TextGraphics graphics = view.backgroundRectangle(screen,15,6,30,12);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));

        graphics.putString(20, 16, "YOU CURRENTLY HAVE " + valueOf(farmModel.getFarmer().getFishBait()));
        graphics.putString(26,17, "FISH BAIT");

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.putString(23, 6, "LET'S GO FISHING!");

        if(farmModel.getLake().isPossibleFishing() && farmModel.getFarmer().getFishBait() >= 5) {
            graphics.putString(20, 9, "YOU NEED 5 FISH BAIT!");
            graphics.putString(20, 10, "PRESS 1 TO FISH!");
        }
        else if(farmModel.getFarmer().getFishBait() < 5) {
            graphics.putString(20, 9, "NO FISHBAITS DETECTED...");
            graphics.putString(20, 10, "COME BACK IN A WHILE!");
        }
        else{
            graphics.putString(20, 9, "NO FISHES ON SIGHT...");
            graphics.putString(20, 10, "COME BACK IN A WHILE!");
        }
    }
}
