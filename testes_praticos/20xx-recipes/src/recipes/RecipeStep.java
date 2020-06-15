package recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RecipeStep extends Ingredient {
    protected String action;
    protected HashMap<Ingredient, Float> ingredients;

    public RecipeStep(String name, String action) throws IllegalArgumentException {
        if(name == null || action == null) throw new IllegalArgumentException();
        this.name = name;
        this.action = action;
        this.ingredients = new HashMap<Ingredient, Float>();
    }

    public String getAction() {
        return action;
    }

    public void addIngredient(Ingredient ingredient, float quantity) {
        this.ingredients.putIfAbsent(ingredient, quantity);
    }

    public int getIngredientCount() {
        return this.ingredients.keySet().size();
    }

    public double getQuantity(Ingredient ingredient) {
        double total = 0f;
        for (Map.Entry<Ingredient, Float> entry : this.ingredients.entrySet()) {
            if(entry.getKey() instanceof RecipeStep){
                if(entry.getKey().equals(ingredient))
                    total += entry.getValue();
                total += entry.getValue() * ((RecipeStep) entry.getKey()).getQuantity(ingredient);
            }
            else{
                if(entry.getKey().equals(ingredient))
                    total += entry.getValue();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        String result = "to make "  + name + ", " + action + " ";
        for (Map.Entry<Ingredient, Float> entry : this.ingredients.entrySet()) {
            if(entry.getKey() instanceof SimpleIngredient)
                result += entry.getValue() + " " + entry.getKey() + ", ";
            else
                result += entry.getValue() + " " + entry.getKey().getName() + ", ";
        }
        return result.substring(0, result.length() - 2);
    }
}
