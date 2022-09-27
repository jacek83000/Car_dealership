package com.example.car_dealership.controllers.institutionalclient;

import com.example.car_dealership.ArgumentError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InstitutionalClient;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.car_dealership.ArgumentError.*;
import static com.example.car_dealership.CheckForValidation.*;
import static com.example.car_dealership.CheckForValidation.stringIsCorrect;
import static com.example.car_dealership.WindowOperations.*;

public class InstitutionalClientPutController implements Initializable {

    @FXML
    private Button cancelPutInstitutionalClientButton;

    @FXML
    private Button savePutInstitutionalClientButton;

    @FXML
    private TextField putEmailTextField;

    @FXML
    private TextField putContactNumberTextField;

    @FXML
    private TextField putInstitutionNameTextField;

    @FXML
    private TextField putInstitutionAddressTextField;

    @FXML
    private TextField putOtherFormOfContractTextField;

    @FXML
    private Label putErrorEmailLabel;

    @FXML
    private Label putErrorContactNumberLabel;

    @FXML
    private Label putErrorInstitutionNameLabel;

    @FXML
    private Label putErrorInstitutionAddressLabel;

    @FXML
    private Label putErrorOtherFormOfContactLabel;


    @FXML
    void cancelPutInstitutionalClient() {
        Stage stage = (Stage) cancelPutInstitutionalClientButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePutInstitutionalClient() {
        ArgumentError emailAE = emailIsCorrect(putEmailTextField.getText());
        ArgumentError contactNumberAE = contactNumberIsCorrect(putContactNumberTextField.getText());
        ArgumentError institutionNameAE = stringIsCorrect(putInstitutionNameTextField.getText());
        ArgumentError institutionAddressAE = stringIsCorrect(putInstitutionAddressTextField.getText());

        ArgumentError[] argumentErrorResults = {emailAE, contactNumberAE, institutionNameAE, institutionAddressAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                replaceInstitutionalClient();
                closeAllWindows(savePutInstitutionalClientButton);
                switchToInstitutionalClientMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {EMAIL, CONTACT_NUMBER, INSTITUTION_NAME, INSTITUTION_ADDRESS};
            Label[] errorLabels = {putErrorEmailLabel, putErrorContactNumberLabel, putErrorInstitutionNameLabel, putErrorInstitutionAddressLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }

    public void replaceInstitutionalClient() {
        InstitutionalClientMenuController.deleteSelectedObject();
        new InstitutionalClient(putInstitutionNameTextField.getText(),
                putInstitutionAddressTextField.getText(),
                putEmailTextField.getText(),
                putContactNumberTextField.getText(),
                putOtherFormOfContractTextField.getText());
    }

    public void setAlreadyExistingValuesOfInstitutionalClient(InstitutionalClient institutionalClient) {
        putEmailTextField.setText(institutionalClient.getEmail());
        putContactNumberTextField.setText(institutionalClient.getContactNumber());
        putInstitutionNameTextField.setText(institutionalClient.getInstitutionName());
        putInstitutionAddressTextField.setText(institutionalClient.getInstitutionAddress());
        putOtherFormOfContractTextField.setText(institutionalClient.getOtherFormOfContact());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        putEmailTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, EMAIL, putErrorEmailLabel, emailIsCorrect(putEmailTextField.getText()));
        });

        putContactNumberTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, CONTACT_NUMBER, putErrorContactNumberLabel, contactNumberIsCorrect(putContactNumberTextField.getText()));
        });

        putInstitutionNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, INSTITUTION_NAME, putErrorInstitutionNameLabel, stringIsCorrect(putInstitutionNameTextField.getText()));
        });

        putInstitutionAddressTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, INSTITUTION_ADDRESS, putErrorInstitutionAddressLabel, stringIsCorrect(putInstitutionAddressTextField.getText()));
        });
    }
}
