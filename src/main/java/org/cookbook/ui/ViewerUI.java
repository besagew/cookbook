package org.cookbook.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.cookbook.model.Recipe;

import java.io.IOException;

public class ViewerUI {
    private Recipe recipe;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ingredientLabel;
    @FXML
    private Label instructionLabel;
    public ViewerUI(){

    }
    public ViewerUI(Recipe recipe){
        this.recipe = recipe;
    }

    public void open() throws IOException {
        System.out.println("Opening recipe");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/RecipeViewer.fxml"));
        // This setup only works if you declare the controller out here instead of in the FXML. I think it has something to do with the new stages. No clue
        fxmlLoader.setController(this);

        Scene scene = new Scene(fxmlLoader.load(), 440, 440);
        Stage stage = new Stage();
        stage.setTitle(recipe.getName());
        stage.setScene(scene);
        stage.show();

        nameLabel.setText(recipe.getName());
        ingredientLabel.setText((recipe.getIngredients()));
        instructionLabel.setText(recipe.getInstructions());
    }
    @FXML
    public void initialize() {
        // Initialization code (if any)
    }

    @FXML
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;

        // Update the UI elements with recipe details
        if (recipe != null) {
            nameLabel.setText(recipe.getName());
            ingredientLabel.setText(recipe.getIngredients());
            instructionLabel.setText(recipe.getInstructions());
        }
    }
}