package game.models.elements;

import java.util.List;
import java.util.Random;
import game.auxiliary.TYPE;

public class GiftModel extends ElementModel {

    private TYPE type;
    private int quantity;

    public GiftModel() {
        super(1, 1);
        Random random = new Random();
        int index, quantity;
        index = random.nextInt(8);
        List<TYPE> types = TYPE.getTypes(false);
        this.type = types.get(index);
        if(this.type == TYPE.ANIMAL) quantity = random.nextInt(2)+2;
        else quantity = random.nextInt(10)+2;
        this.quantity = quantity;
    }

    public TYPE getType() { return this.type; }
    public int getQuantity() { return this.quantity; }

}
