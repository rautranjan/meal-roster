package com.meal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Room {
    private String[] roommates;
    private String vacuumCleaner, bathroomCleaner;
    private Date bathroomCleanLastDate, vacuumLastDate;

    Room(String[] roommates) {
        this.roommates = roommates;
    }

    Room(String[] roommates, String vacuumCleaner, String bathroomCleaner, String bathroomCleanLastDate, String vacuumLastDate) {
        this.roommates = roommates;
        this.vacuumCleaner = vacuumCleaner;
        this.bathroomCleaner = bathroomCleaner;
        try {
            this.bathroomCleanLastDate = new SimpleDateFormat("MM/dd/yyyy").parse(bathroomCleanLastDate);
        } catch (ParseException ex) {
            System.out.println("Invalid Date Format");
            this.bathroomCleanLastDate = new Date();
        }

        try {
            this.vacuumLastDate = new SimpleDateFormat("MM/dd/yyyy").parse(vacuumLastDate);
        } catch (ParseException ex) {
            System.out.println("Invalid Date Format");
            this.vacuumLastDate = new Date();
        }
    }

    public String[] getRoommates() {
        return roommates;
    }

    public void setRoommates(String[] roommates) {
        this.roommates = roommates;
    }

    public String getVacuumCleaner() {
        return vacuumCleaner;
    }

    public void setVacuumCleaner(String vacuumCleaner) {
        this.vacuumCleaner = vacuumCleaner;
    }

    public String getBathroomCleaner() {
        return bathroomCleaner;
    }

    public void setBathroomCleaner(String bathroomCleaner) {
        this.bathroomCleaner = bathroomCleaner;
    }

    public Date getBathroomCleanLastDate() {
        return bathroomCleanLastDate;
    }

    public void setBathroomCleanLastDate(Date bathroomCleanLastDate) {
        this.bathroomCleanLastDate = bathroomCleanLastDate;
    }

    public Date getVacuumLastDate() {
        return vacuumLastDate;
    }

    public void setVacuumLastDate(Date vacuumLastDate) {
        this.vacuumLastDate = vacuumLastDate;
    }

    @Override
    public String toString() {

        return "Roommates: " + this.roommates + "\n" +
                "Vacuum : " + this.vacuumCleaner + "\n" +
                "Bathroom : " + this.bathroomCleaner + "\n" +
                "Bathroom Last Cleaned : " + this.bathroomCleanLastDate + "\n" +
                "Vacuum Last Date : " + this.vacuumLastDate;
    }

    public void productNextCleaningDate() {
        if ((new Date().getTime() - bathroomCleanLastDate.getTime()) / (24 * 60 * 60 * 1000) >= 30) {
            for (int i = 0; i < roommates.length; i++) {

                if (i == roommates.length - 1) {
                    bathroomCleaner = roommates[0];
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, +7);
                    bathroomCleanLastDate = cal.getTime();
                    break;
                }

                if (bathroomCleaner.equals(roommates[i])) {
                    bathroomCleaner = roommates[i + 1];
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, +7);
                    bathroomCleanLastDate = cal.getTime();
                    break;
                }


            }
        }

        if ((new Date().getTime() - vacuumLastDate.getTime()) / (24 * 60 * 60 * 1000) >= 30) {
            for (int i = 0; i < roommates.length; i++) {


                if (i == roommates.length - 1) {
                    vacuumCleaner = roommates[0];
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, +7);
                    vacuumLastDate = cal.getTime();
                    break;
                }

                if (vacuumCleaner.equals(roommates[i])) {
                    vacuumCleaner = roommates[i + 1];
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, +7);
                    vacuumLastDate = cal.getTime();
                    break;
                }


            }
        }
    }
}