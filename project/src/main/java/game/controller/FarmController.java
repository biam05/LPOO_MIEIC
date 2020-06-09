package game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.commands.*;
import game.commands.memento.RestoreCommand;
import game.commands.memento.SaveCommand;
import game.commands.movement.*;
import game.commands.state.*;
import game.models.*;
import game.memento.FarmMemento;
import game.state.MenuState;
import game.state.QuitState;
import game.state.State;
import game.views.FarmView;
import game.auxiliary.*;

import java.io.IOException;

public class FarmController {
    private FarmModel farmModel;
    private FarmView farmView;
    private FarmMemento farmState;
    private State state;

    public FarmController(FarmModel farmModel, FarmView farmView) {
        this.farmModel = farmModel;
        this.farmView = farmView;
        this.farmState = farmModel.save();
        this.state = new MenuState(this);
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public FarmView getFarmView() {
        return farmView;
    }
    public FarmModel getFarmModel() { return farmModel; }
    public void setFarmModel(FarmModel farmModel) {
        this.farmModel = farmModel;
    }
    public void setFarmState(FarmMemento farmState) { this.farmState = farmState;}

    public Command getCommand() throws IOException {
        while (true) {
            KeyStroke key = farmView.getScreen().readInput();

            if (key.getKeyType() == KeyType.Escape) return new QuitCommand(this);

            if (key.getKeyType() == KeyType.ArrowUp) return new UpCommand(farmModel);
            if (key.getKeyType() == KeyType.ArrowRight) return new RightCommand(farmModel);
            if (key.getKeyType() == KeyType.ArrowDown) return new DownCommand(farmModel);
            if (key.getKeyType() == KeyType.ArrowLeft) return new LeftCommand(farmModel);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'e' || key.getCharacter() == 'E')) return new ActionCommand(farmModel);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'i' || key.getCharacter() == 'I')) return new InventoryCommand(this);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 's' || key.getCharacter() == 'S')) return new SaveCommand(this);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'r' || key.getCharacter() == 'R')) return new RestoreCommand(farmState, farmModel);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'h' || key.getCharacter() == 'H')) return new HelpGameCommand(this);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'p' || key.getCharacter() == 'P')) return new PlantCommand(this);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'm' || key.getCharacter() == 'M'))
                if(collision.rugCollision(farmModel, farmModel.getFarmer().getPos()))
                    return new StallsCommand(this);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'l' || key.getCharacter() == 'L'))
                if(collision.deckCollision(farmModel, farmModel.getFarmer().getPos()))
                    return new FishingCommand(this);
        }
    }
    public int getNumber() throws IOException {
        while (true) {
            KeyStroke key = farmView.getScreen().readInput();

            if (key.getKeyType() == KeyType.Escape) return -1;

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '0') return 0;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '1') return 1;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '2') return 2;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '3') return 3;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '4') return 4;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '5') return 5;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '6') return 6;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '7') return 7;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '8') return 8;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == '9') return 9;

        }
    }

    public void run() throws IOException {

        while (true) {
            farmView.drawGame(farmModel, state);
            state.doAction(this);
            if(this.state instanceof QuitState) break;
        }
    }
}
