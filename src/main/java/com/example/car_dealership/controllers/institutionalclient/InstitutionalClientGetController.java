package com.example.car_dealership.controllers.institutionalclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.InstitutionalClient;

import static java.lang.String.valueOf;

public class InstitutionalClientGetController {
    @FXML
    private Button closeGetInstitutionalClientButton;

    @FXML
    private Label getIdLabel;

    @FXML
    private Label getEmailLabel;

    @FXML
    private Label getContactNumberLabel;

    @FXML
    private Label getInstitutionNameLabel;

    @FXML
    private Label getInstitutionAddressLabel;

    @FXML
    private Label getOtherFormOfContactLabel;

    @FXML
    void closeGetInstitutionalClient() {
        Stage stage = (Stage) closeGetInstitutionalClientButton.getScene().getWindow();
        stage.close();
    }

    public void initializeInstitutionalClient(InstitutionalClient institutionalClient) {
        getIdLabel.setText(valueOf(institutionalClient.getIdClient()));
        getEmailLabel.setText(institutionalClient.getEmail());
        getContactNumberLabel.setText(institutionalClient.getContactNumber());
        getInstitutionNameLabel.setText(institutionalClient.getInstitutionName());
        getInstitutionAddressLabel.setText(institutionalClient.getInstitutionAddress());
        getOtherFormOfContactLabel.setText(institutionalClient.getOtherFormOfContact());
    }
}
