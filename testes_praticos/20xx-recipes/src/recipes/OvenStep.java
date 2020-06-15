package recipes;

import java.util.HashMap;

public class OvenStep extends RecipeStep{
    protected int temperature;
    protected HashMap<Ingredient, Integer> main;

    public OvenStep(String name, String action, int temperature) {
        super(name, action);
        this.temperature = temperature;
        this.main = new HashMap<Ingredient, Integer>();
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }

    public int getTemperature() {
        return temperature;
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        this.main.putIfAbsent(ingredient, quantity);
    }
}
