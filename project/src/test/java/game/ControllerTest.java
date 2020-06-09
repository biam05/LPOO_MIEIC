package game;

import game.controller.FarmController;
import game.models.FarmModel;
import game.state.GameState;
import game.state.HelpMenuState;
import game.state.MenuState;
import game.views.FarmView;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ControllerTest {

    @Test
    public void controller() throws IOException {

        FarmView fvMock = Mockito.mock(FarmView.class);
        FarmModel fm = new FarmModel(60,25);

        FarmController fc = new FarmController(fm, fvMock);

        assertThat(fc.getState(), instanceOf(MenuState.class));
        fc.setState(new HelpMenuState(fc));
        assertThat(fc.getState(), instanceOf(HelpMenuState.class));

        assertThat(fc.getFarmView(), instanceOf(FarmView.class));
        assertThat(fc.getFarmModel(), instanceOf(FarmModel.class));

        fc.setFarmModel(fm);

        fc.setFarmState(fm.save());

        FarmController fcMock = Mockito.mock(FarmController.class);
        when(fcMock.getState()).thenReturn(new GameState(fcMock));
        when(fcMock.getFarmView()).thenReturn(fvMock);
        when(fcMock.getFarmModel()).thenReturn(fm);
        doNothing().when(fcMock).run();
        //verify(fcMock, times(1)).getState().doAction(fcMock);
        //verify(fcMock, times(1)).getFarmView().drawGame(fcMock.getFarmModel(),fcMock.getState());

    }
}
