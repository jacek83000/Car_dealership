package com.example.car_dealership.controllers.employee;

import com.example.car_dealership.HttpRequestMethods;
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
import models.Employee;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import static com.example.car_dealership.WindowOperations.*;

public class EmployeeMenuController implements Initializable, HttpRequestMethods {

    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Stage stage;

    private DialogPane dialogPane;

    private static Employee selectedEmployee;

    private ObservableList<Employee> employeesObservable;

    @FXML
    private MenuButton clientsMenuButton;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> firstName;

    @FXML
    private TableColumn<Employee, String> lastName;

    @FXML
    private TableColumn<Employee, Calendar> dateOfBirth;

    @FXML
    private TableColumn<Employee, String> position;

    @FXML
    private TableColumn<Employee, Calendar> dateOfEmployment;

    @FXML
    private TableColumn<Employee, Double> ratePerHour;

    @Override
    public void get() {
        selectedEmployee = tableView.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/employee/employee-get-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-get.css", false);

                EmployeeGetController employeeGetController = fxmlLoader.getController();
                employeeGetController.initializeEmployee(selectedEmployee);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void post() {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("views/employee/employee-post-view.fxml"));
            scene = createScene(fxmlLoader, "css/styles-post-put.css", false);
            stage = createStage(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put() {
        selectedEmployee = tableView.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("views/employee/employee-put-view.fxml"));
                scene = createScene(fxmlLoader, "css/styles-post-put.css", false);

                EmployeePutController employeePutController = fxmlLoader.getController();
                employeePutController.setAlreadyExistingValuesOfEmployee(selectedEmployee);

                stage = createStage(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete() {
        selectedEmployee = tableView.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Pracownik");
            alert.setHeaderText("Usunięcie pracownika");
            alert.setContentText("Czy na pewno chcesz usunąć pracownika o imieniu '" +
                    selectedEmployee.getFirstName() + " " + selectedEmployee.getLastName() + "'?");

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

    public void increaseSalary() {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("views/employee/employee-salaryIncrease-view.fxml"));
            scene = createScene(fxmlLoader, "css/styles-post-put.css", false);
            stage = createStage(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToCars(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/car/car-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, null);
    }

    @FXML
    void switchToIndividualClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/individualclient/individualclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event,clientsMenuButton);
    }

    @FXML
    void switchToInstitutionalClients(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/institutionalclient/institutionalclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, true, event, clientsMenuButton);
    }

    public static void deleteSelectedObject() {
        Employee.deleteEmployee(selectedEmployee);
    }

    public void updateTable() {
        employeesObservable = FXCollections.observableArrayList(Employee.getEmployees());
        tableView.setItems(employeesObservable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeesObservable = FXCollections.observableArrayList(Employee.getEmployees());
        initializeTableColumns();
        tableView.setItems(employeesObservable);
    }

    void initializeTableColumns() {
        firstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        dateOfBirth.setCellFactory(column -> new TableCell<Employee, Calendar>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            @Override
            protected void updateItem(Calendar item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item.getTime()));
                }
            }
        });
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<Employee, Calendar>("dateOfBirth"));
        position.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        dateOfEmployment.setCellValueFactory(new PropertyValueFactory<Employee, Calendar>("dateOfEmployment"));
        dateOfEmployment.setCellFactory(column -> new TableCell<Employee, Calendar>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            @Override
            protected void updateItem(Calendar item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item.getTime()));
                }
            }
        });
        ratePerHour.setCellValueFactory(new PropertyValueFactory<Employee, Double>("ratePerHour"));
        ratePerHour.setCellFactory(column -> new TableCell<Employee, Double>() {
            private final NumberFormat decimalFormat = new DecimalFormat("#.00");

            @Override
            protected void updateItem(Double salary, boolean empty) {
                super.updateItem(salary, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(decimalFormat.format(salary));
                }
            }
        });
    }
}
