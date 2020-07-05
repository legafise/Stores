package com.lashkevich.stores.util.converter;

import java.sql.Date;
import java.time.LocalDate;

public final class NNSDateConverter {
    private NNSDateConverter() {
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toLocalDate();
    }
}
