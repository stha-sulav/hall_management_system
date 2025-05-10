package com.group4.controller;

import com.group4.App; // Import your main App class for scene switching and ORM access
import com.group4.lib.enums.Pages; // Import Pages enum for navigation
import com.group4.lib.data.SimpleFileORM; // Import SimpleFileORM
import com.group4.model.UserModel; // Import the User model

import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink; // Ensure this is imported
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

// Optional: Implement Initializable if you need to perform setup when the FXML is loaded
// import javafx.fxml.Initializable;
// import java.net.URL;
// import java.util.ResourceBundle;

// This controller handles the logic for the login.fxml view
// implements Initializable // Uncomment if using Initializable
public class LoginController {

    // FXML elements injected from login.fxml
    @FXML
    private MFXTextField usernameField;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private MFXButton loginButton;

    // Ensure these are Hyperlink types and correctly linked via fx:id in FXML
    @FXML
    private Hyperlink signupButton;

    @FXML
    private Hyperlink forgotPasswordButton;

    // Optional: Initialization method - uncomment if implementing Initializable
    // @Override
    // public void initialize(URL url, ResourceBundle rb) {
    // // Code to run after all FXML elements are injected
    // errorMessageLabel.setVisible(false); // Hide error message initially
    // }

    /**
     * Handles the login button action.
     * Retrieves username/email and password, performs validation,
     * and attempts to authenticate against the user data file.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String usernameOrEmail = usernameField.getText().trim(); // Get text and trim whitespace
        String password = passwordField.getText(); // Get password

        // Clear previous error message
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        // Basic Input Validation
        if (usernameOrEmail.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("Please enter username/email and password.");
            errorMessageLabel.setVisible(true);
            return; // Stop the login process
        }

        // --- Authentication Logic using SimpleFileORM ---
        try {
            // Get the ORM instance from the App class
            SimpleFileORM<UserModel> userORM = App.getUserORM();

            // Attempt to find a user matching either the username or email AND the password
            Optional<UserModel> authenticatedUserOptional = userORM
                    .find(user -> (usernameOrEmail.equalsIgnoreCase(user.getUsername())
                            || usernameOrEmail.equalsIgnoreCase(user.getEmail())) &&
                            password.equals(user.getPassword()) // WARNING: Comparing plain text passwords! Hash
                                                                // passwords in production!
                    ).stream().findFirst(); // Get the first matching user, if any

            if (authenticatedUserOptional.isPresent()) {
                // Authentication Successful
                UserModel authenticatedUser = authenticatedUserOptional.get();
                System.out.println("Login Successful for user: " + authenticatedUser.getUsername() + " with role: "
                        + authenticatedUser.getRole());

                // --- Navigate to the next screen (e.g., Dashboard) ---
                try {
                    App.setRoot(Pages.Dashboard); // Navigate to the Dashboard view
                } catch (IOException e) {
                    e.printStackTrace();
                    errorMessageLabel.setText("Failed to load the main application screen.");
                    errorMessageLabel.setVisible(true);
                }

            } else {
                // Authentication Failed
                errorMessageLabel.setText("Invalid username/email or password.");
                errorMessageLabel.setVisible(true);
                System.out.println("Login Failed for username/email: " + usernameOrEmail);
            }

        } catch (SimpleFileORM.ORMException e) {
            // Handle potential ORM errors during the find operation
            System.err.println("Error during user authentication lookup: " + e.getMessage());
            e.printStackTrace();
            errorMessageLabel.setText("An error occurred during authentication. Please try again.");
            errorMessageLabel.setVisible(true);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred during login: " + e.getMessage());
            e.printStackTrace();
            errorMessageLabel.setText("An unexpected error occurred. Please try again.");
            errorMessageLabel.setVisible(true);
        }
        // --- End of Authentication Logic ---
    }

    // Removed the toggleTheme method as the button is removed from the FXML
    // @FXML
    // private void toggleTheme(ActionEvent event) {
    // App.toggleTheme();
    // }

    /**
     * Handles the action for the "Sign Up" hyperlink.
     * Navigates to the Signup view.
     *
     * @param event The ActionEvent triggered by the hyperlink click.
     */
    @FXML
    private void handleSignUp(ActionEvent event) {
        // Navigate to the Signup view
        try {
            App.setRoot(Pages.Signup);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load the signup screen.");
            errorMessageLabel.setVisible(true);
        }
    }

    /**
     * Handles the action for the "Forgot Password?" hyperlink.
     * Navigates to the Forgot Password view.
     *
     * @param event The ActionEvent triggered by the hyperlink click.
     */
    @FXML
    private void handleForgotPassword(ActionEvent event) {
        // Navigate to the ForgotPassword view
        try {
            App.setRoot(Pages.ForgotPassword);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load the forgot password screen.");
            errorMessageLabel.setVisible(true);
        }
    }
}
