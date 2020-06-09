package game;

import game.auxiliary.TYPE;
import game.memento.FarmMemento;
import game.models.*;
import game.models.elements.FenceModel;
import game.models.elements.FishModel;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.FlowerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;
import game.models.market.StallModel;
import game.models.market.UpgradeModel;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class MarketModelTest {

    @Test
    public void differentStalls(){

        FarmModel farmModel = new FarmModel(60,25);

        BushModel bush = new BushModel();
        FlowerModel flower = new FlowerModel();
        TreeModel tree = new TreeModel();
        SeedModel seed = new SeedModel();

        StallModel stall1 = new StallModel(bush, 10);
        TYPE type = stall1.getType();
        assertEquals(type, TYPE.BUSH);
        assertEquals("BUSH", stall1.getName());

        StallModel stall2 = new StallModel(flower, 10);
        type = stall2.getType();
        assertEquals(type, TYPE.FLOWER);
        assertEquals("FLOWER", stall2.getName());

        StallModel stall3 = new StallModel(tree, 10);
        type = stall3.getType();
        assertEquals(type, TYPE.TREE);
        assertEquals("TREE", stall3.getName());

        StallModel stall4 = new StallModel(seed, 10);
        type = stall4.getType();
        assertEquals(type, TYPE.SEED);
        assertEquals("SEED", stall4.getName());

        UpgradeModel up1 = new UpgradeModel(1);
        UpgradeModel up2 = new UpgradeModel(2);
        UpgradeModel up3 = new UpgradeModel(3);
        UpgradeModel up4 = new UpgradeModel(4);
        UpgradeModel uperr = new UpgradeModel(50);

        StallModel stallup1 = new StallModel(up1);
        assertEquals("FENCE", stallup1.getName());

        StallModel stallup2 = new StallModel(up2);
        assertEquals("FLOWER", stallup2.getName());

        StallModel stallup3 = new StallModel(up3);
        assertEquals("LAKE", stallup3.getName());

        StallModel stallup4 = new StallModel(up4);
        assertEquals("COMING SOON", stallup4.getName());

        StallModel stalluperr = new StallModel(uperr);
        assertEquals("COMING SOON", stalluperr.getName());

    }

    @Test
    public void Market() {

        FarmModel farmModel = new FarmModel(60,25);

        assertEquals(farmModel.getMarket().getCapacity(), farmModel.getMarket().getStalls().size());
        assertEquals(5, farmModel.getMarket().getStalls().size());

        List<StallModel> stalls = farmModel.getMarket().getStalls();
        assertNull(stalls.get(4).getPlant());
        assertEquals((new UpgradeModel(1).getPrice()), stalls.get(4).getPrice());

        farmModel.getMarket().removeStall(stalls.get(3));
        assertEquals(4, farmModel.getMarket().getStalls().size());

        farmModel.getMarket().addStall(new StallModel(new FenceModel(), 3));
        assertEquals(5, farmModel.getMarket().getStalls().size());

        stalls = farmModel.getMarket().getStalls();
        assertEquals(TYPE.FENCE, stalls.get(4).getType());
        assertEquals((new FenceModel()).getPrice() * 3, stalls.get(4).getPrice());

        assertFalse(stalls.get(4).getFence().isLimitFence());

    }

    @Test
    public void Sell() {

        FarmModel farmModel = new FarmModel(60,25);
        for(int i = 0; i<4; i++) {
            farmModel.getInventory().addElement(new TreeModel());
        }
        for(int i = 0; i<6; i++) {
            farmModel.getInventory().addElement(new BushModel());
        }
        //inventory has 3 seeds, 4 trees and 6 bushes; map is not empty!
        HashMap<String, Integer> map = farmModel.getInventory().indexSelection(true);

        //0 = bush
        assertEquals(20, farmModel.getFarmer().getBalance());
        assertTrue(farmModel.sellMarket(map,0));
        assertEquals(5, farmModel.getInventory().getBushes());

        assertEquals(20+(new BushModel().getPrice()+1), farmModel.getFarmer().getBalance());
    }

    @Test
    public void Buy() {

        FarmModel farmModel = new FarmModel(60,25);
        farmModel.getMarket().getStalls().remove(4);
        farmModel.getMarket().addStall(new StallModel(new SeedModel(),2));

        List<StallModel> stalls = farmModel.getMarket().getStalls();

        assertEquals(5, farmModel.getMarket().getStalls().size());
        assertEquals(3, farmModel.getInventory().getElements().size());

        farmModel.getFarmer().setBalance(0);
        assertFalse(farmModel.buyMarket(9));

        farmModel.getFarmer().setBalance(20);

        assertTrue(farmModel.buyMarket(5));
        assertEquals(5,farmModel.getInventory().getElements().size());
        assertEquals(4,farmModel.getMarket().getStalls().size());
        assertEquals(20-(new game.models.elements.plants.SeedModel().getPrice()*2), farmModel.getFarmer().getBalance());
    }

    @Test
    public void FarmSell() {
        FarmModel fm = new FarmModel(60,25);
        fm.getInventory().addElement(new TreeModel());
        fm.getInventory().addElement(new FenceModel());
        fm.getInventory().addElement(new FishModel());
        fm.getInventory().addElement(new FlowerModel());
        fm.getInventory().addElement(new BushModel());

        assertTrue(fm.sellMarket(fm.getInventory().indexSelection(true),4));
        assertTrue(fm.sellMarket(fm.getInventory().indexSelection(true),4));
        assertTrue(fm.sellMarket(fm.getInventory().indexSelection(true),3));
        assertTrue(fm.sellMarket(fm.getInventory().indexSelection(true),2));
        assertTrue(fm.sellMarket(fm.getInventory().indexSelection(true),1));
        assertTrue(fm.sellMarket(fm.getInventory().indexSelection(true),0));

        assertFalse(fm.sellMarket(fm.getInventory().indexSelection(true),6));

        assertEquals(2,fm.getInventory().getElements().size());
    }

    @Test
    public void FarmBuy(){
        FarmModel fm = new FarmModel(60,25);
        fm.getFarmer().setBalance(160);

        assertTrue(fm.buyMarket(5));

        fm.getFarmer().setBalance(300);
        assertTrue(fm.buyMarket(9));
    }

    @Test
    public void Memento(){
        FarmModel fm = new FarmModel(60,25);
        fm.getFarmer().setBalance(1000);

        assertTrue(fm.buyMarket(5));
        assertTrue(fm.buyMarket(5));
        assertTrue(fm.buyMarket(5));

        fm.getInventory().addElement(new TreeModel());
        fm.getInventory().addElement(new FenceModel());
        fm.getInventory().addElement(new FishModel());
        fm.getInventory().addElement(new FlowerModel());
        fm.getInventory().addElement(new BushModel());

        int counter = fm.getInventory().getElements().size();
        FarmMemento fs = fm.save();
        fm.getInventory().removeElement(0);
        fm.restore(fs);
        assertEquals(counter,fm.getInventory().getElements().size());
    }
}
