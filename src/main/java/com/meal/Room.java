package com.meal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {
    String[] roommates;
    String vacuum, bathroom;
    Date lastDate;

    Room(String[] roommates) {
        this.roommates = roommates;
    }

    Room(String[] roommates, String vacuum, String bathroom, String lastDate) {
        this.roommates = roommates;
        this.vacuum = vacuum;
        this.bathroom = bathroom;
        try {
            this.lastDate = new SimpleDateFormat("MM/dd/yyyy").parse(lastDate);
        } catch (ParseException ex) {
            System.out.println("Invalid Date Format");
            this.lastDate = new Date();
        }
    }

    @Override
    public String toString() {


        return "Roommates: " + this.roommates + "\n" +
                "Vacuum : " + this.vacuum + "\n" +
                "Bathroom : " + this.bathroom + "\n" +
                "Last Cleaned : " + this.lastDate;
    }

    public boolean productNextCleaningDate() {
        if ((new Date().getTime() - lastDate.getTime()) / (24 * 60 * 60 * 1000) >= 21)
            return true;
        return false;
    }
}