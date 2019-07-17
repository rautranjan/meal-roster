package com.meal;

import org.apache.commons.lang3.ArrayUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Home {

    private Room room1, room2;
    private String[] flatmates;
    private Date vacuumDate;
    private String vacuumCleaner;
    private String[] recent;

    Home(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
        this.flatmates = ArrayUtils.addAll(room1.getRoommates(), room2.getRoommates());
    }

    Home(Room room1, Room room2, String[] recent, String vacuumDateString, String vacuumCleaner) {
        this.room1 = room1;
        this.room2 = room2;
        this.flatmates = ArrayUtils.addAll(room1.getRoommates(), room2.getRoommates());
        try {
            this.vacuumDate = new SimpleDateFormat("MM/dd/yyyy").parse(vacuumDateString);
            this.recent = recent;
            this.vacuumCleaner = vacuumCleaner;
        } catch (ParseException ex) {
            System.out.println("Invalid Date Format");
            this.vacuumDate = new Date();
        }
    }

    public Room getRoom1() {
        return room1;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public void setRoom2(Room room2) {
        this.room2 = room2;
    }

    public String[] getFlatmates() {
        return flatmates;
    }

    public void setFlatmates(String[] flatmates) {
        this.flatmates = flatmates;
    }

    public Date getVacuumDate() {
        return vacuumDate;
    }

    public void setVacuumDate(Date vacuumDate) {
        this.vacuumDate = vacuumDate;
    }

    public String getVacuumCleaner() {
        return vacuumCleaner;
    }

    public void setVacuumCleaner(String vacuumCleaner) {
        this.vacuumCleaner = vacuumCleaner;
    }

    public String[] getRecent() {
        return recent;
    }

    public void setRecent(String[] recent) {
        this.recent = recent;
    }

    public void rotateRecent() {
        String[] temp = new String[this.recent.length];

        for(int i=1;i<this.recent.length;i++){
            temp[i-1] = this.recent[i];
        }
        temp[recent.length-1] = this.recent[0];

        this.setRecent(temp);
    }

    public void setCleaningDate() {
        if ((new Date().getTime() - vacuumDate.getTime()) / (24 * 60 * 60 * 1000) >= 21) {
            for (int i = 0; i < flatmates.length; i++) {

                if (i == flatmates.length - 1) {
                    vacuumCleaner = flatmates[0];
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, +7);
                    vacuumDate = cal.getTime();
                    break;
                }

                if (vacuumCleaner.equals(flatmates[i])) {
                    vacuumCleaner = flatmates[i + 1];
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, +7);
                    vacuumDate = cal.getTime();
                    break;
                }

            }
        }
    }
}