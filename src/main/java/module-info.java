module com.group4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires MaterialFX;

    opens com.group4.controller to javafx.fxml;

    exports com.group4;
}
