package org.cookbook.service;

import org.cookbook.io.JsonFileHandler;
import org.cookbook.model.Recipe;
import org.cookbook.ui.EditorController;

import java.io.File;
import java.util.HashSet;

// This service should handle the data between GUI and saving, I.E taking string inputs and making them recipes
// Aka the "Model" part of the model-view-controller pattern.
public class CookbookService {
    private static EditorController editorController = new EditorController();
    private static HashSet<Recipe> loadedRecipes = new HashSet<>();
    // RefreshRecipes should add any new recipes to loadedRecipes and remove any that don't exist anymore
    public static HashSet<Recipe> GetLoadedRecipes(){
        if (loadedRecipes.isEmpty()){
            loadedRecipes = LoadRecipesFromFile();
        }
        return loadedRecipes;
    }
    public static HashSet<Recipe> GetRecipesByName(String searchQuery){
        HashSet<Recipe> loadedRecipes = GetLoadedRecipes();
        HashSet<Recipe> matchingRecipes = new HashSet<>();
        for (Recipe recipe : loadedRecipes){
            System.out.println("Searching recipes with query: " + searchQuery);
            if (recipe.getName().toLowerCase().contains(searchQuery.toLowerCase())){
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }
    private static HashSet<Recipe> LoadRecipesFromFile() {
        HashSet<Recipe> returnRecipe = new HashSet<>();
        File dir = new File(System.getenv("LOCALAPPDATA")+"\\recipes/");
        dir.mkdir();
        System.out.println(dir.getAbsolutePath());
        File[] directoryFiles = dir.listFiles();

        if (directoryFiles != null) {
            for (File recipe : directoryFiles) {
                System.out.println("File name: " + recipe.getName());
                Recipe loadedRecipe = JsonFileHandler.LoadRecipeFromFile(recipe);

                if (loadedRecipe != null) {
                    System.out.println("Loaded Recipe Name: " + loadedRecipe.getName());
                    returnRecipe.add(loadedRecipe);
                } else {
                    // Handle the case where loadedRecipe is null, e.g., log an error message.
                    System.err.println("Failed to load recipe from file: " + recipe.getName());
                }
            }
            System.out.println("Loaded Recipes: " + returnRecipe);
        } else {
            // Handle the case where directoryFiles is null, e.g., log an error message.
            System.err.println("No recipe files found in the 'recipes' directory.");
        }

        return returnRecipe;
    }
    public static void SaveRecipe(Recipe recipe){
        JsonFileHandler.SaveRecipeToFile(recipe);
        loadedRecipes.remove(recipe); // if it has an old version of the recipe
        loadedRecipes.removeIf(i -> i.getName().equals(recipe.getName()));
        loadedRecipes.add(recipe);
    }
    public static void DeleteRecipe(Recipe recipe){
        String recipeName = recipe.getName();
        File toDelete = new File(System.getenv("LOCALAPPDATA")+"\\recipes" + recipeName + ".json");
        loadedRecipes.remove(recipe);
        JsonFileHandler.DeleteRecipe(toDelete);
        System.out.println("Removed recipe " + recipeName);
        //if (toDelete.exists()){
        //    JsonFileHandler.DeleteRecipe(toDelete);
        //    for (Recipe i : loadedRecipes){
        //        if (i.getName().equals(recipeName)){
        //            loadedRecipes.remove(i);
        //        }
        //    }
        //} else {
        //    System.out.println("Failed to find recipe " + recipeName + " for deletion");
        //}
    }
}