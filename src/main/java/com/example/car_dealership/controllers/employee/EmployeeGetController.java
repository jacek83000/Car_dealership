package com.example.car_dealership.controllers.employee;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Employee;

import java.util.Calendar;

import static com.example.car_dealership.CheckForValidation.*;
import static com.example.car_dealership.DateOperations.*;
import static java.lang.String.valueOf;

public class EmployeeGetController {

    @FXML
    private Button closeGetEmployeeButton;

    @FXML
    private Label getFirstNameLabel;

    @FXML
    private Label getLastNameLabel;

    @FXML
    private Label getDateOfBirthLabel;

    @FXML
    private Label getAgeLabel;

    @FXML
    private Label getPositionLabel;

    @FXML
    private Label getDateOfEmploymentLabel;

    @FXML
    private Label getRatePerHourLabel;

    @FXML
    private Label getMonthlySalaryLabel;

    @FXML
    void closeGetEmployee() {
        Stage stage = (Stage) closeGetEmployeeButton.getScene().getWindow();
        stage.close();
    }

    public void initializeEmployee(Employee employee) {
        getFirstNameLabel.setText(employee.getFirstName());
        getLastNameLabel.setText(employee.getLastName());
        getDateOfBirthLabel.setText(convertCalendarToString(employee.getDateOfBirth()));
        getAgeLabel.setText(valueOf(employee.getAge()));
        getPositionLabel.setText(employee.getPosition());
        getDateOfEmploymentLabel.setText(convertCalendarToString(employee.getDateOfEmployment()));
        getRatePerHourLabel.setText(setDecimalFormat(employee.getRatePerHour()) + " zł");
        getMonthlySalaryLabel.setText((setDecimalFormat(employee.calculateMonthlySalary(Calendar.getInstance()))) + " zł");
    }
}
