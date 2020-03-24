import static java.lang.Character.*;

public class StringReplacer implements StringTransformer{
    char first, second;

    public StringReplacer(char first, char second) {
        this.first = first;
        this.second = second;
    }

    public char getFirst() {
        return first;
    }

    public void setFirst(char first) {
        this.first = first;
    }

    public char getSecond() {
        return second;
    }

    public void setSecond(char second) {
        this.second = second;
    }

    @Override
    public void execute(StringDrink drink) {
        drink.setText(drink.getText().replace(first, second));
    }

    @Override
    public void undo(StringDrink drink) {
        drink.setText(drink.getText().replace(second, first));
    }
}
