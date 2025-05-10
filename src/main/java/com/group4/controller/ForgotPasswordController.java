package com.group4.controller;

import com.group4.App;
import com.group4.lib.enums.Pages;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.io.IOException;

/**
 * Controller for the forgot password screen.
 */
public class ForgotPasswordController {

    // FXML elements injected from forgotPassword.fxml
    @FXML
    private MFXTextField usernameOrEmailField;

    @FXML
    private Label messageLabel; // Label to display instructions

    @FXML
    private Label errorMessageLabel; // Label to display errors

    @FXML
    private MFXButton resetPasswordButton;

    @FXML
    private Hyperlink backToLoginLink; // Link to go back to login

    /**
     * Handles the action for the Reset Password button.
     * (Currently just logs a message)
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleResetPassword(ActionEvent event) {
        // TODO: Implement password reset logic (e.g., send email)
        String input = usernameOrEmailField.getText().trim();
        System.out.println("Reset Password requested for: " + input);

        // For now, just show a simple message
        if (input.isEmpty()) {
            errorMessageLabel.setText("Please enter your username or email.");
            errorMessageLabel.setVisible(true);
            messageLabel.setVisible(false); // Hide info message if error occurs
        } else {
            // Simulate sending instructions (replace with actual logic)
            messageLabel.setText(
                    "If an account with that username or email exists, password reset instructions have been sent.");
            messageLabel.setVisible(true);
            errorMessageLabel.setVisible(false); // Hide error message
            System.out.println("Simulating sending reset instructions for: " + input);
        }
    }

    /**
     * Handles the action for the "Back to Login" hyperlink.
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
