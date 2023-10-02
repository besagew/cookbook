//UI Controller. These are the functional elements of the UI. All data should go to CookbookService.
package org.cookbook.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.cookbook.model.Recipe;
import org.cookbook.service.CookbookService;

import java.io.IOException;
import java.util.HashSet;

public class EditorController {
    private CookbookService CookBookService = new CookbookService();
    @FXML
    public Button deleteButton;
    @FXML
    public Button saveButton;
    private Recipe currentRecipe;
    @FXML
    private VBox textEditor;
    @FXML
    private Button readerViewButton;
    @FXML
    private TextField BrowserSearch;
    @FXML
    public ListView<Recipe> Browser;
    @FXML
    private TextArea nameField;
    @FXML
    private TextArea ingredientField;
    @FXML
    private TextArea instructionField;
    private void DisplayBrowserSet(HashSet<Recipe> recipeSet){
        ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        recipes.addAll(recipeSet);
        Browser.setItems(recipes);
    }
    private void DisplayRecipe(Recipe recipe){
        if (recipe == null){ return;}
        nameField.setText(recipe.getName());
        ingredientField.setText((recipe.getIngredients()));
        instructionField.setText(recipe.getInstructions());
        currentRecipe = recipe;
    }
    @FXML
    private void initialize(){
        Browser.setCellFactory(param -> new ListCell<Recipe>(){
            @Override
            protected void updateItem(Recipe recipe, boolean empty){
                super.updateItem(recipe,empty);
                if (empty || recipe == null) {
                    setText(null);
                } else {
                    setText(recipe.getName());
                }
            }
        });
        Browser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Recipe selectedRecipe =  Browser.getSelectionModel().getSelectedItem();
                DisplayRecipe(selectedRecipe);
            }
        });
        readerViewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    ViewerUI displayStage = new ViewerUI(currentRecipe);
                    System.out.println(currentRecipe.getName());
                    displayStage.open();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Press");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save recipe as " + nameField.getText() + "?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    Recipe toSave = new Recipe(
                            nameField.getText(),
                            ingredientField.getText(),
                            instructionField.getText()
                    );
                    CookbookService.SaveRecipe(toSave);
                    DisplayBrowserSet(CookbookService.GetRecipesByName(BrowserSearch.getText()));
                    currentRecipe = toSave;
                    DisplayRecipe(toSave);
                }
            }
        });
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Press");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + currentRecipe.getName() + "?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    CookbookService.DeleteRecipe(currentRecipe);
                    DisplayBrowserSet(CookbookService.GetRecipesByName(BrowserSearch.getText()));
                }
            }
        });
        BrowserSearch.textProperty().addListener((obs, oldValue, newValue) ->{
            System.out.println(oldValue + " " + newValue);
            HashSet<Recipe> recipeSet = CookbookService.GetRecipesByName(newValue);
            DisplayBrowserSet(recipeSet);
        });
        DisplayBrowserSet(CookbookService.GetLoadedRecipes());

    }
}