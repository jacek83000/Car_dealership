package com.example.car_dealership.controllers.employee;

import com.example.car_dealership.WindowOperations;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Employee;

import java.io.IOException;

import static com.example.car_dealership.WindowOperations.closeAllWindows;
import static com.example.car_dealership.WindowOperations.switchToEmployeeMenuView;

public class EmployeeSalaryIncreaseController {
    @FXML
    private Button closeIncreaseSalaryButton;

    @FXML
    private TextField increaseSalaryTextField;

    @FXML
    void closeIncreaseSalaryEmployee() {
        Stage stage = (Stage) closeIncreaseSalaryButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void increaseSalaryEmployee() throws IOException {
        double increase = Double.parseDouble(increaseSalaryTextField.getText());
        Employee.increaseSalary(increase);
        closeAllWindows(closeIncreaseSalaryButton);
        switchToEmployeeMenuView();
    }
}
