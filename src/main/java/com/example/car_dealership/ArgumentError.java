package com.example.car_dealership;

import javafx.scene.control.Label;

public enum ArgumentError {
    NULL,
    WRONG,
    WRONG_LENGTH,
    CORRECT,

    FIRST_NAME("Imię nie może być puste.", "Imię nie może zawierać spacji, cyfr znaków specjalnych.", "Imię może zawierać tylko 30 znaków."),
    LAST_NAME("Nazwisko nie może być puste.", "Nazwisko nie może zawierać spacji, cyfr oraz znaków \nspecjalnych.","Nazwisko może zawierać tylko 30 znaków."),
    DATE_OF_BIRTH("Data urodzenia nie może być pusta."),
    POSITION("Stanowisko nie może być puste.", "Stanowisko nie może zawierać znaków specjalnych.", "Stanowisko może zawierać tylko 30 znaków."),
    DATE_OF_EMPLOYMENT("Data zatrudnienia nie może być pusta."),
    RATE_PER_HOUR("Stawka za godzinę nie może być pusta.", "Stawka za godzinę musi być liczbą.", "Stawka za godzinę jest za duża."),

    EMAIL("Email nie może być pusty.", "Email jest niepoprawny.", "Email może zawierać tylko 30 znaków."),
    CONTACT_NUMBER("Numer kontatkowy nie może być pusty.", "Numer kontaktowy jest nie poprawny."),
    BILLING_ADDRESS("Adres rozliczeniowy nie może być pusty."),

    INSTITUTION_NAME("Nazwa instytucji nie może być pusta.", "Nazwa instytucji nie może JAKAS", "Nazwa instytucji może zawierać tylko 30 znaków."),
    INSTITUTION_ADDRESS("Adres instytucji nie może być pusty.", "Adres instytucji nie może JAKAS", "Adres instytucji może zawierać tylko 30 znaków."),

    VIN("VIN nie może być pusty.","VIN nie może zawierać znaków specjalnych.", "VIN może zawierać tylko 30 znaków."),
    NAME("Nazwa nie może być pusta.","","Nazwa może zawierać tylko 30 znaków."),
    REGISTRATION_NUMBER("Numer rejestacyjny nie może być pusty.","Numer rejestracyjny nie może zawierać znaków specjalnych.", "Numer rejestracyjny może zawierać tylko 30 znaków."),
    MILEAGE("Przebieg nie możę być pusty.", "Przebieg musi być liczbą.","Przebieg jest za duży."),
    BODY_TYPE("Typ nadwozia nie może być pusty.", "Typ nadwozia nie może zawierać znaków specjalnych.", ""),
    COLOR("Kolor nie może być pusty.", "Kolor nie może zawierać znaków specjalnych.", "Kolor może zawierać tylko 30 znaków."),
    ACCESSORIES("Akcesoria muszą zawierać co najmniej jeden element.", "Akcesorium jest puste.")
    ;


    String nullPointerExceptionMessage;
    String illegalArgumentExceptionMessage;
    String illegalArgumentLengthExceptionMessage;

    ArgumentError(String NullPointerExceptionMessage, String IllegalArgumentExceptionMessage, String IllegalArgumentLengthExceptionMessage){
        this.nullPointerExceptionMessage = NullPointerExceptionMessage;
        this.illegalArgumentExceptionMessage = IllegalArgumentExceptionMessage;
        this.illegalArgumentLengthExceptionMessage = IllegalArgumentLengthExceptionMessage;
    }

    ArgumentError(String NullPointerExceptionMessage, String IllegalArgumentExceptionMessage){
        this.nullPointerExceptionMessage = NullPointerExceptionMessage;
        this.illegalArgumentExceptionMessage = IllegalArgumentExceptionMessage;
    }

    ArgumentError(String message){
        this.nullPointerExceptionMessage = message;
    }

    ArgumentError(){}

    public String getNullPointerExceptionMessage(){
        return nullPointerExceptionMessage;
    }

    public String getIllegalArgumentExceptionMessage(){
        return illegalArgumentExceptionMessage;
    }

    public String getIllegalArgumentLengthExceptionMessage(){ return illegalArgumentLengthExceptionMessage;}

    public static void setAllArgumentErrors(ArgumentError argumentErrorResult, Label errorLabel, ArgumentError argumentError) {
        switch (argumentErrorResult) {
            case NULL -> errorLabel.setText(argumentError.getNullPointerExceptionMessage());
            case WRONG -> errorLabel.setText(argumentError.getIllegalArgumentExceptionMessage());
            case WRONG_LENGTH -> errorLabel.setText(argumentError.getIllegalArgumentLengthExceptionMessage());
            default -> errorLabel.setText(null);
        }
    }

    public static void validateTextField(boolean newVal, ArgumentError argumentError, Label errorLabel, ArgumentError result){
        try {
            if (!newVal) {
                switch (result) {
                    case NULL -> throw new NullPointerException(argumentError.getNullPointerExceptionMessage());
                    case WRONG -> throw new IllegalArgumentException(argumentError.getIllegalArgumentExceptionMessage());
                    case WRONG_LENGTH -> throw new IllegalArgumentException(argumentError.getIllegalArgumentLengthExceptionMessage());
                }
            } else {
                errorLabel.setText(null);
            }
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public static boolean noErrorsForArguments(ArgumentError[] argumentErrorResults) {
        boolean dataIsCorrect = true;
        for (ArgumentError argumentErrorResult : argumentErrorResults) {
            if (argumentErrorResult != CORRECT) {
                dataIsCorrect = false;
                break;
            }
        }
        return dataIsCorrect;
    }
}
