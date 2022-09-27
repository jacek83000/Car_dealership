package com.example.car_dealership.controllers.institutionalclient;

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
import models.InstitutionalClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.car_dealership.WindowOperations.*;

public class InstitutionalClientMenuController implements Initializable {

    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Stage stage;

    private DialogPane dialogPane;

    private static InstitutionalClient selectedInstitutionalClient;

    private ObservableList<InstitutionalClient> institutionalClientsObservable;

    @FXML
    private MenuButton clientsMenuButton;

    @FXML
    private TableView<InstitutionalClient> tableView;

    @FXML
    private TableColumn<InstitutionalClient, Integer> id;

    @FXML
    private TableColumn<InstitutionalClient, String> email;

    @FXML
    private TableColumn<InstitutionalClient, String> contactNumber;

    @FXML
    private TableColumn<InstitutionalClient, String> institutionName;

    @FXML
    private TableColumn<InstitutionalClient, String> institutionAddress;

    @FXML
    private TableColumn<InstitutionalClient, String> otherFormOfContact;

    @FXML
    void get() {
        selectedInstitutionalClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedInstitutionalClient != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/institutionalclient/institutionalClient-get-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-get.css", false);

                InstitutionalClientGetController institutionalClientGetController  = fxmlLoader.getController();
                institutionalClientGetController.initializeInstitutionalClient(selectedInstitutionalClient);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void post() {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("views/institutionalclient/institutionalClient-post-view.fxml"));
            scene = createScene(fxmlLoader, "css/styles-post-put.css", false);
            stage = createStage(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void put() {
        selectedInstitutionalClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedInstitutionalClient != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/institutionalclient/institutionalClient-put-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-post-put.css", false);

                InstitutionalClientPutController institutionalClientPutController  = fxmlLoader.getController();
                institutionalClientPutController.setAlreadyExistingValuesOfInstitutionalClient(selectedInstitutionalClient);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void delete() {
        selectedInstitutionalClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedInstitutionalClient != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Klient Instytucjonalny");
            alert.setHeaderText("Usunięcie klienta");
            alert.setContentText("Czy na pewno chcesz usunąć klienta o ID '" +
                    selectedInstitutionalClient.getIdClient() + "'?");

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
    void switchToIndividualClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/individualclient/individualclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, clientsMenuButton);
    }

    public static void deleteSelectedObject() {
        InstitutionalClient.deleteInstitutionalClient(selectedInstitutionalClient);
    }

    public void updateTable() {
        institutionalClientsObservable = FXCollections.observableArrayList(InstitutionalClient.getInstitutionalClients());
        tableView.setItems(institutionalClientsObservable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        institutionalClientsObservable = FXCollections.observableArrayList(InstitutionalClient.getInstitutionalClients());
        initializeTableColumns();
        tableView.setItems(institutionalClientsObservable);
    }

    void initializeTableColumns(){
        id.setCellValueFactory(new PropertyValueFactory<InstitutionalClient, Integer>("idClient"));
        email.setCellValueFactory(new PropertyValueFactory<InstitutionalClient, String>("email"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<InstitutionalClient, String>("contactNumber"));
        institutionName.setCellValueFactory(new PropertyValueFactory<InstitutionalClient, String>("institutionName"));
        institutionAddress.setCellValueFactory(new PropertyValueFactory<InstitutionalClient, String>("institutionAddress"));
        otherFormOfContact.setCellValueFactory(new PropertyValueFactory<InstitutionalClient, String>("otherFormOfContact"));
    }
}
