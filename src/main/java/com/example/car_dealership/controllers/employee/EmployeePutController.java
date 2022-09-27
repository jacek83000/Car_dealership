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
import static com.example.car_dealership.DateOperations.convertCalendarToDatePicker;
import static com.example.car_dealership.DateOperations.convertDatePickerToCalendar;
import static com.example.car_dealership.WindowOperations.closeAllWindows;
import static com.example.car_dealership.WindowOperations.switchToEmployeeMenuView;
import static java.lang.String.valueOf;

public class EmployeePutController implements Initializable {
    @FXML
    private Button cancelPutEmployeeButton;

    @FXML
    private Button savePutEmployeeButton;

    @FXML
    private TextField putFirstNameTextField;

    @FXML
    private TextField putLastNameTextField;

    @FXML
    private DatePicker putDateOfBirthDatePicker;

    @FXML
    private TextField putPositionTextField;

    @FXML
    private DatePicker putDateOfEmploymentDatePicker;

    @FXML
    private TextField putRatePerHourTextField;

    @FXML
    private Label putErrorFirstNameLabel;

    @FXML
    private Label putErrorLastNameLabel;

    @FXML
    private Label putErrorDateOfBirthLabel;

    @FXML
    private Label putErrorPositionLabel;

    @FXML
    private Label putErrorDateOfEmploymentLabel;

    @FXML
    private Label putErrorRatePerHourLabel;


    @FXML
    void cancelPutEmployee() {
        Stage stage = (Stage) cancelPutEmployeeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePutEmployee() {
        ArgumentError firstNameAE = wordIsCorrect(putFirstNameTextField.getText());
        ArgumentError lastNameAE = wordIsCorrect(putLastNameTextField.getText());
        ArgumentError dateOfBirthAE = dateIsCorrect(putDateOfBirthDatePicker);
        ArgumentError positionAE = wordsAreCorrect(putPositionTextField.getText());
        ArgumentError dateOfEmploymentAE = dateIsCorrect(putDateOfEmploymentDatePicker);
        ArgumentError ratePerHourAE = doubleIsCorrect(putRatePerHourTextField.getText());

        ArgumentError[] argumentErrorResults = {firstNameAE, lastNameAE, dateOfBirthAE, positionAE, dateOfEmploymentAE, ratePerHourAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                replaceEmployee();
                closeAllWindows(savePutEmployeeButton);
                switchToEmployeeMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, POSITION, DATE_OF_EMPLOYMENT, RATE_PER_HOUR};
            Label[] errorLabels = {putErrorFirstNameLabel, putErrorLastNameLabel, putErrorDateOfBirthLabel, putErrorPositionLabel, putErrorDateOfEmploymentLabel, putErrorRatePerHourLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }

    public void replaceEmployee() {
        EmployeeMenuController.deleteSelectedObject();
        new Employee(putPositionTextField.getText(),
                convertDatePickerToCalendar(putDateOfEmploymentDatePicker),
                Double.parseDouble(putRatePerHourTextField.getText().replaceAll(",", ".")),
                putFirstNameTextField.getText(),
                putLastNameTextField.getText(),
                convertDatePickerToCalendar(putDateOfBirthDatePicker));
    }

    public void setAlreadyExistingValuesOfEmployee(Employee employee) {
        putFirstNameTextField.setText(employee.getFirstName());
        putLastNameTextField.setText(employee.getLastName());
        putDateOfBirthDatePicker.setValue(convertCalendarToDatePicker(employee.getDateOfBirth()));
        putPositionTextField.setText(employee.getPosition());
        putDateOfEmploymentDatePicker.setValue(convertCalendarToDatePicker(employee.getDateOfEmployment()));
        putRatePerHourTextField.setText(valueOf(employee.getRatePerHour()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        putFirstNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, FIRST_NAME, putErrorFirstNameLabel, wordIsCorrect(putFirstNameTextField.getText()));
        });

        putLastNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, LAST_NAME, putErrorLastNameLabel, wordIsCorrect(putLastNameTextField.getText()));
        });

        putDateOfBirthDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null)
                putErrorDateOfBirthLabel.setText(DATE_OF_BIRTH.getNullPointerExceptionMessage());
            else
                putErrorDateOfEmploymentLabel.setText(null);
        });

        putPositionTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, POSITION, putErrorPositionLabel, wordIsCorrect(putPositionTextField.getText()));
        });

        putDateOfEmploymentDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null)
                putErrorDateOfEmploymentLabel.setText(DATE_OF_EMPLOYMENT.getNullPointerExceptionMessage());
            else
                putErrorDateOfEmploymentLabel.setText(null);
        });

        putRatePerHourTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, RATE_PER_HOUR, putErrorRatePerHourLabel, doubleIsCorrect(putRatePerHourTextField.getText()));
        });
    }
}
