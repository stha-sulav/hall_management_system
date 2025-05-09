package com.group4.controller;

import java.io.IOException;

import com.group4.App; // Import your main App class for navigation and theme toggling
import com.group4.lib.enums.Pages;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import io.github.palexdev.materialfx.controls.MFXButton; // Import MFXButton if needed

// This controller handles the logic for the menu.fxml
// implements Initializable 
public class MenuController {

    // FXML elements (optional, only if you need to interact with them in the
    // controller)
    @FXML
    private MFXButton dashboardButton;
    @FXML
    private MFXButton studentsButton;
    @FXML
    private MFXButton roomsButton;
    @FXML
    private MFXButton settingsButton;
    @FXML
    private MFXButton logoutButton;

    // Optional: Initialization method - uncomment if implementing Initializable
    // @Override
    // public void initialize(URL url, ResourceBundle rb) {
    // // Code to run after all FXML elements are injected
    // // For example, setting initial button states, etc.
    // }

    /**
     * Handles the Dashboard button action.
     * Navigates to the Dashboard view.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleDashboard(ActionEvent event) {
        System.out.println("Dashboard button clicked");
        try {
            // Assuming you have a DashboardView.fxml
            App.setRoot(Pages.Dashboard); // Navigate using your App class
        } catch (Exception e) {
            e.printStackTrace();
            // Handle navigation error
        }
    }

    /**
     * Handles the Students button action.
     * Navigates to the Students view.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleStudents(ActionEvent event) {
        System.out.println("Students button clicked");
        try {
            // Assuming you have a StudentsView.fxml
            App.setRoot(Pages.Student); // Navigate using your App class
        } catch (Exception e) {
            e.printStackTrace();
            // Handle navigation error
        }
    }

    /**
     * Handles the Rooms button action.
     * Navigates to the Rooms view.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleRooms(ActionEvent event) {
        System.out.println("Rooms button clicked");
        try {
            // Assuming you have a RoomsView.fxml
            App.setRoot(Pages.Room); // Navigate using your App class
        } catch (Exception e) {
            e.printStackTrace();
            // Handle navigation error
        }
    }

    /**
     * Handles the Settings button action.
     * Navigates to the Settings view.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleSettings(ActionEvent event) {
        System.out.println("Settings button clicked");
        try {
            // Assuming you have a SettingsView.fxml
            App.setRoot(Pages.Setting); // Navigate using your App class
        } catch (Exception e) {
            e.printStackTrace();
            // Handle navigation error
        }
    }

    /**
     * Handles the theme toggle button action.
     * Calls the static method in the App class to switch themes.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void toggleTheme(ActionEvent event) {
        System.out.println("Toggle Theme button clicked");
        App.toggleTheme(); // Call the static method in your App class
    }

    /**
     * Handles the Logout button action.
     * Performs logout logic and navigates back to the Login view.
     * 
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Logout button clicked");
        // --- Add Logout Logic Here ---
        // Clear session, user data, etc.

        // --- Navigate back to Login View ---
        try {
            App.setRoot(Pages.Login); // Navigate back to the login view
        } catch (IOException e) {
            e.printStackTrace();
            // Handle navigation error
        }
    }
}
