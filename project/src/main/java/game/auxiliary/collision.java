package game.auxiliary;

import game.models.*;
import game.models.elements.lake.DeckModel;
import game.models.elements.ElementModel;

import java.util.List;

public class collision {

    static public boolean elementCollision(FarmModel fm, PositionModel position){
        List<ElementModel> elements = fm.getAllElements();
        for (ElementModel element : elements){
            if(element.getClass()!= DeckModel.class)
                if(element.getPos().equals(position)) return false;
        }
        return true;
    }
    static public boolean marketCollision(FarmModel fm, PositionModel position){
        return (position.getX() <= (fm.getWidth() - 14) || position.getX() >= (fm.getWidth() - 13 + fm.getWidth() / 7) || position.getY() <= 0 || position.getY() >= (fm.getHeight() / 6));
    }
    static public boolean rugCollision(FarmModel fm, PositionModel position){
        return position.getX() > (fm.getWidth() - 12) && position.getX() < (fm.getWidth() - 12 + 5) && position.getY() == (fm.getHeight() / 6);
    }
    static public boolean deckCollision(FarmModel fm, PositionModel position){
        return position.getX() >= (fm.getWidth() - 19) && position.getX() <= (fm.getWidth() - 19 +1) && position.getY() == (fm.getHeight() - 6);
    }

}
