package org.cookbook;

import org.cookbook.io.JsonFileHandler;
import org.cookbook.model.Ingredient;
import org.cookbook.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get("recipes"));
        String lettuce = "Lettuce - 3 Leaves\n";
        String burgerMeat = "Evil Meat - 1 Patty\n";

        Recipe burgerRecipe = new Recipe("Burger",lettuce + burgerMeat,"1. Cook it\n2. Eat it");
        System.out.flush();
        System.out.println(burgerRecipe);

        System.out.println("Saving burger to file.");
        JsonFileHandler.SaveRecipeToFile(burgerRecipe);
        System.out.println("Loading burger from file.");
        Recipe loadedRecipe = JsonFileHandler.LoadRecipeFromFile(new File("recipes/burger.json"));
        System.out.println(loadedRecipe);
    }
}