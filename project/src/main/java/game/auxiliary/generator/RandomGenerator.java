package game.auxiliary.generator;

import game.models.FarmModel;
import java.util.List;

public interface RandomGenerator {
    List<?> generate(FarmModel fm, int num);

}
