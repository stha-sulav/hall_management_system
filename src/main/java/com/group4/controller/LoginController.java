package com.group4.controller;

import com.group4.App;
import com.group4.lib.enums.Pages;
import com.group4.service.AuthService;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.event.ActionEvent;
import java.io.IOException;
import com.group4.lib.enums.UserRole;

public class LoginController {

    @FXML
    private MFXTextField usernameField;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private MFXButton loginButton;

    @FXML
    private Hyperlink signupButton;

    @FXML
    private Hyperlink forgotPasswordButton;

    private AuthService authService;

    public void initialize() {
        this.authService = new AuthService(App.getUserService());
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String usernameOrEmail = usernameField.getText().trim();
        String password = passwordField.getText();

        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        if (usernameOrEmail.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("Please enter username/email and password.");
            errorMessageLabel.setVisible(true);
            return;
        }

        boolean isAuthenticated = authService.authenticate(usernameOrEmail, password);

        if (isAuthenticated) {
            System.out.println("Login Successful for user: " + usernameOrEmail);
            UserRole role = authService.getUserRole(usernameOrEmail);
            App.setCurrentUserRole(role); // Set the user role in App

            Pages page = Pages.Dashboard; // Default to Dashboard
            if (role == UserRole.Customer) {
                page = Pages.Hall; // Navigate to Hall for customers
            }

            try {
                App.setRoot(page);
            } catch (IOException e) {
                e.printStackTrace();
                errorMessageLabel.setText("Failed to load the main application screen.");
                errorMessageLabel.setVisible(true);
            }
        } else {
            errorMessageLabel.setText("Invalid username/email or password.");
            errorMessageLabel.setVisible(true);
            System.out.println("Login Failed for username/email: " + usernameOrEmail);
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        try {
            App.setRoot(Pages.Signup);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load the signup screen.");
            errorMessageLabel.setVisible(true);
        }
    }

    @FXML
    private void handleForgotPassword(ActionEvent event) {
        try {
            App.setRoot(Pages.ForgotPassword);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load the forgot password screen.");
            errorMessageLabel.setVisible(true);
        }
    }
}
