import static java.lang.Character.*;

public class StringCaseChanger implements StringTransformer {
    public StringCaseChanger() {
    }

    @Override
    public void execute(StringDrink drink) {
        String result = "";
        for(int i = 0; i < drink.getText().length(); i++){
            char c = drink.getText().charAt(i);
            if(isLowerCase(c))
                result += toUpperCase(c);

            else
                result += toLowerCase(c);
        }
        drink.setText(result);
    }

    @Override
    public void undo(StringDrink drink) {
        String result = "";
        for(int i = 0; i < drink.getText().length(); i++){
            char c = drink.getText().charAt(i);
            if(isLowerCase(c))
                result += toUpperCase(c);

            else
                result += toLowerCase(c);
        }
        drink.setText(result);
    }
}
