module com.example.car_dealership {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.car_dealership;
    opens com.example.car_dealership to javafx.fxml;

    exports models;
    opens models to javafx.fxml;

    exports com.example.car_dealership.controllers;
    opens com.example.car_dealership.controllers to javafx.fxml;

    exports com.example.car_dealership.controllers.car;
    opens com.example.car_dealership.controllers.car to javafx.fxml;

    exports com.example.car_dealership.controllers.employee;
    opens com.example.car_dealership.controllers.employee to javafx.fxml;

    exports com.example.car_dealership.controllers.individualclient;
    opens com.example.car_dealership.controllers.individualclient to javafx.fxml;

    exports com.example.car_dealership.controllers.institutionalclient;
    opens com.example.car_dealership.controllers.institutionalclient to javafx.fxml;
}