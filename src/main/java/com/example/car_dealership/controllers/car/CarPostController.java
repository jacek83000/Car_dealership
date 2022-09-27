package com.example.car_dealership.controllers.car;

import com.example.car_dealership.ArgumentError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Car;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.car_dealership.ArgumentError.*;
import static com.example.car_dealership.CheckForValidation.*;
import static com.example.car_dealership.CheckForValidation.stringIsCorrect;
import static com.example.car_dealership.WindowOperations.*;

public class CarPostController implements Initializable {
    @FXML
    private Button cancelPostCarButton;

    @FXML
    private Button savePostCarButton;

    @FXML
    private TextField postVinTextField;

    @FXML
    private TextField postNameTextField;

    @FXML
    private TextField postMileageTextField;

    @FXML
    private TextField postRegistrationNumberTextField;

    @FXML
    private TextField postBodyTypeTextField;

    @FXML
    private TextField postColorTextField;
    @FXML
    private TextField postAccessoriesTextField;

    @FXML
    private ListView<String> postAccessoriesListView;


    @FXML
    private Label postErrorVinLabel;

    @FXML
    private Label postErrorNameLabel;

    @FXML
    private Label postErrorRegistrationNumberLabel;

    @FXML
    private Label postErrorMileageLabel;

    @FXML
    private Label postErrorBodyTypeLabel;

    @FXML
    private Label postErrorColorLabel;

    @FXML
    private Label postErrorAccessoriesLabel;


    private ArrayList<String> _accessories = new ArrayList<>();


    @FXML
    void cancelPostCar() {
        Stage stage = (Stage) cancelPostCarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void savePostCar() {
        ArgumentError vinAE = wordWithNumbersIsCorrect(postVinTextField.getText());
        ArgumentError nameAE = stringIsCorrect(postNameTextField.getText());
        ArgumentError mileageAE = integerIsCorrect(postMileageTextField.getText());
        ArgumentError registrationNumberAE = stringIsCorrect(postRegistrationNumberTextField.getText());
        ArgumentError bodyTypeAE = wordsAreCorrect(postBodyTypeTextField.getText());
        ArgumentError colorAE = wordsAreCorrect(postColorTextField.getText());
        ArgumentError accessoriesAE = listViewIsCorrect(postAccessoriesListView);

        ArgumentError[] argumentErrorResults = {vinAE, nameAE, mileageAE, registrationNumberAE, bodyTypeAE, colorAE, accessoriesAE};

        try {
            if (noErrorsForArguments(argumentErrorResults)) {
                createCar();
                closeAllWindows(savePostCarButton);
                switchToCarsMenuView();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            ArgumentError[] argumentErrors = {VIN, NAME, MILEAGE, REGISTRATION_NUMBER, BODY_TYPE, COLOR, ACCESSORIES};
            Label[] errorLabels = {postErrorVinLabel, postErrorNameLabel, postErrorMileageLabel, postErrorRegistrationNumberLabel, postErrorBodyTypeLabel, postErrorColorLabel, postErrorAccessoriesLabel};

            for (int i = 0; i < argumentErrorResults.length; i++) {
                setAllArgumentErrors(argumentErrorResults[i], errorLabels[i], argumentErrors[i]);
            }
        }
    }

    public void createCar() {
        _accessories = new ArrayList<>(postAccessoriesListView.getItems());

        new Car(postVinTextField.getText(),
                postNameTextField.getText(),
                Integer.parseInt(postMileageTextField.getText()),
                postRegistrationNumberTextField.getText(),
                postBodyTypeTextField.getText(),
                postColorTextField.getText(),
                _accessories);
    }

    @FXML
    void removeAccessories() {
        if(postAccessoriesListView.getSelectionModel().getSelectedItem() != null) {
            int selectedID = postAccessoriesListView.getSelectionModel().getSelectedIndex();
            postAccessoriesListView.getItems().remove(selectedID);
        }
    }

    @FXML
    void addAccessories() {
        if(postAccessoriesTextField.getText() == null || postAccessoriesTextField.getText().isEmpty()) {
            postErrorAccessoriesLabel.setText(ACCESSORIES.getIllegalArgumentExceptionMessage());
        } else {
            postErrorAccessoriesLabel.setText(null);
            postAccessoriesListView.getItems().add(postAccessoriesTextField.getText());
            postAccessoriesTextField.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _accessories.add("apteczka");
        postAccessoriesListView.getItems().addAll(_accessories);

        postVinTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, VIN, postErrorVinLabel, wordWithNumbersIsCorrect(postVinTextField.getText()));
        });

        postNameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, NAME, postErrorNameLabel, stringIsCorrect(postNameTextField.getText()));
        });

        postMileageTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, MILEAGE, postErrorMileageLabel, integerIsCorrect(postMileageTextField.getText()));
        });

        postRegistrationNumberTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, REGISTRATION_NUMBER, postErrorRegistrationNumberLabel, stringIsCorrect(postRegistrationNumberTextField.getText()));
        });

        postBodyTypeTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, BODY_TYPE, postErrorBodyTypeLabel, wordsAreCorrect(postBodyTypeTextField.getText()));
        });

        postColorTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            validateTextField(newVal, COLOR, postErrorColorLabel, wordsAreCorrect(postColorTextField.getText()));
        });
    }
}
