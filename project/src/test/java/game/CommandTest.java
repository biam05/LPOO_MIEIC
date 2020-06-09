package game;

import game.commands.memento.RestoreCommand;
import game.commands.memento.SaveCommand;
import game.commands.movement.*;
import game.commands.*;
import game.commands.state.*;
import game.controller.FarmController;
import game.memento.ElementMemento;
import game.memento.FarmMemento;
import game.models.FarmModel;
import game.models.PositionModel;
import game.models.elements.ElementModel;
import game.models.elements.plants.BushModel;
import game.models.elements.plants.SeedModel;
import game.models.elements.plants.TreeModel;
import game.models.market.InventoryModel;
import game.state.*;
import game.views.FarmView;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CommandTest {

    @Test
    public void testFarmerMovementCommands() throws IOException {

        FarmModel farmModel = new FarmModel(60,25);

        //Initial position: middle of the screen
        assertEquals(30, farmModel.getFarmer().getPos().getX());
        assertEquals(12, farmModel.getFarmer().getPos().getY());

        //UpCommand: farmer moves up, his y coordinate is decremented by 1
        Command command = new UpCommand(farmModel);
        command.execute();
        PositionModel position = new PositionModel(30, 11);

        assertEquals(position.getX(), farmModel.getFarmer().getPos().getX());
        assertEquals(position.getY(), farmModel.getFarmer().getPos().getY());

        //LeftCommand: farmer moves up, his x coordinate is decremented by 1
        command = new LeftCommand(farmModel);
        command.execute();
        position = new PositionModel(29, 11);

        assertEquals(position.getX(), farmModel.getFarmer().getPos().getX());
        assertEquals(position.getY(), farmModel.getFarmer().getPos().getY());

        //RightCommand: farmer moves up, his x coordinate is incremented by 1
        command = new RightCommand(farmModel);
        command.execute();
        position = new PositionModel(30, 11);

        assertEquals(position.getX(), farmModel.getFarmer().getPos().getX());
        assertEquals(position.getY(), farmModel.getFarmer().getPos().getY());

        //DownCommand: farmer moves up, his y coordinate is incremented by 1
        command = new DownCommand(farmModel);
        command.execute();
        position = new PositionModel(30, 12);

        assertEquals(position.getX(), farmModel.getFarmer().getPos().getX());
        assertEquals(position.getY(), farmModel.getFarmer().getPos().getY());
    }

    @Test
    public void testActionCommand() throws IOException{

        FarmModel farmModel = new FarmModel(60,25);
        int seeds = farmModel.getInventory().getSeeds();

        // Checks if the farmer initially has 3 seeds
        assertEquals(3, seeds);

        //Farmer plants a seed next to him
        farmModel.removeFromInventory(new SeedModel(farmModel.getFarmer().getPos().getX() + 1,farmModel.getFarmer().getPos().getY()));

        seeds = farmModel.getInventory().getSeeds();

        //Checks if the farmer lost 1 seed
        assertEquals(2, seeds);

        //ActionCommand: picks the object next to the farmer -> it will incremented the number of seeds owned by the farmer
        Command command = new ActionCommand(farmModel);
        command.execute();

        seeds = farmModel.getInventory().getSeeds();

        //Checks if, after picking the seed, the farmer has 3 seeds again
        assertEquals(3, seeds);
    }

    @Test
    public void testMementoCommands() {
        FarmModel fm = new FarmModel(60,25);
        FarmMemento fs = fm.save();

        FarmController fcMock = Mockito.mock(FarmController.class);
        when(fcMock.getFarmModel()).thenReturn(fm);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                fcMock.setFarmState(fs);
                return null;
            }
        }).when(fcMock).setFarmState(fs);

        SaveCommand saveCommand = new SaveCommand(fcMock);
        saveCommand.execute();

        int counter = fm.getTrees().size();
        fm.addElement(new TreeModel());
        assertEquals(counter+1,fm.getTrees().size());

        RestoreCommand restoreCommand = new RestoreCommand(fs,fm);
        restoreCommand.execute();

        assertEquals(counter, fm.getTrees().size());
    }

    @Test
    public void testStateCommands1() throws IOException{

        //FISHING
        FarmController fcMock = Mockito.mock(FarmController.class);

        Command command = new FishingCommand(fcMock);
        when(fcMock.getState()).thenReturn(new GameState(fcMock));

        doAnswer(invocationOnMock -> {
            fcMock.setState(new FishingState(fcMock));
            return null;
        }).when(fcMock).setState(new FishingState(fcMock));
        command.execute();

        when(fcMock.getState()).thenReturn(new FishingState(fcMock));
        assertThat(fcMock.getState(), instanceOf(FishingState.class));

        when(fcMock.getNumber()).thenReturn(-1);
        when(fcMock.getState()).thenReturn(new FishingState(fcMock));
        doNothing().when(fcMock).setState(new GameState(fcMock));
        command.execute();
        when(fcMock.getNumber()).thenReturn(1);
        FarmModel fmMock = Mockito.mock(FarmModel.class);
        when(fcMock.getFarmModel()).thenReturn(fmMock);
        doNothing().when(fmMock).fish();
        command.execute();

        FishingState fishingState = new FishingState(fcMock);
        fishingState.doAction(fcMock);
        assertEquals("Fishing", fishingState.getName());

        //HELP
        State state = new HelpGameState(fcMock);
        assertEquals("Help Game", state.getName());

      //  command = new HelpGameCommand(fcMock);
        when(fcMock.getState()).thenReturn(new GameState(fcMock));

        State finalState = state;
        doAnswer(invocationOnMock -> {
            fcMock.setState(finalState);
            return null;
        }).when(fcMock).setState(new HelpGameState(fcMock));
        //command.execute();
        state.doAction(fcMock);
        when(fcMock.getState()).thenReturn(state);
        assertThat(fcMock.getState(), instanceOf(HelpGameState.class));

        when(fcMock.getNumber()).thenReturn(-1);
        when(fcMock.getState()).thenReturn(new HelpGameState(fcMock));
        doNothing().when(fcMock).setState(new GameState(fcMock));
        command.execute();

        HelpGameState helpGameState = new HelpGameState(fcMock);
        helpGameState.doAction(fcMock);
        command = new HelpMenuCommand(fcMock);
        when(fcMock.getState()).thenReturn(new MenuState(fcMock));
        doAnswer(invocationOnMock -> {
            fcMock.setState(new HelpGameState(fcMock));
            return null;
        }).when(fcMock).setState(new HelpGameState(fcMock));
        command.execute();

        state = new HelpMenuState(fcMock);
        assertEquals("Help Menu", state.getName());
        when(fcMock.getState()).thenReturn(new MenuState(fcMock));
        state.doAction(fcMock);
        when(fcMock.getState()).thenReturn(state);
        when(fcMock.getNumber()).thenReturn(-1);
        doAnswer(invocationOnMock -> {
            fcMock.setState(new MenuState(fcMock));
            return null;
        }).when(fcMock).setState(new MenuState(fcMock));
        state.doAction(fcMock);
        HelpMenuState helpMenuState = new HelpMenuState(fcMock);
        helpMenuState.doAction(fcMock);

        GameState gameState = new GameState(fcMock);
        assertEquals("Game", gameState.getName());
        QuitCommand quitCommandMock = Mockito.mock(QuitCommand.class);
        when(fcMock.getCommand()).thenReturn(quitCommandMock);
        doNothing().when(quitCommandMock).execute();
        gameState.doAction(fcMock);

        //MENU
        MenuState menuState = new MenuState(fcMock);
        assertEquals("Menu", menuState.getName());
        command = new MenuCommand(fcMock);
        when(fcMock.getNumber()).thenReturn(1);
        doAnswer(invocationOnMock -> {
            fcMock.setState(gameState);
            return null;
        }).when(fcMock).setState(gameState);
        menuState.doAction(fcMock);

        when(fcMock.getNumber()).thenReturn(3);
        doAnswer(invocationOnMock -> {
            fcMock.setState(helpMenuState);
            return null;
        }).when(fcMock).setState(helpMenuState);
        menuState.doAction(fcMock);

        when(fcMock.getNumber()).thenReturn(2);
        FarmModel farmModel = new FarmModel(60,25);
        when(fcMock.getFarmModel().readFile()).thenReturn(farmModel.save().getState());
        doNothing().when(fmMock).restore(farmModel.save());
        doAnswer(invocationOnMock -> {
            fcMock.setState(gameState);
            return null;
        }).when(fcMock).setState(gameState);
        menuState.doAction(fcMock);
    }

    @Test
    public void testStateCommands2() throws IOException {

        // INVENTORY
        FarmController fcMock = Mockito.mock(FarmController.class);
        GameState gameState = new GameState(fcMock);

        InventoryState inventoryState = new InventoryState(fcMock);
        assertEquals("Inventory", inventoryState.getName());
        InventoryCommand inventoryCommand = new InventoryCommand(fcMock);

        when(fcMock.getState()).thenReturn(gameState);
        doAnswer(invocationOnMock -> {
            fcMock.setState(inventoryState);
            return null;
        }).when(fcMock).setState(inventoryState);
        inventoryState.doAction(fcMock);

        when(fcMock.getState()).thenReturn(inventoryState);
        when(fcMock.getNumber()).thenReturn(-1);
        doAnswer(invocationOnMock -> {
            fcMock.setState(gameState);
            return null;
        }).when(fcMock).setState(gameState);
        inventoryCommand.execute();

        // MARKET
        MarketState marketState = new MarketState(fcMock);
        assertEquals("Market", marketState.getName());
        StallsCommand stallsCommand = new StallsCommand(fcMock);

        when(fcMock.getState()).thenReturn(gameState);
        doAnswer(invocationOnMock -> {
            fcMock.setState(marketState);
            return null;
        }).when(fcMock).setState(marketState);
        inventoryState.doAction(fcMock);

        when(fcMock.getState()).thenReturn(marketState);
        when(fcMock.getNumber()).thenReturn(-1);
        doAnswer(invocationOnMock -> {
            fcMock.setState(gameState);
            return null;
        }).when(fcMock).setState(gameState);
        stallsCommand.execute();

        when(fcMock.getNumber()).thenReturn(5);
        FarmModel fmMock = Mockito.mock(FarmModel.class);
        when(fcMock.getFarmModel()).thenReturn(fmMock);
        when(fmMock.buyMarket(5)).thenReturn(true);
        stallsCommand.execute();
        when(fmMock.buyMarket(5)).thenReturn(false);
        stallsCommand.execute();
        when(fcMock.getNumber()).thenReturn(0);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("BUSH",0);
        InventoryModel inventoryMock = Mockito.mock(InventoryModel.class);
        when(fmMock.getInventory()).thenReturn(inventoryMock);
        when(inventoryMock.indexSelection(true)).thenReturn(map);
     //   when(fmMock.getInventory().indexSelection(true)).thenReturn(map);
        when(fmMock.sellMarket(map,0)).thenReturn(true);
        marketState.doAction(fcMock);
        //stallsCommand.execute();
        when(fmMock.sellMarket(map,0)).thenReturn(false);
        stallsCommand.execute();
    }

    @Test
    public void testStateCommands3() throws IOException {

        FarmController fcMock = Mockito.mock(FarmController.class);

        PlantingState plantingState = new PlantingState(fcMock);
        assertEquals("Planting", plantingState.getName());
        PlantCommand plantCommand = new PlantCommand(fcMock);

        FarmModel fmMock = Mockito.mock(FarmModel.class);
        InventoryModel inventoryMock = Mockito.mock(InventoryModel.class);
        when(fcMock.getFarmModel()).thenReturn(fmMock);
        when(fmMock.getInventory()).thenReturn(inventoryMock);

        List<ElementModel> list = new ArrayList<>();
        when(inventoryMock.getPlants()).thenReturn(list);
        doAnswer(invocationOnMock -> {
            fcMock.setState(new GameState(fcMock));
            return null;
        }).when(fcMock).setState(new GameState(fcMock));
        plantingState.doAction(fcMock);

        list.add(new BushModel());

        when(fcMock.getState()).thenReturn(new GameState(fcMock));
        doAnswer(invocationOnMock -> {
            fcMock.setState(plantingState);
            return null;
        }).when(fcMock).setState(plantingState);
        plantCommand.execute();

        when(fcMock.getState()).thenReturn(plantingState);
        when(fcMock.getNumber()).thenReturn(-1);
        plantingState.doAction(fcMock);

        when(fcMock.getNumber()).thenReturn(0);
        HashMap<String, Integer>map = new HashMap<>();
        map.put("BUSH",0);
        when(inventoryMock.indexSelection(true)).thenReturn(map);
        when(fmMock.removeFromInventory(new BushModel())).thenReturn(true);
        plantingState.doAction(fcMock);

        QuitState quitState = new QuitState(fcMock);
        QuitState qsMock = Mockito.mock(QuitState.class);
        QuitCommand quitCommand = new QuitCommand(fcMock);
        QuitCommand qcMock = Mockito.mock(QuitCommand.class);
        assertEquals("Quit", quitState.getName());
        doNothing().when(qcMock).execute();
        qsMock.doAction(fcMock);
        verify(qsMock, times(1)).doAction(fcMock);
        qcMock.execute();
        verify(qcMock, times(1)).execute();



    }
}
