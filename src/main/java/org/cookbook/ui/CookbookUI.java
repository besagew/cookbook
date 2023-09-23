package org.cookbook.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cookbook.service.CookbookService;

import java.io.IOException;

// The class will handle the UI and UI based interactions. anything involving data should be in CookbookService
// It and the FXML file are the "View" element of the Model-View-Controller pattern.
public class CookbookUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/RecipeEditor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 440);
        stage.setTitle("Calvin's Cookbook");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}