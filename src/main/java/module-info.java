module cookbook {
    // Javafx won't work if we don't tell the UI it can use it, so you gotta do it here
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    opens org.cookbook.ui to javafx.fxml;
    opens org.cookbook.model to com.fasterxml.jackson.databind;
    exports org.cookbook.ui;
    exports org.cookbook.model;
}