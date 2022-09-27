package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;


public abstract class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    private String _firstName;
    private String _lastName;
    private Calendar _dateOfBirth;
    private int _age;

    Calendar now = Calendar.getInstance();

    public Person(String firstName, String lastName, Calendar dateOfBirth) {
        this._firstName = firstName;
        this._lastName = lastName;
        this._dateOfBirth = dateOfBirth;
        setAge();
    }

    public Person() {
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public Calendar getDateOfBirth() {
        return _dateOfBirth;
    }

    public int getAge() {
        return _age;
    }

    public void setAge() {
        this._age = now.get(Calendar.YEAR) - _dateOfBirth.get(Calendar.YEAR);
    }
}
