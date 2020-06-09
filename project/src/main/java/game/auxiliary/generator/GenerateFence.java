package game.auxiliary.generator;

import game.models.*;
import game.models.elements.FenceModel;

import java.util.ArrayList;
import java.util.List;

public class GenerateFence implements RandomGenerator {

    private boolean limitFence;

    public GenerateFence(boolean limitFence) {
        this.limitFence = limitFence;
    }
    public GenerateFence() {this.limitFence = false;}

    @Override
    public List<FenceModel> generate(FarmModel fm, int num) {

        List<FenceModel> walls = new ArrayList<>();

        if(limitFence) {
            for (int w = 0; w < fm.getWidth(); w++) {
                walls.add(new FenceModel(w,0, true));
                walls.add(new FenceModel(w,fm.getHeight()-1, true));
            }

            for (int h = 1; h < fm.getHeight() - 1; h++) {
                walls.add(new FenceModel(0, h, true));
                walls.add(new FenceModel(fm.getWidth() - 1, h, true));
            }
        }
        else {
            for (int w = 10; w <= 20; w++) {
                walls.add(new FenceModel(w,5, false));
                walls.add(new FenceModel(w,15, false));
            }

            for (int h = 5; h <= 15; h++) {
                walls.add(new FenceModel(10, h, false));
                walls.add(new FenceModel(20, h, false));
            }
        }

        return walls;
    }
}
