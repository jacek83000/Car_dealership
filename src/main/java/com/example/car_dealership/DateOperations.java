package com.example.car_dealership;

import javafx.scene.control.DatePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public abstract class DateOperations {
    public static Calendar convertDatePickerToCalendar(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static LocalDate convertCalendarToDatePicker(Calendar calendar){
        return calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String convertCalendarToString(Calendar calendar){
        final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = calendar.getTime();
        return format.format(date);
    }

    public static int getWorkingDaysInMonth(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(year, month, firstDay);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(year, month, lastDay);

        int workingDays = 0;
        while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
            int dayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workingDays++;
            }
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return workingDays;
    }
}
