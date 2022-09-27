package com.example.car_dealership.controllers.car;

import com.example.car_dealership.ArgumentError;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Car;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.car_dealership.ArgumentError.*;
import static com.example.car_dealership.CheckForValidation.*;
import static com.example.car_dealership.CheckForValidation.wordsAreCorrect;
import static com.example.car_dealership.WindowOperations.closeAllWindows;
import static com.example.car_dealership.WindowOperations.switchToCarsMenuView;
import static java.lang.String.valueOf;

public class CarPutController implements Initializable {
    @FXML
    private Button cancelPutCarButton;

    @FXML
    private Button savePutCarButton;

    @FXML
    private TextField putVinTextField;

    @FXML
    private TextField putNameTextField;

    @FXML
    private TextField putMileageTextField;

    @FXML
    private TextField putRegistrationNumberTextField;

    @FXML
    private TextField putBodyTypeTextField;

    @FXML
    private TextField putColorTextField;

    @FXML
    private TextField putAccessoriesTextField;

    @FXML
    private ListView<String> putAccessoriesListView;


    @FXML
    private Label putErrorVinLabel;

    @FXML
    private Label putErrorNameLabel;

    @FXML
    private Label putErrorMileageLabel;

    @FXML
    private Label putErrorRegistrationNumberLabel;

    @FXML
    private Label putErrorBodyTypeLabel;

    @FXML
    private Label putErrorColorLabel;

    @FXML
    private Label putErrorAccessoriesLabel;

    private ArrayList<String> _accessories = new ArrayList<>();


    @FXML
    void cancelPutCar() {
        Stage stage = (Stage) cancelPutCarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePutCar() {
        ArgumentError vinAE = wordWithNumbersIsCorrect(putVinTextField.getText());
        ArgumentError nameAE = stringIsCorrect(putNameTextField.getText());
        ArgumentError mileageAE = integerIsCorrect(putMileageTextField.getText());
        ArgumentError registrationNumberAE = stringIsCorrect(putRegistrationNumberTextField.getText());
        ArgumentError bodyTypeAE = wordsAreCorrect(putBodyTypeTextField.getText());
        ArgumentError colorAE = wordsAreCorrect(putColorTextField.getText());
        ArgumentError accessoriesAE = listViewIsCorrect(putAccessoriesListView);

        ArgumentError[] argumentErrorResults = {vinAE, nameAE, mileageAE, registrationNumberAE, bodyTypeAE, colorAE, accessoriesAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                replaceCar();
                closeAllWindows(savePutCarButton);
                switchToCarsMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {VIN, NAME, MILEAGE, REGISTRATION_NUMBER, BODY_TYPE, COLOR, ACCESSORIES};
            Label[] errorLabels = {putErrorVinLabel, putErrorNameLabel, putErrorMileageLabel, putErrorRegistrationNumberLabel, putErrorBodyTypeLabel, putErrorColorLabel, putErrorAccessoriesLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }

    public void replaceCar(){
        CarMenuController.deleteSelectedObject();
        _accessories = new ArrayList<>(putAccessoriesListView.getItems());

        new Car(putVinTextField.getText(),
                putNameTextField.getText(),
                Integer.parseInt(putMileageTextField.getText()),
                putRegistrationNumberTextField.getText(),
                putBodyTypeTextField.getText(),
                putColorTextField.getText(),
                _accessories);
    }

    @FXML
    void removeAccessories() {
        if(putAccessoriesListView.getSelectionModel().getSelectedItem() != null) {
            int selectedID = putAccessoriesListView.getSelectionModel().getSelectedIndex();
            putAccessoriesListView.getItems().remove(selectedID);
        }
    }

    @FXML
    void addAccessories() {
        if(putAccessoriesTextField.getText() == null || putAccessoriesTextField.getText().isEmpty()) {
            putErrorAccessoriesLabel.setText(ACCESSORIES.getNullPointerExceptionMessage());
        } else {
            putAccessoriesListView.getItems().add(putAccessoriesTextField.getText());
            putAccessoriesTextField.clear();
        }
    }

    public void setAlreadyExistingValuesOfCar(Car car) {
        putVinTextField.setText(car.getVin());
        putNameTextField.setText(car.getName());
        putMileageTextField.setText(valueOf(car.getMileage()));
        putRegistrationNumberTextField.setText(car.getRegistrationNumber());
        putBodyTypeTextField.setText(car.getBodyType());
        putColorTextField.setText(car.getColor());
        putAccessoriesListView.setItems(FXCollections.observableList(car.getAccessories()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        putVinTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, VIN, putErrorVinLabel, wordWithNumbersIsCorrect(putVinTextField.getText()));
        });

        putNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, NAME, putErrorNameLabel, stringIsCorrect(putNameTextField.getText()));
        });

        putMileageTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, MILEAGE, putErrorMileageLabel, integerIsCorrect(putMileageTextField.getText()));
        });

        putRegistrationNumberTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, REGISTRATION_NUMBER, putErrorRegistrationNumberLabel, stringIsCorrect(putRegistrationNumberTextField.getText()));
        });

        putBodyTypeTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, BODY_TYPE, putErrorBodyTypeLabel, wordsAreCorrect(putBodyTypeTextField.getText()));
        });

        putColorTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, COLOR, putErrorColorLabel, wordsAreCorrect(putColorTextField.getText()));
        });
    }
}
