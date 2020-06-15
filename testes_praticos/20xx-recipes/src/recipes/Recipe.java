package recipes;

import java.util.ArrayList;

public class Recipe {
    protected String nome;
    protected ArrayList<RecipeStep> recipes;

    public Recipe(String nome) {
        this.nome = nome;
        this.recipes = new ArrayList<>();
    }

    public int getStepCount() {
        return recipes.size();
    }

    public void addStep(RecipeStep r) {
        for(RecipeStep recipe : recipes)
            if(r.getName() == recipe.getName())
                return;
        recipes.add(r);
    }
}
