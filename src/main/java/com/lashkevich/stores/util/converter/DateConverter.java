package com.lashkevich.stores.util.converter;

import java.sql.Date;
import java.time.LocalDate;

public class DateConverter {
    public static java.sql.Date convertLocalDateToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toLocalDate();
    }
}
