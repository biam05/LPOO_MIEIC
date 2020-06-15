package recipes;

public class SimpleIngredient extends Ingredient{

    public SimpleIngredient(String name) throws IllegalArgumentException{
        if (name == null) throw new IllegalArgumentException();
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
