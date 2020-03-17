import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element {

    public Hero(int x, int y) {
        super(x, y);
    }
    public Position moveUp() {
        return new Position(super.getPosition().getx(), super.getPosition().gety() - 1);
    }
    public Position moveDown() {
        return new Position(super.getPosition().getx(), super.getPosition().gety() + 1);
    }
    public Position moveLeft() {
        return new Position(super.getPosition().getx() - 1, super.getPosition().gety());
    }
    public Position moveRight() {
        return new Position(super.getPosition().getx() + 1, super.getPosition().gety());
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#80aaff"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getPosition().getx(),super.getPosition().gety()), "X");
    }

}
