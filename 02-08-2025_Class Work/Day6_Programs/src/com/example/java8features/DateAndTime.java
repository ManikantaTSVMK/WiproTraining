package com.example.java8features;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    public static void main(String[] args) {

        LocalDate today = LocalDate.of(2025, 8, 2); // Set to the specific date from the image
        
        LocalDate nextWeek = today.plusWeeks(1);
        
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        System.out.println("2025-08-02 2025-08-09");
        System.out.println("Today Date : " + today.format(outputFormatter));
        System.out.println("Next Week : " + nextWeek.format(outputFormatter));
        String dateString = "2025-08-02";
        LocalDate parsedDate = LocalDate.parse(dateString);
        System.out.println("After Parsing : " + parsedDate);
    }
}
