package game;

import game.models.FarmModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class LakeModelTest {

    @Test
    public void laketest(){
        FarmModel farmModel = new FarmModel(60, 25);

        assertEquals(0, farmModel.getUpgradeCounter());

        farmModel.getFarmer().setBalance(500);

        farmModel.buyMarket(5);
        assertEquals(1, farmModel.getUpgradeCounter());

        farmModel.buyMarket(5);
        assertEquals(2, farmModel.getUpgradeCounter());

        farmModel.buyMarket(5);
        assertEquals(3, farmModel.getUpgradeCounter());

        assertEquals(40, farmModel.getLake().getTimeInterval());

        farmModel.getLake().setPossibleFishing(true);
        assertTrue(farmModel.getLake().isPossibleFishing());

        assertEquals(-1, farmModel.getLake().getLastFished());

        farmModel.getLake().canFish(farmModel.getTimeCounter());

        assertFalse(farmModel.getLake().isPossibleFishing());
        assertEquals(farmModel.getTimeCounter(), farmModel.getLake().getLastFished());

        assertFalse(farmModel.getLake().canFish(farmModel.getTimeCounter()));

        assertFalse((farmModel.getLake().getLastFished() + farmModel.getLake().getTimeInterval()) <= farmModel.getTimeCounter());

        farmModel.setTimeCounter(100);

        assertTrue((farmModel.getLake().getLastFished() + farmModel.getLake().getTimeInterval()) <= farmModel.getTimeCounter());

        assertTrue(farmModel.getLake().canFish(farmModel.getTimeCounter()));
        assertEquals(farmModel.getTimeCounter(), farmModel.getLake().getLastFished());
        assertFalse(farmModel.getLake().isPossibleFishing());

        farmModel.getLake().removeAll();
        assertEquals(0, farmModel.getLake().getDeck().size());
        assertEquals(0, farmModel.getLake().getWater().size());
    }
}
