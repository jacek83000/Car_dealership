package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.car_dealership.DateOperations.getWorkingDaysInMonth;


public class Employee extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    private static final ArrayList<Employee> _employees = new ArrayList<>();

    private final String _position;
    private final Calendar _dateOfEmployment;
    private double _ratePerHour;

    public static int WORKING_TIME_PER_DAY = 8;

    public Employee(String position, Calendar dateOfEmployment, double ratePerHour, String firstName, String lastName, Calendar dateOfBirth) {
        super(firstName, lastName, dateOfBirth);
        this._position = position;
        this._dateOfEmployment = dateOfEmployment;
        this._ratePerHour = ratePerHour;
        _employees.add(this);
    }

    public String getPosition() {
        return this._position;
    }

    public Calendar getDateOfEmployment() {
        return this._dateOfEmployment;
    }

    public double getRatePerHour() {
        return this._ratePerHour;
    }

    public static ArrayList<Employee> getEmployees() {
        return new ArrayList<>(_employees);
    }

    public double calculateMonthlySalary(Calendar calendar) {
        return getWorkingDaysInMonth(calendar) * WORKING_TIME_PER_DAY * this._ratePerHour;
    }

    public static void deleteEmployee(Employee employee) {
        _employees.remove(employee);
    }

    public static void increaseSalary(double salaryIncrease) {
        for (Employee employee : _employees) {
            employee._ratePerHour += salaryIncrease;
        }
    }
}