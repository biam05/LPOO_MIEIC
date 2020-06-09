package game.views;

import game.auxiliary.view;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import game.models.FarmModel;
import java.util.HashMap;

import static java.lang.String.valueOf;

public class InventoryView implements ObjectView {

    private Screen screen;
    private FarmModel farmModel;

    public InventoryView(Screen screen, FarmModel farmModel) {
        this.screen = screen;
        this.farmModel = farmModel;
    }

    @Override
    public void draw() {
        TextGraphics graphics = view.backgroundRectangle(screen,15,6,30,12);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(26, 16, valueOf(farmModel.getFarmer().getBalance()) + " COINS");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(25, 6, "INVENTORY");

        printPositions(graphics, farmModel);
    }

    private String keyFromValue(HashMap<String, Integer> map, int value) {
        if (map.get("BUSH") == value) return "BUSH";
        else if (map.get("SEED") == value) return "SEED";
        else if (map.get("TREE") == value) return "TREE";
        else if (map.get("FLOWER") == value) return "FLOWER";
        else if (map.get("FISH") == value) return "FISH";
        else if (map.get("FENCE") == value) return "FENCE";
        return "";
    }

    private void printPositions(TextGraphics graphics, FarmModel farmModel) {
        HashMap<String, Integer> map = farmModel.getInventory().indexSelection(true);
        String string = "";

        for (int i = 0; i < 6; i++) {
            if (map.containsValue(i)) {
                string = keyFromValue(map, i);
                InventorySingularOrPlural(graphics, farmModel, string, i);
            }
        }
    }
    private void InventorySingularOrPlural(TextGraphics graphics, FarmModel farmModel, String string, int posInv) {
        switch (string) {
            case "TREE":
                if (farmModel.getInventory().getTrees() == 1) graphics.putString(20, 7 + posInv, valueOf(1) + " TREE");
                else graphics.putString(20, 7 + posInv, valueOf(farmModel.getInventory().getTrees()) + " TREES");
                break;
            case "SEED":
                if (farmModel.getInventory().getSeeds() == 1) graphics.putString(20, 7 + posInv, valueOf(1) + " SEED");
                else graphics.putString(20, 7 + posInv, valueOf(farmModel.getInventory().getSeeds()) + " SEEDS");
                break;
            case "BUSH":
                if (farmModel.getInventory().getBushes() == 1) graphics.putString(20, 7 + posInv, valueOf(1) + " BUSH");
                else graphics.putString(20, 7 + posInv, valueOf(farmModel.getInventory().getBushes()) + " BUSHES");
                break;
            case "FISH":
                if (farmModel.getInventory().getFishes() == 1) graphics.putString(20, 7 + posInv, valueOf(1) + " FISH");
                else graphics.putString(20, 7 + posInv, valueOf(farmModel.getInventory().getFishes()) + " FISHES");
                break;
            case "FLOWER":
                if (farmModel.getInventory().getFlowers() == 1) graphics.putString(20, 7 + posInv, valueOf(1) + " FLOWER");
                else graphics.putString(20, 7 + posInv, valueOf(farmModel.getInventory().getFlowers()) + " FLOWERS");
                break;
            case "FENCE":
                if (farmModel.getInventory().getFences() == 1) graphics.putString(20, 7 + posInv, valueOf(1) + " FENCE");
                else graphics.putString(20, 7 + posInv, valueOf(farmModel.getInventory().getFences()) + " FENCES");
                break;
        }
    }
}
