package rules;

import data.ArenaModel;
import gui.ArenaView;

import java.io.IOException;

public class ArenaController {
    private ArenaView gui;
    private ArenaModel arena;

    public ArenaController(ArenaView gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
    }

    public void start() throws IOException {
        while (true) {
            ArenaView.COMMAND command = gui.getCommand();

            if (command == ArenaView.COMMAND.UP)
                arena.setHeroPosition(arena.getHeroPosition().up());
            if (command == ArenaView.COMMAND.RIGHT)
                arena.setHeroPosition(arena.getHeroPosition().right());
            if (command == ArenaView.COMMAND.DOWN)
                arena.setHeroPosition(arena.getHeroPosition().down());
            if (command == ArenaView.COMMAND.LEFT)
                arena.setHeroPosition(arena.getHeroPosition().left());
        }
    }
}
