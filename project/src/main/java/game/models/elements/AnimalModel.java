package game.models.elements;

import game.models.PositionModel;

import java.util.Random;

public class AnimalModel extends ElementModel {
    public AnimalModel(int x, int y) {
        super(x, y);
    }
    public AnimalModel() {super(1,1);}

    public PositionModel move() {
        Random random = new Random();
        int i = random.nextInt(4);
        PositionModel position = super.getPos();
        switch (i) {
            case 0:
                position = new PositionModel(super.getPos().getX(), super.getPos().getY() - 1);
                break;
            case 1:
                position = new PositionModel(super.getPos().getX(), super.getPos().getY() + 1);
                break;
            case 2:
                position = new PositionModel(super.getPos().getX() + 1, super.getPos().getY());
                break;
            case 3:
                position = new PositionModel(super.getPos().getX() - 1, super.getPos().getY());
                break;
            default:
                break;
        }
        return position;
    }

}
