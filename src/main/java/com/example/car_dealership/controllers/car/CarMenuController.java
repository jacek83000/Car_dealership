package com.example.car_dealership.controllers.car;

import com.example.car_dealership.Main;
import com.example.car_dealership.controllers.individualclient.IndividualClientGetController;
import com.example.car_dealership.controllers.individualclient.IndividualClientPutController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Car;
import models.IndividualClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.car_dealership.WindowOperations.*;

public class CarMenuController implements Initializable {

    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Stage stage;

    private DialogPane dialogPane;

    private static Car selectedCar;

    private ObservableList<Car> carsObservable;

    @FXML
    private MenuButton clientsMenuButton;

    @FXML
    private TableColumn<Car, String> vin;

    @FXML
    private TableColumn<Car, String> name;

    @FXML
    private TableColumn<Car, Integer> mileage;

    @FXML
    private TableColumn<Car, String> registrationNumber;

    @FXML
    private TableColumn<Car, String> bodyType;

    @FXML
    private TableColumn<Car, String> color;

    @FXML
    private TableView<Car> tableView;

    @FXML
    void get() {
        selectedCar = tableView.getSelectionModel().getSelectedItem();

        if (selectedCar != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/car/car-get-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-get.css", false);

                CarGetController carGetController  = fxmlLoader.getController();
                carGetController.initializeCar(selectedCar);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void post() {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("views/car/car-post-view.fxml"));
            scene = createScene(fxmlLoader, "css/styles-post-put.css", false);
            stage = createStage(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void put() {
        selectedCar = tableView.getSelectionModel().getSelectedItem();

        if (selectedCar != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/car/car-put-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-post-put.css", false);

                CarPutController carPutController  = fxmlLoader.getController();
                carPutController.setAlreadyExistingValuesOfCar(selectedCar);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void delete() {
        selectedCar = tableView.getSelectionModel().getSelectedItem();

        if (selectedCar != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Samochód");
            alert.setHeaderText("Usunięcie samochodu");
            alert.setContentText("Czy na pewno chcesz usunąć samochód o nazwie '" +
                    selectedCar.getName() + "'?");

            dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Main.class.getResource("css/exit.css").toExternalForm());
            dialogPane.getStyleClass().add("alertDialog");

            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Tak");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Nie");

            if (alert.showAndWait().get() == ButtonType.OK) {
                deleteSelectedObject();
                alert.close();
                updateTable();
            }
        }
    }

    @FXML
    void switchToEmployees(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/employee/employee-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, null);
    }

    @FXML
    void switchToIndividualClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/individualclient/individualclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, clientsMenuButton);
    }

    @FXML
    void switchToInstitutionalClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/institutionalclient/institutionalclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, clientsMenuButton);
    }

    public static void deleteSelectedObject() {
        Car.deleteCar(selectedCar);
    }

    public void updateTable() {
        carsObservable = FXCollections.observableArrayList(Car.getCars());
        tableView.setItems(carsObservable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carsObservable = FXCollections.observableArrayList(Car.getCars());
        initializeTableColumns();
        tableView.setItems(carsObservable);
    }

    void initializeTableColumns(){
        vin.setCellValueFactory(new PropertyValueFactory<Car, String>("vin"));
        name.setCellValueFactory(new PropertyValueFactory<Car, String>("name"));
        mileage.setCellValueFactory(new PropertyValueFactory<Car, Integer>("mileage"));
        registrationNumber.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
        bodyType.setCellValueFactory(new PropertyValueFactory<Car, String>("bodyType"));
        color.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
    }
}
