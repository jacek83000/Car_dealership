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
import static com.example.car_dealership.WindowOperations.*;

public class InstitutionalClientPostController implements Initializable {

    @FXML
    private Button cancelPostInstitutionalClientButton;

    @FXML
    private Button savePostInstitutionalClientButton;

    @FXML
    private TextField postEmailTextField;

    @FXML
    private TextField postContactNumberTextField;

    @FXML
    private TextField postInstitutionNameTextField;

    @FXML
    private TextField postInstitutionAddressTextField;

    @FXML
    private TextField postOtherFormOfContractTextField;

    @FXML
    private Label postErrorEmailLabel;

    @FXML
    private Label postErrorContactNumberLabel;

    @FXML
    private Label postErrorInstitutionNameLabel;

    @FXML
    private Label postErrorInstitutionAddressLabel;

    @FXML
    private Label postErrorOtherFormOfContactLabel;

    @FXML
    void cancelPostInstitutionalClient() {
        Stage stage = (Stage) cancelPostInstitutionalClientButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePostInstitutionalClient() {
        ArgumentError emailAE = emailIsCorrect(postEmailTextField.getText());
        ArgumentError contactNumberAE = contactNumberIsCorrect(postContactNumberTextField.getText());
        ArgumentError institutionNameAE = stringIsCorrect(postInstitutionNameTextField.getText());
        ArgumentError institutionAddressAE = stringIsCorrect(postInstitutionAddressTextField.getText());

        ArgumentError[] argumentErrorResults = {emailAE, contactNumberAE, institutionNameAE, institutionAddressAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                createInstitutionalClient();
                closeAllWindows(savePostInstitutionalClientButton);
                switchToInstitutionalClientMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {EMAIL, CONTACT_NUMBER, INSTITUTION_NAME, INSTITUTION_ADDRESS};
            Label[] errorLabels = {postErrorEmailLabel, postErrorContactNumberLabel, postErrorInstitutionNameLabel, postErrorInstitutionAddressLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }

    public void createInstitutionalClient() {
        new InstitutionalClient(postInstitutionNameTextField.getText(),
                postInstitutionAddressTextField.getText(),
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

        postInstitutionNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, INSTITUTION_NAME, postErrorInstitutionNameLabel, stringIsCorrect(postInstitutionNameTextField.getText()));
        });

        postInstitutionAddressTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, INSTITUTION_ADDRESS, postErrorInstitutionAddressLabel, stringIsCorrect(postInstitutionAddressTextField.getText()));
        });
    }
}
