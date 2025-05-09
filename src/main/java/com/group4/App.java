package com.group4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;

import com.group4.lib.enums.Pages;
import com.group4.lib.enums.UserRole;
import com.group4.lib.data.SimpleFileORM;
import com.group4.model.User;

public class App extends Application {

    private static Scene mainScene;
    private static boolean isDarkMode = false;

    private SimpleFileORM<User> userORM;
    private static final String USER_DATA_FILE = "user.txt";

    // Keep a static reference to the App instance for static methods to access
    // non-static members
    private static App instance;

    @Override
    public void init() throws Exception {
        super.init();
        instance = this; // Set the static instance reference when the application initializes
    }

    @Override
    public void start(Stage stage) throws IOException {
        // MaterialFX Theming Setup (Keep this)
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        try {
            // Determine the path for the user data file
            // Using a path relative to the application's working directory for simplicity.
            // This will create a 'data' folder next to your application's JAR or execution
            // path.
            // In a real application, consider using a more robust path like user home
            // directory
            // or a platform-specific application data directory.
            String userDataPath = Paths.get("data", USER_DATA_FILE).toString(); // Creates path like ./data/user.txt
            userORM = new SimpleFileORM<>(User.class, userDataPath);

            // Check if the default admin user already exists by username
            Optional<User> adminUserOptional = userORM.find(user -> "admin".equals(user.getUsername()))
                    .stream()
                    .findFirst();

            if (!adminUserOptional.isPresent()) {
                // Create the default admin user if they don't exist
                User adminUser = new User("admin", "admin@example.com", "admin", UserRole.Admin);
                userORM.create(adminUser);
                System.out.println("Default admin user created successfully.");
            }

        } catch (SimpleFileORM.ORMException e) {
            // Catch ORM-specific exceptions during initialization or creation
            System.err.println("Error during ORM initialization or default user creation: " + e.getMessage());
            e.printStackTrace();
            // Depending on the severity, you might want to show an alert to the user
            // and potentially exit the application if data storage is critical.
            // For now, we'll just log the error.
        } catch (Exception e) {
            // Catch any other unexpected exceptions during this process
            System.err.println("An unexpected error occurred during startup user creation: " + e.getMessage());
            e.printStackTrace();
        }
        // --- End of ORM Initialization ---

        // Load the initial view (Login) using the updated loadView method
        Parent initialRoot = loadView(Pages.Login);

        mainScene = new Scene(initialRoot);

        // Load the global CSS file onto the scene once
        URL globalCssUrl = getClass().getResource("/com/group4/styles/global.css");
        if (globalCssUrl != null) {
            mainScene.getStylesheets().add(globalCssUrl.toExternalForm());
            System.out.println("Successfully loaded global CSS from: " + globalCssUrl);
        } else {
            System.err.println("Error loading global CSS file: /com/group4/styles/global.css not found on classpath.");
        }

        // Apply initial theme class (e.g., "light") to the root of the scene
        mainScene.getRoot().getStyleClass().add("light");

        stage.setScene(mainScene);
        stage.setTitle("Hall Management System"); // Generic title, can be updated per view
        stage.setMinWidth(450); // Set minimum width for responsiveness
        stage.setMinHeight(550); // Set minimum height for responsiveness
        stage.show();
    }

    /**
     * Loads an FXML view and applies its corresponding CSS file.
     * 
     * @param page The Pages enum value for the view to load.
     * @return The loaded Parent node for the view.
     * @throws IOException If the FXML or CSS file cannot be found or loaded.
     */
    public static Parent loadView(Pages page) throws IOException {
        String fxmlFileName = page.getPage(); // Get the FXML file base name from the enum
        String fxmlResourcePath = "view/" + fxmlFileName + ".fxml"; // Path to FXML resource

        System.out.println("DEBUG: Attempting to load FXML from resource path: " + fxmlResourcePath);
        URL fxmlLocation = App.class.getResource(fxmlResourcePath);

        if (fxmlLocation == null) {
            throw new IOException(
                    "FXML file not found: " + fxmlResourcePath
                            + ". Make sure it's in src/main/resources/com/group4/view/");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Parent root = fxmlLoader.load();

        // --- Load the view-specific CSS file ---
        // Assuming CSS filenames match FXML filenames (lowercase)
        String cssFileName = fxmlFileName.toLowerCase();
        String cssResourcePath = "/com/group4/styles/" + cssFileName + ".css"; // Path to CSS resource

        URL cssUrl = App.class.getResource(cssResourcePath);

        if (cssUrl != null) {
            root.getStylesheets().add(cssUrl.toExternalForm());
            System.out.println("Successfully loaded view CSS from: " + cssUrl);
        } else {
            System.err.println("Warning: View CSS file not found: " + cssResourcePath + " on classpath.");
            // It might be acceptable for a view to not have a dedicated CSS file
        }
        // --- End of view-specific CSS loading ---

        // Re-apply theme class to the new root after loading
        // This ensures the theme variables in global.css and view-specific css work
        // correctly
        // Get the current theme state from the static variable
        String themeClass = isDarkMode ? "dark" : "light";
        root.getStyleClass().removeAll("light", "dark"); // Remove existing theme classes
        root.getStyleClass().add(themeClass); // Add the current theme class

        return root;
    }

    /**
     * Switches the root of the main scene to a new view.
     * 
     * @param page The Pages enum value for the view to switch to.
     * @throws IOException If the view cannot be loaded.
     */
    public static void setRoot(Pages page) throws IOException {
        Parent newRoot = loadView(page);
        mainScene.setRoot(newRoot);
        // The theme class is already applied in loadView
    }

    // Static method to toggle the theme - call this from your controller
    public static void toggleTheme() {
        isDarkMode = !isDarkMode; // Toggle the state

        // Get the current root and update its style class
        Parent currentRoot = mainScene.getRoot();
        currentRoot.getStyleClass().removeAll("light", "dark"); // Remove existing theme classes
        currentRoot.getStyleClass().add(isDarkMode ? "dark" : "light"); // Add the new theme class

        System.out.println("Switched to " + (isDarkMode ? "Dark" : "Light") + " Theme");

        // You might want to save the theme preference here (e.g., to a config file)
    }

    // Getter for the main scene, useful for controllers
    public static Scene getMainScene() {
        return mainScene;
    }

    // Getter for the current theme state, useful for controllers or other parts
    public static boolean isDarkMode() {
        return isDarkMode;
    }

    // Provide a static getter for the userORM instance
    // This allows controllers/services to access the ORM
    // Consider creating a dedicated Service class for ORM interactions in a real
    // app
    public static SimpleFileORM<User> getUserORM() {
        // Check if the App instance has been initialized and the ORM is set
        if (instance != null && instance.userORM != null) {
            return instance.userORM;
        } else {
            // This should not happen if accessed after App.start() completes
            throw new IllegalStateException("User ORM has not been initialized. Access ORM after App.start().");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
