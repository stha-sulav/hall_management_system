package com.group4.controller;

import com.group4.App; // Import your main App class for scene switching and theme toggling
import com.group4.lib.enums.Pages;

import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXButton; // Import MFXButton if used in controller
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;

// Optional: Implement Initializable if you need to perform setup when the FXML is loaded
// import javafx.fxml.Initializable;
// import java.net.URL;
// import java.util.ResourceBundle;

// This controller handles the logic for the LoginView.fxml
// implements Initializable // Uncomment if using Initializable
public class LoginController {

    // FXML elements injected from LoginView.fxml
    @FXML
    private MFXTextField usernameField;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private MFXButton loginButton; // Assuming you might need to disable/enable this button

    // Optional: Initialization method - uncomment if implementing Initializable
    // @Override
    // public void initialize(URL url, ResourceBundle rb) {
    // // Code to run after all FXML elements are injected
    // // For example, setting focus, adding listeners, etc.
    // errorMessageLabel.setVisible(false); // Hide error message initially
    // }

    /**
     * Handles the login button action.
     * Retrieves username and password, performs basic validation,
     * and calls a placeholder authentication method.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Clear previous error message
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        // Basic Input Validation
        if (username.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("Please enter both username and password.");
            errorMessageLabel.setVisible(true);
            return; // Stop the login process
        }

        // --- Placeholder Authentication Logic ---
        // Replace this with your actual authentication service call
        // This is where you would interact with your model or service layer
        boolean loginSuccessful = checkCredentials(username, password);

        if (loginSuccessful) {
            System.out.println("Login Successful for user: " + username);
            // --- Navigate to the next screen ---
            try {
                // Assuming you have a 'main' FXML file (e.g., MainView.fxml)
                // and a corresponding loadFXML method in your App class
                App.setRoot(Pages.Dashboard); // Call static method in App to change scene content
            } catch (IOException e) {
                e.printStackTrace();
                errorMessageLabel.setText("Failed to load the main application screen.");
                errorMessageLabel.setVisible(true);
            }

        } else {
            errorMessageLabel.setText("Invalid username or password.");
            errorMessageLabel.setVisible(true);
            System.out.println("Login Failed for user: " + username);
        }
    }

    /**
     * Placeholder method for checking user credentials.
     * In a real application, this would interact with a backend service or
     * database.
     * 
     * @param username The entered username.
     * @param password The entered password.
     * @return true if credentials are valid, false otherwise.
     */
    private boolean checkCredentials(String username, String password) {
        // !!! WARNING: Hardcoding credentials is NOT secure for production !!!
        // This is purely a placeholder.
        // Replace with secure authentication logic.
        return "admin".equals(username) && "password".equals(password);
    }

    /**
     * Handles the theme toggle button action.
     * Calls the static method in the App class to switch themes.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void toggleTheme(ActionEvent event) {
        App.toggleTheme(); // Call the static method in your App class
    }

    // Optional: Add handlers for "Forgot Password" or "Sign Up" labels if they are
    // interactive
    // @FXML
    // private void handleForgotPassword() {
    // System.out.println("Forgot Password clicked");
    // // Add logic to navigate to forgot password screen or show a dialog
    // }

    // @FXML
    // private void handleSignUp() {
    // System.out.println("Sign Up clicked");
    // // Add logic to navigate to sign up screen
    // }
}
