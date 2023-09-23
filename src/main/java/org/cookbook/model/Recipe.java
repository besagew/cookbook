package org.cookbook.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
// We use "getInternalName" for file saving and Jackson infers that InternalName is a property when saving
// However it ISN'T so it errors. JsonIgnoreProperties is here to stop this.

// Support for recipe "tags" would be awesome, consider adding them after GUIS are made
public class Recipe {
    private final double Version = 1.0;
    private String name;
    private String ingredients;
    private String instructions;
    private Set<String> tags;
    public Recipe(){}
    public Recipe(String name, String ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    public String getName() {
        return name;
    }
    public String getInternalName(){
        return name.replaceAll(" ", "_").toLowerCase();
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIngredients() {return ingredients;}
    public String getInstructions() {
        return instructions;
    }
    @Override
    public String toString(){
        StringBuilder out;
        out = new StringBuilder(name + " Recipe:\n");
        out.append("Ingredients:\n").append(ingredients);
        out.append("Steps:\n").append(instructions);

        return out.toString();
    }
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof Recipe otherRecipe)) {
            return false;
        }
        //We can assume it's a recipe
        boolean sameName = Objects.equals(this.name, otherRecipe.getName());
        boolean sameIngredients = Objects.equals(this.ingredients, otherRecipe.getIngredients());
        boolean sameInstructions = Objects.equals(this.instructions, otherRecipe.getIngredients());
        return (sameName && sameIngredients && sameInstructions);
    }
}
