package com.group4.controller;

import com.group4.App;
import com.group4.lib.enums.Pages;
import com.group4.service.AuthService; // Import AuthService

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
    private Hyperlink backToLoginLink;

    @FXML
    private MFXTextField firstnameField;

    @FXML
    private MFXTextField lastnameField;

    @FXML
    private MFXTextField photoField;

    @FXML
    private MFXTextField phoneNumberField;

    @FXML
    private MFXTextField addressField;

    private AuthService authService;

    public void initialize() {
        this.authService = new AuthService(App.getUserService());
    }

    /**
     * Handles the action for the Sign Up button.
     * (Currently just logs a message)
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleSignup(ActionEvent event) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

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

        String firstname = firstnameField.getText().trim();
        String lastname = lastnameField.getText().trim();
        String photo = photoField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String address = addressField.getText().trim();

        // --- Signup Logic using AuthService ---
        boolean isSignedUp = authService.signup(username, email, password, firstname, lastname, photo, phoneNumber,
                address);

        if (isSignedUp) {
            // Signup Successful
            System.out.println("Signup Successful for user: " + username);

            try {
                App.setRoot(Pages.Login);
            } catch (IOException e) {
                e.printStackTrace();
                errorMessageLabel.setText("Failed to load the login screen.");
                errorMessageLabel.setVisible(true);
            }

        } else {
            // Signup Failed
            errorMessageLabel.setText("Signup failed. Please try again.");
            errorMessageLabel.setVisible(true);
            System.out.println("Signup Failed for username: " + username);
        }
        // --- End of Signup Logic ---
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
