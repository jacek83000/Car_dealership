package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class IndividualClient extends Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    private static final ArrayList<IndividualClient> _individualClients = new ArrayList<>();
    private final int _IdClient;
    private final String _billingAddress;

    public IndividualClient(String billingAddress, String email, String contactNumber, String otherFormOfContact) {
        super(email, contactNumber, otherFormOfContact);
        this._IdClient = getUniqueID();
        this._billingAddress = billingAddress;
        _individualClients.add(this);
    }

    public int getIdClient() {
        return this._IdClient;
    }

    public String getBillingAddress() {
        return _billingAddress;
    }

    public static ArrayList<IndividualClient> getIndividualClients() {
        return new ArrayList<>(_individualClients);
    }

    public static void deleteIndividualClient(IndividualClient individualClient) {
        _individualClients.remove(individualClient);
    }

    public int getUniqueID() {
        int id = 1;
        boolean found = false;
        boolean alreadyExists;
        while (!found) {
            alreadyExists = false;
            for (IndividualClient individualClient : _individualClients) {
                if (id == individualClient.getIdClient()) {
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

