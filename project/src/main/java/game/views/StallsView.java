package game.views;

import game.auxiliary.view;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import game.models.FarmModel;
import java.util.HashMap;
import static game.auxiliary.view.*;
import static java.lang.String.valueOf;

public class StallsView implements ObjectView {

    private Screen screen;
    private FarmModel farmModel;

    public StallsView(Screen screen, FarmModel farmModel) {
        this.screen = screen;
        this.farmModel = farmModel;
    }

    @Override
    public void draw() {
        TextGraphics graphics = view.backgroundRectangle(screen,14,6,33,12);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(26, 16, valueOf(farmModel.getFarmer().getBalance()) + " COINS");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(27, 6, "MARKET");
        graphics.putString(20, 8, "SELL");
        graphics.putString(35, 8, "BUY");

        printPositions(graphics, farmModel);
        printBuyableItems(graphics, farmModel);
    }

    private String keyFromValue(HashMap<String, Integer> map, int value) {
        return getString(map, value);
    }

    private void printPositions(TextGraphics graphics, FarmModel farmModel){
        HashMap<String, Integer> map = farmModel.getInventory().indexSelection(true);
        String string = "";

        for(int i = 0; i < 5; i++){
            if(map.containsValue(i)){
                string = keyFromValue(map, i);
                printSellabeItems(graphics, farmModel,string,i+6,i);
            }
        }
    }
    private void printSellabeItems(TextGraphics graphics, FarmModel farmModel, String string, int posMarket, int value){
        switch (string) {
            case "TREE":
                graphics.putString(15, 5 + posMarket, value + " " + string +" (" + valueOf(farmModel.getInventory().getTrees()) + ")");
                break;
            case "SEED":
                graphics.putString(15, 5 + posMarket, value + " " + string +" (" + valueOf(farmModel.getInventory().getSeeds()) + ")");
                break;
            case "BUSH":
                graphics.putString(15, 5 + posMarket, value + " " + string +" (" + valueOf(farmModel.getInventory().getBushes()) + ")");
                break;
            case "FISH":
                graphics.putString(15, 5 + posMarket, value + " " + string +" (" + valueOf(farmModel.getInventory().getFishes()) + ")");
                break;
            case "FLOWER":
                graphics.putString(15, 5 + posMarket, value + " " + string +" (" + valueOf(farmModel.getInventory().getFlowers()) + ")");
                break;
            case "FENCE":
                graphics.putString(15, 5 + posMarket, value + " " + string +" (" + valueOf(farmModel.getInventory().getFences()) + ")");
                break;
        }
    }
    private void printBuyableItems(TextGraphics graphics, FarmModel farmModel) {
        for(int i = 0; i< farmModel.getMarket().getStalls().size(); i++) {
            if(farmModel.getMarket().getStalls().get(i).getName().equals("COMING SOON")) graphics.putString(30, 11 +i, 9-i + " " + farmModel.getMarket().getStalls().get(i).getName());
            else if(farmModel.getMarket().getStalls().get(i).getName().equals("FLOWER")) graphics.putString(30, 11 +i, 9-i + " " + farmModel.getMarket().getStalls().get(i).getName() + " (" +
                    farmModel.getMarket().getStalls().get(i).getQuantity() + ")" + farmModel.getMarket().getStalls().get(i).getPrice() + "$");
            else if(farmModel.getMarket().getStalls().get(i).getName().equals("FENCE") && farmModel.getMarket().getStalls().get(i).getQuantity()>6)
                graphics.putString(30, 11 +i, 9-i + " " + farmModel.getMarket().getStalls().get(i).getName() + " (" +
                    farmModel.getMarket().getStalls().get(i).getQuantity() + ")" + farmModel.getMarket().getStalls().get(i).getPrice() + "$");
            else graphics.putString(30, 11 +i, 9-i + " " + farmModel.getMarket().getStalls().get(i).getName() + " (" +
                        farmModel.getMarket().getStalls().get(i).getQuantity() + ") " + farmModel.getMarket().getStalls().get(i).getPrice() + "$");
        }
    }

}
