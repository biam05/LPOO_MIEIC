package game.auxiliary;

import java.util.ArrayList;
import java.util.List;

public enum TYPE {

    TREE, BUSH, SEED, ANIMAL, MONEY, FISHBAIT, FLOWER, UPGRADE, FENCE;

    private String name;

    static {
        TREE.name = "TREE";
        SEED.name = "SEED";
        BUSH.name = "BUSH";
        FLOWER.name = "FLOWER";
        FENCE.name = "FENCE";
        UPGRADE.name = "UPGRADE";
        MONEY.name = "";
        FISHBAIT.name = "";
        ANIMAL.name = "";
    }

    public String getName() {return name; }

    public static List<TYPE> getTypes(boolean all) {
        List<TYPE> types = new ArrayList<>();
        types.add(TREE);
        types.add(BUSH);
        types.add(SEED);
        types.add(FLOWER);
        types.add(FISHBAIT);
        types.add(MONEY);
        types.add(FENCE);
        if(all) types.add(UPGRADE);
        types.add(ANIMAL);
        return types;
    }
}
