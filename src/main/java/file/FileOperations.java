package file;

import models.Car;
import models.Employee;
import models.IndividualClient;
import models.InstitutionalClient;

import java.io.*;
import java.util.ArrayList;

public class FileOperations implements Serializable {

    public static void readFromFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                System.out.println("WCZYTYWANIE Z PLIKU: Plik nie istnieje.");
            } else if(file.length() == 0L) {
                System.out.println("WCZYTYWANIE Z PLIKU: Plik jest pusty.");
            } else {
                streamDataFromFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Blad przy probie wczytania zawartosci z pliku!");
        } catch (ClassNotFoundException e) {
            System.err.println("Nie znaleziono klasy pasujacej do modeli!");
        }
    }

    public static void saveToFile(String path){
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            streamDataToFile(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void streamDataFromFile(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        ArrayList<Object> deserializedLists = (ArrayList<Object>) objectInputStream.readObject();
        createObjectsFromObjectList(deserializedLists);

        fileInputStream.close();
        objectInputStream.close();
    }

    public static void streamDataToFile(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(getObjectListOfOtherLists());

        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static void createObjectsFromObjectList(ArrayList<Object> deserializedLists) {
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                ArrayList<IndividualClient> individualClients = (ArrayList<IndividualClient>) deserializedLists.get(0);
                for (IndividualClient individualClient : individualClients) {
                    new IndividualClient(
                            individualClient.getBillingAddress(),
                            individualClient.getEmail(),
                            individualClient.getContactNumber(),
                            individualClient.getOtherFormOfContact());
                }
            } else if (i == 1) {
                ArrayList<InstitutionalClient> institutionalClients = (ArrayList<InstitutionalClient>) deserializedLists.get(1);
                for (InstitutionalClient institutionalClient : institutionalClients) {
                    new InstitutionalClient(
                            institutionalClient.getInstitutionName(),
                            institutionalClient.getInstitutionAddress(),
                            institutionalClient.getEmail(),
                            institutionalClient.getContactNumber(),
                            institutionalClient.getOtherFormOfContact());
                }
            } else if (i == 2) {
                ArrayList<Employee> employees = (ArrayList<Employee>) deserializedLists.get(2);
                for (Employee employee : employees) {
                    new Employee(
                            employee.getPosition(),
                            employee.getDateOfEmployment(),
                            employee.getRatePerHour(),
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getDateOfBirth());
                }
            } else {
                ArrayList<Car> cars = (ArrayList<Car>) deserializedLists.get(3);
                for (Car car : cars) {
                    new Car(
                            car.getVin(),
                            car.getName(),
                            car.getMileage(),
                            car.getRegistrationNumber(),
                            car.getBodyType(),
                            car.getColor(),
                            car.getAccessories());
                }

            }
        }
    }

    public static ArrayList<Object> getObjectListOfOtherLists() {
        ArrayList<Object> serialisedLists = new ArrayList<>();
        serialisedLists.add(IndividualClient.getIndividualClients());
        serialisedLists.add(InstitutionalClient.getInstitutionalClients());
        serialisedLists.add(Employee.getEmployees());
        serialisedLists.add(Car.getCars());

        return serialisedLists;
    }
}
