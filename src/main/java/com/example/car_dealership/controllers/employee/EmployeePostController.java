package com.example.car_dealership.controllers.employee;

import com.example.car_dealership.ArgumentError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Employee;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.car_dealership.ArgumentError.*;
import static com.example.car_dealership.CheckForValidation.*;
import static com.example.car_dealership.DateOperations.convertDatePickerToCalendar;
import static com.example.car_dealership.WindowOperations.closeAllWindows;
import static com.example.car_dealership.WindowOperations.switchToEmployeeMenuView;

public class EmployeePostController implements Initializable {

    @FXML
    private Button cancelPostEmployeeButton;

    @FXML
    private Button savePostEmployeeButton;

    @FXML
    private TextField postFirstNameTextField;

    @FXML
    private TextField postLastNameTextField;

    @FXML
    private DatePicker postDateOfBirthDatePicker;

    @FXML
    private TextField postPositionTextField;

    @FXML
    private DatePicker postDateOfEmploymentDatePicker;

    @FXML
    private TextField postRatePerHourTextField;

    @FXML
    private Label postErrorFirstNameLabel;

    @FXML
    private Label postErrorLastNameLabel;

    @FXML
    private Label postErrorDateOfBirthLabel;

    @FXML
    private Label postErrorPositionLabel;

    @FXML
    private Label postErrorDateOfEmploymentLabel;

    @FXML
    private Label postErrorRatePerHourLabel;


    @FXML
    void cancelPostEmployee() {
        Stage stage = (Stage) cancelPostEmployeeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePostEmployee() {
        ArgumentError firstNameAE = wordIsCorrect(postFirstNameTextField.getText());
        ArgumentError lastNameAE = wordIsCorrect(postLastNameTextField.getText());
        ArgumentError dateOfBirthAE = dateIsCorrect(postDateOfBirthDatePicker);
        ArgumentError positionAE = wordsAreCorrect(postPositionTextField.getText());
        ArgumentError dateOfEmploymentAE = dateIsCorrect(postDateOfEmploymentDatePicker);
        ArgumentError ratePerHourAE = doubleIsCorrect(postRatePerHourTextField.getText());

        ArgumentError[] argumentErrorResults = {firstNameAE, lastNameAE, dateOfBirthAE, positionAE, dateOfEmploymentAE, ratePerHourAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                createEmployee();
                closeAllWindows(savePostEmployeeButton);
                switchToEmployeeMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, POSITION, DATE_OF_EMPLOYMENT, RATE_PER_HOUR};
            Label[] errorLabels = {postErrorFirstNameLabel, postErrorLastNameLabel, postErrorDateOfBirthLabel, postErrorPositionLabel, postErrorDateOfEmploymentLabel, postErrorRatePerHourLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }


    public void createEmployee() {
        new Employee(postPositionTextField.getText(),
                convertDatePickerToCalendar(postDateOfEmploymentDatePicker),
                Double.parseDouble(postRatePerHourTextField.getText().replaceAll(",", ".")),
                postFirstNameTextField.getText(),
                postLastNameTextField.getText(),
                convertDatePickerToCalendar(postDateOfBirthDatePicker));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        postFirstNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, FIRST_NAME, postErrorFirstNameLabel, wordIsCorrect(postFirstNameTextField.getText()));
        });

        postLastNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, LAST_NAME, postErrorLastNameLabel, wordIsCorrect(postLastNameTextField.getText()));
        });

        postDateOfBirthDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null)
                postErrorDateOfBirthLabel.setText(DATE_OF_BIRTH.getNullPointerExceptionMessage());
            else
                postErrorDateOfEmploymentLabel.setText(null);
        });

        postPositionTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, POSITION, postErrorPositionLabel, wordIsCorrect(postPositionTextField.getText()));
        });

        postDateOfEmploymentDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null)
                postErrorDateOfEmploymentLabel.setText(DATE_OF_EMPLOYMENT.getNullPointerExceptionMessage());
            else
                postErrorDateOfEmploymentLabel.setText(null);
        });

        postRatePerHourTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, RATE_PER_HOUR, postErrorRatePerHourLabel, doubleIsCorrect(postRatePerHourTextField.getText()));
        });
    }
}
