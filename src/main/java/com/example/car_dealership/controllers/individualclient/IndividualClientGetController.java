package com.example.car_dealership.controllers.individualclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.IndividualClient;

import static java.lang.String.valueOf;

public class IndividualClientGetController {

    @FXML
    private Button closeGetIndividualClientButton;

    @FXML
    private Label getIdLabel;

    @FXML
    private Label getEmailLabel;

    @FXML
    private Label getContactNumberLabel;

    @FXML
    private Label getBillingAddressLabel;

    @FXML
    private Label getOtherFormOfContactLabel;

    @FXML
    void closeGetIndividualClient() {
        Stage stage = (Stage) closeGetIndividualClientButton.getScene().getWindow();
        stage.close();
    }

    public void initializeIndividualClient(IndividualClient individualClient) {
        getIdLabel.setText(valueOf(individualClient.getIdClient()));
        getEmailLabel.setText(individualClient.getEmail());
        getContactNumberLabel.setText(individualClient.getContactNumber());
        getBillingAddressLabel.setText(individualClient.getBillingAddress());
        getOtherFormOfContactLabel.setText(individualClient.getOtherFormOfContact());
    }
}
