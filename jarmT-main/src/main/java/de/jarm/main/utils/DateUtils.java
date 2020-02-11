package de.jarm.main.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
    public static String toString(LocalDateTime datum) {
        return datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss"));
    }

    public static LocalDateTime toDate(String datum) {
        return LocalDateTime.parse(datum, DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss"));
    }
	
}
