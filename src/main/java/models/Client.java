package models;

import java.io.Serial;
import java.io.Serializable;

public abstract class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    private final String _email;
    private final String _contactNumber;
    private String _otherFormOfContact;

    public Client(String email, String contactNumber, String otherFormOfContact) {
        this._email = email;
        this._contactNumber = contactNumber;
        this._otherFormOfContact = otherFormOfContact;
    }

    public Client(String email, String contactNumber) {
        this._email = email;
        this._contactNumber = contactNumber;
    }

    public String getEmail() {
        return this._email;
    }

    public String getContactNumber() {
        return _contactNumber;
    }

    public String getOtherFormOfContact() {
        return _otherFormOfContact;
    }
}
