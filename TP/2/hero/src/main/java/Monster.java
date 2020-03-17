import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Element {

    public Monster(int x, int y) {
        super(x, y);
    }
    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#66ff66"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getPosition().getx(), super.getPosition().gety()), "U");
    }
    public Position move()
    {
        Random random = new Random();
        int i = random.nextInt(4);
        Position position = super.getPosition();
        switch (i)
        {
            case 0:
               position = new Position(super.getPosition().getx(), super.getPosition().gety() - 1);
               break;
            case 1:
                position = new Position(super.getPosition().getx(), super.getPosition().gety() + 1);
                break;
            case 2:
                position = new Position(super.getPosition().getx() + 1, super.getPosition().gety());
                break;
            case 3:
                position = new Position(super.getPosition().getx() - 1, super.getPosition().gety());
                break;
            default:
                break;
        }
        return position;
    }
}
