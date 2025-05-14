package com.group4.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

import com.group4.App;
import com.group4.lib.enums.Pages;

public class DrawerController {

    @FXML
    private ToggleButton dashboardButton;

    @FXML
    private ToggleButton studentsButton;

    @FXML
    private ToggleButton roomsButton;

    @FXML
    private ToggleButton settingsButton;

    @FXML
    private ToggleButton logoutButton;

    private ToggleGroup toggleGroup;

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        dashboardButton.setToggleGroup(toggleGroup);
        studentsButton.setToggleGroup(toggleGroup);
        roomsButton.setToggleGroup(toggleGroup);
        settingsButton.setToggleGroup(toggleGroup);
        logoutButton.setToggleGroup(toggleGroup);
    }

    @FXML
    void handleDashboard(ActionEvent event) throws IOException {
        App.setRoot(Pages.Dashboard);
        dashboardButton.setSelected(true);
    }

    @FXML
    void handleStudents(ActionEvent event) throws IOException {
        App.setRoot(Pages.Hall); // Assuming Hall page is for students
        studentsButton.setSelected(true);
    }

    @FXML
    void handleRooms(ActionEvent event) throws IOException {
        App.setRoot(Pages.Hall); // Assuming Hall page is for rooms
        roomsButton.setSelected(true);
    }

    @FXML
    void handleSettings(ActionEvent event) throws IOException {
        App.setRoot(Pages.Hall); // Assuming Hall page is for settings
        settingsButton.setSelected(true);
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        App.setRoot(Pages.Login);
        logoutButton.setSelected(true);
    }
}
