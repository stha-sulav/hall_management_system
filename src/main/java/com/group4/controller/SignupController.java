package com.group4.controller;

import com.group4.App;
import com.group4.lib.enums.Pages;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.io.IOException;

/**
 * Controller for the signup screen.
 */
public class SignupController {

    // FXML elements injected from signup.fxml
    @FXML
    private MFXTextField usernameField;

    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private MFXPasswordField confirmPasswordField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private MFXButton signupButton;

    @FXML
    private Hyperlink backToLoginLink; // Link to go back to login

    /**
     * Handles the action for the Sign Up button.
     * (Currently just logs a message)
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleSignup(ActionEvent event) {
        // TODO: Implement user signup logic (validation, create UserModel, persist
        // using ORM)
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Basic logging for now
        System.out.println("Sign Up attempted with:");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password); // WARNING: Don't log passwords in production!

        // Clear previous error message
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        // Basic validation example (more comprehensive validation needed)
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessageLabel.setText("All fields are required.");
            errorMessageLabel.setVisible(true);
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorMessageLabel.setText("Passwords do not match.");
            errorMessageLabel.setVisible(true);
            return;
        }

        // TODO: Add more validation (e.g., email format, username uniqueness, password
        // complexity)
        // TODO: Hash the password before creating and persisting the UserModel

        System.out.println("Basic validation passed. Proceeding with signup logic...");

        // After successful signup logic, you would typically navigate to another page,
        // e.g., login or dashboard
        // For now, we'll just log and stay on the page or navigate back to login as a
        // placeholder
        // handleBackToLogin(null); // Example: navigate back to login after simulated
        // signup attempt
    }

    /**
     * Handles the action for the "Already have an account? Login" hyperlink.
     * Navigates back to the Login view.
     *
     * @param event The ActionEvent triggered by the hyperlink click.
     */
    @FXML
    private void handleBackToLogin(ActionEvent event) {
        try {
            // Navigate back to the Login view using the App class
            App.setRoot(Pages.Login);
        } catch (IOException e) {
            // Handle potential errors during scene switching
            e.printStackTrace();
            System.err.println("Failed to load the login screen: " + e.getMessage());
            // Optionally display an error message on the current screen
            errorMessageLabel.setText("Failed to load the login screen.");
            errorMessageLabel.setVisible(true);
        }
    }
}
