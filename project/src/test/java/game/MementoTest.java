package game;

import game.models.*;
import game.models.elements.FenceModel;
import game.models.elements.FishModel;
import game.models.elements.plants.BushModel;
import game.models.elements.FarmerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;
import game.models.market.InventoryModel;
import org.junit.Test;
import game.memento.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class MementoTest {

    @Test
    public void Inventory(){

        // capacity = 20 and inventory contains 3 seeds
        InventoryModel inventory = new InventoryModel();

        // capacity now at 25
        inventory.updateCapacity();
        assertEquals(25,inventory.getCapacity());

        // inventory now contains 2 seeds, 1 bush, 1 tree, 1 fish
        inventory.addElement(new TreeModel());
        inventory.addElement(new BushModel());
        inventory.removeElement(inventory.indexInInventory(new SeedModel()));
        inventory.addElement(new FishModel());
        assertEquals(1,inventory.getTrees());
        assertEquals(1,inventory.getBushes());
        assertEquals(2,inventory.getSeeds());
        assertEquals(1,inventory.getFishes());
        assertEquals(20,inventory.getCapacity()-inventory.getElements().size());

        inventory.removeElement(inventory.indexInInventory(new FishModel()));
        // checking save function
        InventoryMemento state = inventory.save();
        assertEquals(
                "25\n1 BUSH\n2 SEED\n1 TREE\n0 FLOWER\n0 FISH\n0 FENCE", state.getState()
        );

        // changes contents of the inventory (3 trees, 0 bushes, 0 seeds and capacity = 40)
        inventory.updateCapacity();
        inventory.addElement(new TreeModel());
        inventory.addElement(new TreeModel());
        inventory.removeElement(inventory.indexInInventory(new SeedModel()));
        inventory.removeElement(inventory.indexInInventory(new SeedModel()));
        inventory.removeElement(inventory.indexInInventory(new BushModel()));
        assertEquals(30,inventory.getCapacity());
        assertEquals(3,inventory.getTrees());
        assertEquals(0,inventory.getBushes());
        assertEquals(0,inventory.getSeeds());

        // checking restore function (1 trees, 1 bushes, 2 seeds and capacity = 30)
        inventory.restore(state);
        assertEquals(25,inventory.getCapacity());
        assertEquals(1,inventory.getTrees());
        assertEquals(1,inventory.getBushes());
        assertEquals(2,inventory.getSeeds());

        //inventory content can correctly be restored to the latest saved game.state
    }

    @Test
    public void Element(){

        FarmerModel farmer = new FarmerModel(1,1);
        assertEquals(new PositionModel(1,1),farmer.getPos());

        FarmerMemento farmerState = farmer.save();
        assertEquals(
                "1;1;20;0", farmerState.getState()
        );

        farmer.setPos(new PositionModel(2,3));
        assertEquals(new PositionModel(2,3),farmer.getPos());

        farmer.restore(farmerState);
        assertEquals(new PositionModel(1,1), farmer.getPos() );


        TreeModel tree = new TreeModel(1,1);
        PlantMemento elementState2 = tree.save();
        assertEquals("1;1;5;-1",elementState2.getState());
        ElementMemento elementStateNew = new ElementMemento(new PositionModel(5,5));
        assertEquals("5;5",elementStateNew.getState());
        tree.restore(elementStateNew);
        assertEquals(new PositionModel(5,5),tree.getPos());
        ElementMemento elementState1 = new ElementMemento("10;10");
        tree.restore(elementState1);
        assertEquals(new PositionModel(10,10),tree.getPos());
    }

    @Test
    public void Plant(){

        SeedModel seed = new SeedModel(1,1);
        assertEquals(new PositionModel(1,1),seed.getPos());
        assertEquals(5,seed.getGrowthTime());
        assertEquals(-1,seed.getPlantedAt());

        PlantMemento plantState = seed.save();
        assertEquals(
                "1;1;5;-1", plantState.getState()
        );

        seed.setPos(new PositionModel(2,3));
        assertEquals(new PositionModel(2,3),seed.getPos());
        seed.setPlantedAt(2);
        assertEquals(2,seed.getPlantedAt());

        seed.restore(plantState);
        assertEquals(new PositionModel(1,1),seed.getPos());
        assertEquals(5,seed.getGrowthTime());
        assertEquals(-1,seed.getPlantedAt());
    }

    @Test
    public void Fence(){

        FenceModel fenceLimit = new FenceModel(1,1, true);
        assertEquals(new PositionModel(1,1),fenceLimit.getPos());
        assertTrue(fenceLimit.isLimitFence());

        FenceModel fence = new FenceModel(1,1, false);
        assertEquals(new PositionModel(1,1),fence.getPos());
        assertFalse(fence.isLimitFence());

        FenceMemento fenceMemento = new FenceMemento(fence.getPos(),fence.isLimitFence());
        assertEquals(
                "1;1;0", fenceMemento.getState()
        );

        FenceMemento fenceLimitMemento = new FenceMemento(fenceLimit.getPos(),fenceLimit.isLimitFence());
        assertEquals(
                "1;1;1", fenceLimitMemento.getState()
        );

        fence.setPos(new PositionModel(2,3));
        assertEquals(new PositionModel(2,3),fence.getPos());

        fence.restore(fenceMemento);
        assertEquals(new PositionModel(1,1),fence.getPos());
    }

    @Test
    public void Farm() {

        FarmModel farmModel = new FarmModel(60,25);
        assertEquals(15, farmModel.getTrees().size());
        assertEquals(0, farmModel.getSeeds().size());
        assertEquals(0, farmModel.getTimeCounter());
        assertFalse(farmModel.removeFromInventory(new BushModel()));

        FarmMemento farmState = farmModel.save();
        //can't compare the entire game.state because of randomly allocated objs
        String[] arr = farmState.getState().split("\n::::::::::\n");
        assertEquals("60", arr[0]);
        assertEquals("25", arr[1]);
        assertEquals("0", arr[2]);
        assertEquals(farmModel.getFarmer().getPos().getX() + ";" + farmModel.getFarmer().getPos().getY()
                + ";" + farmModel.getFarmer().getBalance() + ";" + farmModel.getFarmer().getFishBait(),arr[4]);
        assertEquals(
                "20\n0 BUSH\n3 SEED\n0 TREE\n0 FLOWER\n0 FISH\n0 FENCE", arr[7]
        );

        assertEquals(
                "SEEDS", arr[9]
        );

        farmModel.removeFromInventory(new SeedModel());
        assertEquals(1, farmModel.getSeeds().size());
        assertEquals(2, farmModel.getInventory().getElements().size());
        farmModel.moveObject(farmModel.getFarmer().moveUp(), farmModel.getFarmer());
        assertEquals(new PositionModel(farmModel.getWidth()/2,(farmModel.getHeight()/2)-1), farmModel.getFarmer().getPos());
        assertEquals(2, farmModel.getTimeCounter());

        farmModel.restore(farmState);
        String[] arr1 = farmState.getState().split("\n::::::::::\n");
        assertEquals("0", arr1[2]);
        assertEquals((farmModel.getWidth()/2) + ";" + (farmModel.getHeight()/2)
                + ";" + farmModel.getFarmer().getBalance() + ";" + farmModel.getFarmer().getFishBait(),arr1[4]);
        assertEquals(
                "20\n0 BUSH\n3 SEED\n0 TREE\n0 FLOWER\n0 FISH\n0 FENCE", arr1[7]
        );

        assertEquals(
                "BUSHES", arr1[10]
        );
    }

    @Test
    public void Files() throws IOException {

        FarmModel farmModel = new FarmModel(60,25);
        assertEquals(0, farmModel.getSeeds().size());
        farmModel.saveToFile();

        farmModel.removeFromInventory(new SeedModel());
        assertEquals(1, farmModel.getSeeds().size());

        FarmMemento fs = new FarmMemento(farmModel.readFile());
        farmModel.restore(fs);
        assertEquals(60, farmModel.getWidth());
        assertEquals(0, farmModel.getSeeds().size());

    }

}
