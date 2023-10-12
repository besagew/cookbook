package org.cookbook.io;

import org.cookbook.model.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void SaveRecipeToFile(Recipe recipe){
        // this may need to return the File it creates later.
        String newFileName = recipe.getInternalName() + ".json";
        String pathName = System.getenv("LOCALAPPDATA")+"\\recipes/" + newFileName;
        try {
            objectMapper.writeValue(new File(pathName), recipe);
            System.out.println("Saved recipe " + recipe.getInternalName());
        }catch(IOException e){
            System.out.println("Error when saving recipe: " + e);
        }
    }
    public static void DeleteRecipe(File recipeJson){
        if (recipeJson.delete()){
            System.out.println("Deleted recipe: " + recipeJson.getName());
        }else{
            System.out.println("Failed to delete recipe: " + recipeJson.getName());
        }
    }
    public static Recipe LoadRecipeFromFile(File recipeJson){
        Recipe loadedRecipe;
        try{
            loadedRecipe = objectMapper.readValue(recipeJson,Recipe.class);
        }catch(IOException e){
            System.out.println("Error when loading recipe: " + e);
            return null;
        }
        return loadedRecipe;
    }
}
