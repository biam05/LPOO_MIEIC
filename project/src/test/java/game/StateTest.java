package game;

import game.commands.Command;
import game.commands.movement.ActionCommand;
import game.commands.state.FishingCommand;
import game.commands.state.HelpGameCommand;
import game.commands.state.HelpMenuCommand;
import game.commands.state.MenuCommand;
import game.controller.FarmController;
import game.models.FarmModel;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import game.state.*;
import game.views.FarmView;
import java.io.IOException;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StateTest {

    @Test
    public void State() throws IOException {

        FarmController mockFarmController = Mockito.mock(FarmController.class);
        when(mockFarmController.getCommand()).thenReturn(new MenuCommand(mockFarmController));
        State mockState = Mockito.mock(State.class);
        Command mockCommand = Mockito.mock(Command.class);

  //      FarmController farmController = new FarmController(new FarmModel(60,25), new FarmView(60,25));

//        assertThat(farmController.getState(), instanceOf(MenuState.class));
        assertThat(mockFarmController.getCommand(), instanceOf(MenuCommand.class));

        /* doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                mockFarmController.setState(new GameState(mockFarmController));
                return null;
            }
        }).when(mockFarmController).setState(new GameState(mockFarmController));*/
        doNothing().when(mockState).doAction(mockFarmController);
        doNothing().when(mockCommand).execute();

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                when(mockFarmController.getState()).thenReturn(new GameState(mockFarmController));
                return null;
            }
        }).when(mockFarmController).setState(new GameState(mockFarmController));
        mockFarmController.setState(new GameState(mockFarmController));

 /*       farmController.setState(new GameState(mockFarmController));
        assertThat(farmController.getState(), instanceOf(GameState.class));
        assertThat(farmController.getFarmView(), instanceOf(FarmView.class));
        assertThat(farmController.getFarmModel(), instanceOf(FarmModel.class)); */

        when(mockFarmController.getCommand()).thenReturn(new FishingCommand(mockFarmController));
        assertThat(mockFarmController.getCommand(), instanceOf(FishingCommand.class));
     //   farmController.setState(new FishingState(farmController));
       // assertThat(farmController.getState(), instanceOf(FishingState.class));

        when(mockFarmController.getFarmModel()).thenReturn(new FarmModel(60,25));
        FarmModel fm = mockFarmController.getFarmModel();
        when(mockFarmController.getCommand()).thenReturn(new ActionCommand(fm));
        assertThat(mockFarmController.getCommand(), instanceOf(ActionCommand.class));
       // farmController.setState(new InventoryState(farmController));
//        assertThat(farmController.getState(), instanceOf(InventoryState.class));

        when(mockFarmController.getCommand()).thenReturn(new HelpGameCommand(mockFarmController));
        assertThat(mockFarmController.getCommand(), instanceOf(HelpGameCommand.class));
        when(mockFarmController.getCommand()).thenReturn(new HelpMenuCommand(mockFarmController));
        assertThat(mockFarmController.getCommand(), instanceOf(HelpMenuCommand.class));

    }
}
