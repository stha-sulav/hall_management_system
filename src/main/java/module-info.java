module com.group4 { // Ensure this module name matches the one in your error trace (com.group4)
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    // Required modules for external libraries
    requires MaterialFX; // Assuming MaterialFX is a named module
    requires org.controlsfx.controls; // Required for ControlsFX
    requires org.kordamp.ikonli.javafx; // Required for FontIcon
    requires org.kordamp.ikonli.fontawesome5; // Required for FontAwesome 5 icons

    // Export your packages that other modules might need (like if you build a JAR)
    // Also needed for reflection within the same module for some cases
    exports com.group4;
    exports com.group4.controller;
    exports com.group4.lib.enums;
    exports com.group4.lib.data; // Export the ORM package
    exports com.group4.model; // Export the model package

    // Open packages containing FXML files and controllers to javafx.fxml
    opens com.group4 to javafx.fxml; // If your main App class has FXML references
    opens com.group4.controller to javafx.fxml;

    // --- Open packages that need reflection access ---
    // Open the model package to this module itself (com.group4)
    // This allows SimpleFileORM (in com.group4.lib.data) to access UserModel fields
    // via reflection
    opens com.group4.model to com.group4, com.group4.lib.data;

}
