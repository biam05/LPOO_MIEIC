package game.views;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import game.models.*;
import game.auxiliary.*;
import game.models.elements.*;
import game.models.elements.lake.DeckModel;
import game.models.elements.lake.WaterModel;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.FlowerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;

public class ElementsView implements ObjectView {

    private Screen screen;
    private FarmModel farmModel;

    public ElementsView(Screen screen, FarmModel farmModel) {
        this.screen = screen;
        this.farmModel = farmModel;
    }

    @Override
    public void draw() {
        for (ElementModel element : farmModel.getAllElements()) drawObjects(farmModel, element);
    }

    private void drawObjects(FarmModel farmModel, ElementModel element){
        if(element instanceof TreeModel)     drawObject(element.getPos(), "♠", "#064102","#377511");
        else if(element instanceof FenceModel)    drawObject(element.getPos(), "#", " #663300","#377511");
        else if(element instanceof AnimalModel)   drawObject(element.getPos(), "A", "#000000","#377511");
        else if(element instanceof SeedModel)     drawObject(element.getPos(), ".", "#000000","#377511");
        else if(element instanceof BushModel)     drawObject(element.getPos(), "m", "#000000","#377511");
        else if(element instanceof FlowerModel)   drawObject(element.getPos(), "☼", "#FF69B4","#377511");
        else if(element instanceof WaterModel)    drawObject(element.getPos(), "~", "#87CEFA","#298ACF");
        else if(element instanceof DeckModel)     drawObject(element.getPos(), " ", "#78450E","#78450E");
        else if(element instanceof GiftModel)     drawObject(element.getPos(), "♦", "#FF0000","#377511");
        else if(element instanceof FarmerModel)   drawFarmer(farmModel, element.getPos(), "⛹", "#FFFFFF");
    }
    private void drawObject(PositionModel position, String character, String color, String backgroundColor){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(position.getX(), position.getY(), character);
    }
    private void drawFarmer(FarmModel farmModel, PositionModel position, String character, String color){
        TextGraphics graphics = screen.newTextGraphics();
        if(collision.rugCollision(farmModel, position))
            graphics.setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
        else if(collision.deckCollision(farmModel,position) && farmModel.getUpgradeCounter()>=3)
            graphics.setBackgroundColor(TextColor.Factory.fromString("#78450E"));
        else
            graphics.setBackgroundColor(TextColor.Factory.fromString("#377511"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(position.getX(), position.getY(), character);
    }
}
