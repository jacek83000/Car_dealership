package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


public class InstitutionalClient extends Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    private static ArrayList<InstitutionalClient> _institutionalClients = new ArrayList<>();
    private int _IdClient;
    private String _institutionName;
    private String _institutionAddress;

    public InstitutionalClient(String institutionName, String institutionAddress, String email, String contactNumber, String otherFormOfContact) {
        super(email, contactNumber, otherFormOfContact);
        this._IdClient = getUniqueID();
        this._institutionName = institutionName;
        this._institutionAddress = institutionAddress;
        _institutionalClients.add(this);
    }

    public InstitutionalClient(String institutionName, String institutionAddress, String email, String contactNumber) {
        super(email, contactNumber);
        this._IdClient = getUniqueID();
        this._institutionName = institutionName;
        this._institutionAddress = institutionAddress;
        _institutionalClients.add(this);
    }

    public int getIdClient() {
        return this._IdClient;
    }

    public String getInstitutionName() {
        return _institutionName;
    }

    public String getInstitutionAddress() {
        return _institutionAddress;
    }

    public static ArrayList<InstitutionalClient> getInstitutionalClients() {
        return new ArrayList<>(_institutionalClients);
    }

    public static void deleteInstitutionalClient(InstitutionalClient institutionalClient) {
        _institutionalClients.remove(institutionalClient);
    }

    public int getUniqueID() {
        int id = 1;
        boolean found = false;
        boolean alreadyExists;
        while (!found) {
            alreadyExists = false;
            for (InstitutionalClient institutionalClient : _institutionalClients) {
                if (id == institutionalClient.getIdClient()) {
                    alreadyExists = true;
                    break;
                }
            }
            if (alreadyExists)
                id++;
            else
                found = true;
        }
        return id;
    }
}
