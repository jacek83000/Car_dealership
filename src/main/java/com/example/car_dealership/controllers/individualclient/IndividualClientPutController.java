package com.example.car_dealership.controllers.individualclient;

import com.example.car_dealership.ArgumentError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.IndividualClient;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.car_dealership.ArgumentError.*;
import static com.example.car_dealership.CheckForValidation.*;
import static com.example.car_dealership.WindowOperations.*;

public class IndividualClientPutController implements Initializable {
    @FXML
    private AnchorPane postPane;

    @FXML
    private Label putIndividualClientLabel;

    @FXML
    private Button cancelPutIndividualClientButton;

    @FXML
    private Button savePutIndividualClientButton;

    @FXML
    private TextField putEmailTextField;

    @FXML
    private TextField putContactNumberTextField;

    @FXML
    private TextField putBillingAddressTextField;

    @FXML
    private TextField putOtherFormOfContractTextField;

    @FXML
    private Label putErrorEmailLabel;

    @FXML
    private Label putErrorContactNumberLabel;

    @FXML
    private Label putErrorBillingAddressLabel;


    @FXML
    void cancelPutIndividualClient() {
        Stage stage = (Stage) cancelPutIndividualClientButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePutIndividualClient() {
        ArgumentError emailAE = emailIsCorrect(putEmailTextField.getText());
        ArgumentError contactNumberAE = contactNumberIsCorrect(putContactNumberTextField.getText());
        ArgumentError billingAddressAE = stringIsCorrect(putBillingAddressTextField.getText());

        ArgumentError[] argumentErrorResults = {emailAE, contactNumberAE, billingAddressAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                replaceIndividualClient();
                closeAllWindows(savePutIndividualClientButton);
                switchToIndividualClientMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {EMAIL, CONTACT_NUMBER, BILLING_ADDRESS};
            Label[] errorLabels = {putErrorEmailLabel, putErrorContactNumberLabel, putErrorBillingAddressLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }

    public void replaceIndividualClient() {
        IndividualClientMenuController.deleteSelectedObject();
        new IndividualClient(putBillingAddressTextField.getText(),
                putEmailTextField.getText(),
                putContactNumberTextField.getText(),
                putOtherFormOfContractTextField.getText());
    }

    public void setAlreadyExistingValuesOfIndividualClient(IndividualClient individualClient) {
        putEmailTextField.setText(individualClient.getEmail());
        putContactNumberTextField.setText(individualClient.getContactNumber());
        putBillingAddressTextField.setText(individualClient.getBillingAddress());
        putOtherFormOfContractTextField.setText(individualClient.getOtherFormOfContact());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        putEmailTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, EMAIL, putErrorEmailLabel, emailIsCorrect(putEmailTextField.getText()));
        });

        putContactNumberTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, CONTACT_NUMBER, putErrorContactNumberLabel, contactNumberIsCorrect(putContactNumberTextField.getText()));
        });

        putBillingAddressTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, BILLING_ADDRESS, putErrorBillingAddressLabel, stringIsCorrect(putBillingAddressTextField.getText()));
        });
    }
}
