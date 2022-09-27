package models;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    private static final ArrayList<Car> _cars = new ArrayList<>();
    private final String _vin;
    private final String _name;
    private final int _mileage;
    private final String _registrationNumber;
    private final String _bodyType;
    private final String _color;
    private final ArrayList<String> _accessories;

    public Car(String vin, String name, int mileage, String registrationNumber, String bodyType, String color, ArrayList<String> accesories) {
        this._vin = vin;
        this._name = name;
        this._mileage = mileage;
        this._registrationNumber = registrationNumber;
        this._bodyType = bodyType;
        this._color = color;
        this._accessories = accesories;
        _cars.add(this);
    }

    public String getVin() {
        return _vin;
    }

    public String getName() {
        return _name;
    }

    public int getMileage() {
        return _mileage;
    }

    public String getRegistrationNumber() {
        return _registrationNumber;
    }

    public String getBodyType() {
        return _bodyType;
    }

    public String getColor() {
        return _color;
    }

    public ArrayList<String> getAccessories() {
        return _accessories;
    }

    public static ArrayList<Car> getCars() {
        return new ArrayList<>(_cars);
    }

    public static void deleteCar(Car car) {
        _cars.remove(car);
    }
}
