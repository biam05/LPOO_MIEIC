package game;

import game.auxiliary.view;
import game.models.FarmModel;
import game.models.PositionModel;
import game.models.elements.*;
import game.auxiliary.collision;
import game.auxiliary.objectManagement;
import game.models.elements.lake.DeckModel;
import game.models.elements.lake.LakeModel;
import game.models.elements.lake.WaterModel;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.FlowerModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;
import game.models.market.InventoryModel;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class FarmerModelTest {

    @Test
    public void testPositionMove() {

        FarmModel farmModel = new FarmModel(60,25);

        farmModel.moveObject(farmModel.getFarmer().moveRight(), farmModel.getFarmer());
        farmModel.moveObject(farmModel.getFarmer().moveDown(), farmModel.getFarmer());

        assertEquals(31, farmModel.getFarmer().getPos().getX());
        assertEquals(13, farmModel.getFarmer().getPos().getY());
    }

    @Test
    public void testPositionCollision() {

        FarmerModel farmer = new FarmerModel(30,12);
        FarmModel mockFarmModel = Mockito.mock(FarmModel.class);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                farmer.setPos(farmer.moveUp());
                return null;
            }
        }).when(mockFarmModel).moveObject(farmer.moveUp(),farmer);
        when(mockFarmModel.getHeight()).thenReturn(25);
        when(mockFarmModel.getWidth()).thenReturn(60);

        mockFarmModel.moveObject(farmer.moveUp(),farmer);

        assertEquals(30, farmer.getPos().getX());
        assertEquals(11, farmer.getPos().getY());

        farmer.setPos(new PositionModel(2,2));
        FenceModel fence = new FenceModel(2,1,true);
        mockFarmModel.moveObject(farmer.moveUp(),farmer);
        assertEquals(2, farmer.getPos().getX());
        assertEquals(2, farmer.getPos().getY());

        PositionModel pos_false = new PositionModel(53,2);
        PositionModel pos_true = new PositionModel(40,2);
        assertFalse(collision.marketCollision(mockFarmModel,pos_false));
        assertTrue(collision.marketCollision(mockFarmModel,pos_true));

        pos_false.setX(3);
        pos_false.setY(5);
        pos_true.setX(52);
        pos_true.setY(4);
        assertFalse(collision.rugCollision(mockFarmModel,pos_false));
        assertTrue(collision.rugCollision(mockFarmModel,pos_true));

        pos_false.setX(50);
        pos_false.setY(19);
        pos_true.setX(41);
        pos_true.setY(19);
        assertFalse(collision.deckCollision(mockFarmModel,pos_false));
        assertTrue(collision.deckCollision(mockFarmModel,pos_true));

        List<ElementModel> list = new ArrayList<>();
        list.add(new DeckModel(1,1));

        when(mockFarmModel.getAllElements()).thenReturn(list);
        assertTrue(collision.elementCollision(mockFarmModel,new PositionModel(1,2)));

        list.add(new WaterModel(1,2));
        list.add(new TreeModel(1,3));
        list.add(new FenceModel(1,4,true));
        list.add(new AnimalModel(1,5));

        when(mockFarmModel.getAllElements()).thenReturn(list);
        assertFalse(collision.elementCollision(mockFarmModel, new PositionModel(1,3)));
        assertTrue(collision.elementCollision(mockFarmModel, new PositionModel(1,6)));
    }

    @Test
    public void objectManagementtest(){

        FarmModel farmModelMock = Mockito.mock(FarmModel.class);
        InventoryModel inventory = new InventoryModel();
        inventory.addElement(new BushModel());
        inventory.addElement(new FenceModel());
        inventory.addElement(new TreeModel());
        inventory.addElement(new FishModel());
        inventory.addElement(new FlowerModel());
        when(farmModelMock.getInventory()).thenReturn(inventory);

        HashMap<String, Integer> map = inventory.indexSelection(true);
        assertTrue(objectManagement.pickIsValid(map,0));
        assertTrue(objectManagement.pickIsValid(map,1));
        assertTrue(objectManagement.pickIsValid(map,2));
        assertTrue(objectManagement.pickIsValid(map,3));
        assertTrue(objectManagement.pickIsValid(map,4));
        assertTrue(objectManagement.pickIsValid(map,5));

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                int index = -1;
                for(int i = 0; i < inventory.getElements().size(); i++) {
                    if(inventory.getElements().get(i).equals(new TreeModel())) index = i;
                }
                inventory.removeElement(index);
                return null;
            }
        }).when(farmModelMock).removeFromInventory(new TreeModel());

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                int index = -1;
                for(int i = 0; i < inventory.getElements().size(); i++) {
                    if(inventory.getElements().get(i).equals(new BushModel())) index = i;
                }
                inventory.removeElement(index);
                return null;
            }
        }).when(farmModelMock).removeFromInventory(new BushModel());

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                int index = -1;
                for(int i = 0; i < inventory.getElements().size(); i++) {
                    if(inventory.getElements().get(i).equals(new SeedModel())) index = i;
                }
                inventory.removeElement(index);
                return null;
            }
        }).when(farmModelMock).removeFromInventory(new SeedModel());

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                int index = -1;
                for(int i = 0; i < inventory.getElements().size(); i++) {
                    if(inventory.getElements().get(i).equals(new FlowerModel())) index = i;
                }
                inventory.removeElement(index);
                return null;
            }
        }).when(farmModelMock).removeFromInventory(new FlowerModel());

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                int index = -1;
                for(int i = 0; i < inventory.getElements().size(); i++) {
                    if(inventory.getElements().get(i).equals(new FenceModel())) index = i;
                }
                inventory.removeElement(index);
                return null;
            }
        }).when(farmModelMock).removeFromInventory(new FenceModel());

        objectManagement.plant(farmModelMock,map,5);
        objectManagement.plant(farmModelMock,map,4);
        objectManagement.plant(farmModelMock,map,3);
        objectManagement.plant(farmModelMock,map,2);
        objectManagement.plant(farmModelMock,map,1);
        objectManagement.plant(farmModelMock,map,0);

        for(int i = 0; i <25; i++) inventory.addElement(new TreeModel());
       // doReturn((int) inventory.getCapacity()).when(farmModelMock).getInventory().getElements().size();

    //    when(farmModelMock.getInventory().getElements().size()).thenReturn(inventory.getCapacity());

        FarmerModel farmerModelMock = Mockito.mock(FarmerModel.class);
        when(farmModelMock.getFarmer()).thenReturn(farmerModelMock);

        when(farmModelMock.getFarmer().getBalance()).thenReturn(0);
        when(farmerModelMock.getBalance()).thenReturn(0);

      //  doNothing().when(farmModelMock.getFarmer()).setBalance(farmModelMock.getFarmer().getBalance()+( (new FishModel().getPrice())+1));
      //  doNothing().when(farmModelMock.getFarmer()).setBalance(farmModelMock.getFarmer().getBalance()+( (new FenceModel().getPrice())+1));

        FarmModel fm = new FarmModel(60,25);
        for(int i = 0; i< 17; i++) fm.getInventory().addElement(new TreeModel());
        objectManagement.addOrSell(fm,new FishModel(),3);
        objectManagement.addOrSell(fm, new FenceModel(),3);

        List<TreeModel> trees = new ArrayList<>();
        trees.add(new TreeModel());
        List<FenceModel> fences = new ArrayList<>();
        fences.add(new FenceModel());
        List<AnimalModel> animals = new ArrayList<>();
        animals.add(new AnimalModel());
        List<SeedModel> seeds = new ArrayList<>();
        seeds.add(new SeedModel());
        List<BushModel> bushes = new ArrayList<>();
        bushes.add(new BushModel());
        List<FlowerModel> flowers = new ArrayList<>();
        flowers.add(new FlowerModel());
        LakeModel lake = new LakeModel();
        when(farmModelMock.getTrees()).thenReturn(trees);
        when(farmModelMock.getAnimals()).thenReturn(animals);
        when(farmModelMock.getFences(true)).thenReturn(fences);
        when(farmModelMock.getSeeds()).thenReturn(seeds);
        when(farmModelMock.getBushes()).thenReturn(bushes);
        when(farmModelMock.getFlowers()).thenReturn(flowers);
        when(farmModelMock.getLake()).thenReturn(lake);

        objectManagement.clearAll(farmModelMock);
    }

    @Test
    public void viewAuxTest() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("BUSH", 0);
        map.put("SEED", 1);
        map.put("TREE", 2);
        map.put("FLOWER", 3);
        map.put("FENCE", 4);
        map.put("FISH", 5);

        assertEquals("BUSH",view.getString(map,0));
        assertEquals("SEED",view.getString(map,1));
        assertEquals("TREE",view.getString(map,2));
        assertEquals("FLOWER",view.getString(map,3));
        assertEquals("FENCE",view.getString(map,4));
        assertEquals("FISH",view.getString(map,5));
        assertEquals("",view.getString(map,6));

    }
}
