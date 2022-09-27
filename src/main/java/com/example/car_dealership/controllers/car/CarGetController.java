package com.example.car_dealership.controllers.car;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Car;

import static java.lang.String.valueOf;

public class CarGetController {

    @FXML
    private Button closeGetCarButton;

    @FXML
    private Label getVinLabel;

    @FXML
    private Label getNameLabel;

    @FXML
    private Label getMileageLabel;

    @FXML
    private Label getRegistrationNumberLabel;

    @FXML
    private Label getBodyTypeLabel;

    @FXML
    private Label getColorLabel;

    @FXML
    private ListView<String> getAccessoriesListView;

    @FXML
    void closeGetCar() {
        Stage stage = (Stage) closeGetCarButton.getScene().getWindow();
        stage.close();
    }

    public void initializeCar(Car car) {
        getVinLabel.setText(car.getVin());
        getNameLabel.setText(car.getName());
        getMileageLabel.setText(valueOf(car.getMileage()));
        getRegistrationNumberLabel.setText(car.getRegistrationNumber());
        getBodyTypeLabel.setText(car.getBodyType());
        getColorLabel.setText(car.getColor());
        getAccessoriesListView.setItems(FXCollections.observableList(car.getAccessories()));
    }
}
