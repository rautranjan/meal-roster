package com.meal;

import org.apache.commons.lang3.ArrayUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Home {

    private String[] flatmates;
    private Date vacuumDate;
    private String vacuumCleaner;
    private String[] recent;

    Home(Room room1, Room room2) {
        this.flatmates = ArrayUtils.addAll(room1.roommates, room2.roommates);
    }

    Home(Room room1, Room room2, String[] recent, String vacuumDateString, String vacuumCleaner) {
        this.flatmates = ArrayUtils.addAll(room1.roommates, room2.roommates);
        try {
            this.vacuumDate = new SimpleDateFormat("MM/dd/yyyy").parse(vacuumDateString);
            this.recent = recent;
            this.vacuumCleaner = vacuumCleaner;
        } catch (ParseException ex) {
            System.out.println("Invalid Date Format");
            this.vacuumDate = new Date();
        }
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

    public boolean productNextCleaningDate() {
        System.out.println((new Date().getTime() - vacuumDate.getTime()) / (24 * 60 * 60 * 1000));
        if ((new Date().getTime() - vacuumDate.getTime()) / (24 * 60 * 60 * 1000) >= 18)
            return true;
        return false;
    }
}