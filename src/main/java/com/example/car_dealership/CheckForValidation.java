package com.example.car_dealership;

import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.example.car_dealership.ArgumentError.*;

public abstract class CheckForValidation {

    public static boolean isTooLong(String string) {
        return string.length() > 30;
    }

    public static boolean isAWord(String string) {
        return string.matches("^[a-zA-Z\\p{L}]+$");
    }

    public static boolean areWords(String string) {
        return string.matches("^([a-zA-Z\\p{L}]+(\\s)?)+$");
    }

    public static boolean isAWordWithNumbers(String string) {
        return string.matches("^\\w+$");
    }

    public static boolean isAnEmail(String string) {
        return string.matches("^(.+)@(\\S+)$");
    }

    public static boolean isAFloatNumber(String string) {
        return string.matches("\\d+(\\.\\d+)?");
    }

    public static boolean isAIntegerNumber(String string) {
        return string.matches("^\\d+$");
    }

    public static boolean isAContactNumber(String string) {
        return string.matches("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$");
    }


    public static ArgumentError wordIsCorrect(String attribute) {
        if (attribute == null || attribute.isEmpty())
            return NULL;
        else if (!isAWord(attribute))
            return WRONG;
        else if (isTooLong(attribute))
            return WRONG_LENGTH;
        else
            return CORRECT;
    }

    public static ArgumentError wordsAreCorrect(String attribute) {
        if (attribute == null || attribute.isEmpty())
            return NULL;
        else if (!areWords(attribute))
            return WRONG;
        else if (isTooLong(attribute))
            return WRONG_LENGTH;
        else
            return CORRECT;
    }

    public static ArgumentError wordWithNumbersIsCorrect(String attribute) {
        if (attribute == null || attribute.isEmpty())
            return NULL;
        else if (!isAWordWithNumbers(attribute))
            return WRONG;
        else if (isTooLong(attribute))
            return WRONG_LENGTH;
        else
            return CORRECT;
    }

    public static ArgumentError emailIsCorrect(String attribute) {
        if (attribute == null || attribute.isEmpty())
            return NULL;
        else if (!isAnEmail(attribute))
            return WRONG;
        else if (isTooLong(attribute))
            return WRONG_LENGTH;
        else
            return CORRECT;
    }

    public static ArgumentError contactNumberIsCorrect(String attribute) {
        if (attribute == null || attribute.isEmpty())
            return NULL;
        else if (!isAContactNumber(attribute))
            return WRONG;
        else if (isTooLong(attribute)) //nie wyrzuca!!!
            return WRONG_LENGTH;
        else
            return CORRECT;
    }

    public static ArgumentError stringIsCorrect(String attribute) {
        if (attribute == null || attribute.isEmpty())
            return NULL;
        else if (isTooLong(attribute))
            return WRONG_LENGTH;
        else
            return CORRECT;
    }

    public static ArgumentError integerIsCorrect(String intNumber) {
        if (intNumber == null || intNumber.isEmpty())
            return NULL;
        else if (!isAIntegerNumber(intNumber))
            return WRONG;
        else if (isTooLong(intNumber))
            return WRONG_LENGTH;
        else
            return CORRECT;

    }

    public static ArgumentError doubleIsCorrect(String doubleNumber) {
        if (doubleNumber == null || doubleNumber.isEmpty()) {
            return NULL;
        } else {
            String correctFormatDoubleNumber = doubleNumber.replaceAll(",", ".");
            if (!isAFloatNumber(correctFormatDoubleNumber))
                return WRONG;
            else if (isTooLong(doubleNumber))
                return WRONG_LENGTH;
            else
                return CORRECT;
        }
    }

    public static String setDecimalFormat(double doubleNumber) {
        final NumberFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(doubleNumber);
    }

    public static ArgumentError dateIsCorrect(DatePicker datePicker) {
        if (datePicker.getValue() == null)
            return NULL;
        else
            return CORRECT;
    }

    public static ArgumentError listViewIsCorrect(ListView<String> listView) {
        if (listView.getItems().size() == 0)
            return NULL;
        else
            return CORRECT;
    }
}
