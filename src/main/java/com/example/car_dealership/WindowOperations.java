package com.example.car_dealership;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

import static com.example.car_dealership.Main.save;

public abstract class WindowOperations {

    public static void switchToCarsMenuView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/car/car-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        Stage stage = createMainStage(scene, false, null, null);
    }

    public static void switchToEmployeeMenuView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/employee/employee-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        Stage stage = createMainStage(scene, false, null, null);
    }

    public static void switchToIndividualClientMenuView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/individualclient/individualclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        Stage stage = createMainStage(scene, false, null, null);
    }

    public static void switchToInstitutionalClientMenuView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/institutionalclient/institutionalclient-menu-view.fxml"));
        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        Stage stage = createMainStage(scene, false, null, null);
    }


    static void addCssStyling(Scene scene, String cssPath) {
        String css = Main.class.getResource(cssPath).toExternalForm();
        scene.getStylesheets().add(css);
    }

    public static Scene createScene(FXMLLoader fxmlLoader, String cssPath, boolean menuViewSize) throws IOException {
        Scene scene;

        if (menuViewSize) {
            scene = new Scene(fxmlLoader.load(), 900, 600);
        } else {
            scene = new Scene(fxmlLoader.load(), 500, 600);
        }
        addCssStyling(scene, cssPath);
        return scene;
    }

    public static Stage createStage(Scene scene) {
        Stage stage = new Stage();
        stage.setMaxWidth(518);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        return stage;
    }

    public static Stage createMainStage(Scene scene, boolean sameWindow, ActionEvent event, MenuButton menuButton) {
        Stage stage;
        if (sameWindow) {
            if (menuButton == null)
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            else
                stage = (Stage) menuButton.getScene().getWindow();
        }
        else {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
        }

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            exitApplication(stage);
        });

        return stage;
    }


    public static void closeAllWindows(Button button) {
        //        List<Window> open = (List<Window>) Stage.getWindows().stream().filter(Window::isShowing);
        Window window = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        Stage stage1 = (Stage) window;
        stage1.close();

        Stage stage2 = (Stage) button.getScene().getWindow();
        stage2.close();
    }

    public static void exitApplication(Stage primaryStage) {
        DialogPane dialogPane;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wyjście");
        alert.setHeaderText("Wyjdź");
        alert.setContentText("Czy chcesz wyjść z aplikacji?");

        dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Main.class.getResource("css/exit.css").toExternalForm());
        dialogPane.getStyleClass().add("alertDialog");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Tak");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Nie");

        if (alert.showAndWait().get() == ButtonType.OK) {
            primaryStage.close();
            save();
        }
    }
}
