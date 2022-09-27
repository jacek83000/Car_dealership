package com.example.car_dealership.controllers.individualclient;

import com.example.car_dealership.ArgumentError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.IndividualClient;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.car_dealership.ArgumentError.*;
import static com.example.car_dealership.CheckForValidation.*;
import static com.example.car_dealership.WindowOperations.*;

public class IndividualClientPostController implements Initializable {
    @FXML
    private Button cancelPostIndividualClientButton;

    @FXML
    private Button savePostIndividualClientButton;

    @FXML
    private TextField postEmailTextField;

    @FXML
    private TextField postContactNumberTextField;

    @FXML
    private TextField postBillingAddressTextField;

    @FXML
    private TextField postOtherFormOfContractTextField;


    @FXML
    private Label postErrorEmailLabel;

    @FXML
    private Label postErrorContactNumberLabel;

    @FXML
    private Label postErrorBillingAddressLabel;


    @FXML
    void cancelPostIndividualClient() {
        Stage stage = (Stage) cancelPostIndividualClientButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePostIndividualClient() {
        ArgumentError emailAE = emailIsCorrect(postEmailTextField.getText());
        ArgumentError contactNumberAE = contactNumberIsCorrect(postContactNumberTextField.getText());
        ArgumentError billingAddressAE = stringIsCorrect(postBillingAddressTextField.getText());

        ArgumentError[] argumentErrorResults = {emailAE, contactNumberAE, billingAddressAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                createIndividualClient();
                closeAllWindows(savePostIndividualClientButton);
                switchToIndividualClientMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {EMAIL, CONTACT_NUMBER, BILLING_ADDRESS};
            Label[] errorLabels = {postErrorEmailLabel, postErrorContactNumberLabel, postErrorBillingAddressLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }

    public void createIndividualClient() {
        new IndividualClient(postBillingAddressTextField.getText(),
                postEmailTextField.getText(),
                postContactNumberTextField.getText(),
                postOtherFormOfContractTextField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postEmailTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, EMAIL, postErrorEmailLabel, emailIsCorrect(postEmailTextField.getText()));
        });

        postContactNumberTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, CONTACT_NUMBER, postErrorContactNumberLabel, contactNumberIsCorrect(postContactNumberTextField.getText()));
        });

        postBillingAddressTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, BILLING_ADDRESS, postErrorBillingAddressLabel, stringIsCorrect(postBillingAddressTextField.getText()));
        });
    }
}
