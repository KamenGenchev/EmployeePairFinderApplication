package com.sirmaacademy.employeepairfinderapp.util;

import java.time.LocalDate;
import java.time.format.*;

public class DateFormats {
    private static final String[] DATE_FORMATS =
            {
                    "dd-MM-yy",
                    "dd-MM-yyyy",
                    "yyyy-MM-dd",
                    "yy-MM-dd",
                    "dd/MM/yyyy",
                    "dd/MM/yy",
                    "MM/dd/yyyy",
                    "MM/dd/yy",
                    "yyyy/MM/dd",
                    "yy/MM/dd",
                    "yyyyMMdd",
                    "yyMMdd",
                    "dd/MM/yyyyг."
            };

    public static LocalDate convertDate(String date){
        if (date.equals("NULL") || date.equals("null")) {
            return LocalDate.now();
        }

        LocalDate localDate = null;
        for (String dateFormat : DATE_FORMATS) {
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
                return LocalDate.parse(date, format);
            } catch (DateTimeParseException ignore) {
            }
        }
        throw new IllegalArgumentException("Date format not supported " + date);
    }


}
