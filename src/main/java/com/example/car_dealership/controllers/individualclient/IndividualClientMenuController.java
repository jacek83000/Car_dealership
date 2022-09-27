package com.example.car_dealership.controllers.individualclient;

import com.example.car_dealership.Main;
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
import models.IndividualClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.car_dealership.WindowOperations.*;

public class IndividualClientMenuController implements Initializable {

    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Stage stage;

    private DialogPane dialogPane;

    private static IndividualClient selectedIndividualClient;

    private ObservableList<IndividualClient> individualClientsObservable;

    @FXML
    private MenuButton clientsMenuButton;

    @FXML
    private TableView<IndividualClient> tableView;

    @FXML
    private TableColumn<IndividualClient, Integer> id;

    @FXML
    private TableColumn<IndividualClient, String> email;

    @FXML
    private TableColumn<IndividualClient, String> contactNumber;

    @FXML
    private TableColumn<IndividualClient, String> billingAddress;

    @FXML
    private TableColumn<IndividualClient, String> otherFormOfContact;

    @FXML
    void get() {
        selectedIndividualClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedIndividualClient != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/individualclient/individualclient-get-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-get.css", false);

                IndividualClientGetController individualClientGetController  = fxmlLoader.getController();
                individualClientGetController.initializeIndividualClient(selectedIndividualClient);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void post() {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("views/individualclient/individualClient-post-view.fxml"));
            scene = createScene(fxmlLoader, "css/styles-post-put.css", false);
            stage = createStage(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void put() {
        selectedIndividualClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedIndividualClient != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/individualclient/individualclient-put-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-post-put.css", false);

                IndividualClientPutController individualClientPutController  = fxmlLoader.getController();
                individualClientPutController.setAlreadyExistingValuesOfIndividualClient(selectedIndividualClient);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void delete() {
        selectedIndividualClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedIndividualClient != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Klient Indywidualny");
            alert.setHeaderText("Usunięcie klienta");
            alert.setContentText("Czy na pewno chcesz usunąć klienta o ID '" +
                    selectedIndividualClient.getIdClient() + "'?");

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
    void switchToCars(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/car/car-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, null);
    }

    @FXML
    void switchToEmployees(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/employee/employee-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, null);
    }

    @FXML
    void switchToInstitutionalClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/institutionalclient/institutionalclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, clientsMenuButton);
    }

    public static void deleteSelectedObject() {
        IndividualClient.deleteIndividualClient(selectedIndividualClient);
    }

    public void updateTable() {
        individualClientsObservable = FXCollections.observableArrayList(IndividualClient.getIndividualClients());
        tableView.setItems(individualClientsObservable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        individualClientsObservable = FXCollections.observableArrayList(IndividualClient.getIndividualClients());
        initializeTableColumns();
        tableView.setItems(individualClientsObservable);
    }

    void initializeTableColumns(){
        id.setCellValueFactory(new PropertyValueFactory<IndividualClient, Integer>("idClient"));
        email.setCellValueFactory(new PropertyValueFactory<IndividualClient, String>("email"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<IndividualClient, String>("contactNumber"));
        billingAddress.setCellValueFactory(new PropertyValueFactory<IndividualClient, String>("billingAddress"));
        otherFormOfContact.setCellValueFactory(new PropertyValueFactory<IndividualClient, String>("otherFormOfContact"));
    }
}
