module com.group4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires MaterialFX; // Ensure MaterialFX module is required
    requires org.controlsfx.controls; // Ensure ControlsFX module is required
    requires org.kordamp.ikonli.javafx; // Ensure Ikonli module is required
    requires org.kordamp.ikonli.fontawesome5; // Ensure Ikonli FontAwesome module is required

    // Export your main application package(s)
    exports com.group4;
    exports com.group4.controller; // Export the controller package

    // --- Add this line to export the enums package ---
    exports com.group4.lib.enums;

    // --- Add this line to export the models package ---
    exports com.group4.model;

    // Open packages to javafx.fxml for FXML access
    opens com.group4 to javafx.fxml;
    opens com.group4.controller to javafx.fxml;
}
