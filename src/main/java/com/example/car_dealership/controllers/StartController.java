package com.example.car_dealership.controllers;

import com.example.car_dealership.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    public void switchToEmployeeMenuView(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        fxmlLoader = new FXMLLoader(Main.class.getResource("views/car/car-menu-view.fxml"));
        scene = new Scene(fxmlLoader.load());

        String css = Main.class.getResource("css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}