package com.example.car_dealership;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static com.example.car_dealership.WindowOperations.createMainStage;
import static com.example.car_dealership.WindowOperations.createScene;
import static file.FileOperations.*;

public class Main extends Application {
    private static final String path = ".\\src\\main\\java\\data\\data.txt";
    private static File file = new File(path);

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/start-view.fxml"));

        Scene scene = createScene(fxmlLoader, "css/styles.css", true);
        stage = createMainStage(scene, false, null, null);
    }

    public static void main(String[] args) {
        readFromFile(path);
        launch();
    }

    public static void save(){
        saveToFile(path);
    }
}