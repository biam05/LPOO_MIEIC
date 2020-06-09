package game.auxiliary.generator;

import game.auxiliary.collision;
import game.models.*;
import game.models.elements.GiftModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateGift implements RandomGenerator {

    @Override
    public List<GiftModel> generate(FarmModel fm, int num) {
        List<GiftModel> gifts = new ArrayList<>();
        if(fm.getUpgradeCounter()>=2) {
            if(fm.getLake().getWater().size() != 0 && fm.getTimeCounter() % 200 == 0) {
                for (int i = 0; i<num; i++) {
                    Random random = new Random();
                    PositionModel position = new PositionModel(random.nextInt(fm.getWidth() - 2) + 1, random.nextInt(fm.getHeight() - 2) + 1);
                    if(collision.marketCollision(fm,position) && !collision.rugCollision(fm,position) && collision.elementCollision(fm,position)){
                        GiftModel gift = new GiftModel();
                        gift.setPos(position);
                        gifts.add(gift);
                    }
                }
            }
        }
        return gifts;
    }
}
