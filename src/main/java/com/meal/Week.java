package com.meal;

import java.util.Date;

public class Week {

    private Shift monday, tuesday, wednesday, thursday, friday, saturday, saturdayNight, sunday, sundayNight;
    private String trashPicker;
    private Home home;

    Week(Home home) {
        this.home = home;
        this.initWeek();
    }

    public void initWeek() {
        this.home.rotateRecent();
        this.setShifts();
        this.trashPicker = home.getRecent()[3];
        this.home.setCleaningDate();
        this.home.getRoom1().productNextCleaningDate();
        this.home.getRoom2().productNextCleaningDate();
    }


    private void setShifts() {

        this.monday = new Shift(home.getRecent()[3], home.getRecent()[4], home.getRecent()[1]);
        this.tuesday = new Shift(home.getRecent()[0], home.getRecent()[1], home.getRecent()[3]);
        this.wednesday = new Shift(home.getRecent()[2], home.getRecent()[3], home.getRecent()[1]);
        this.thursday = new Shift(home.getRecent()[0], home.getRecent()[4], home.getRecent()[3]);
        this.friday = new Shift(home.getRecent()[1], home.getRecent()[2], home.getRecent()[0]);
        this.saturday = new Shift(home.getRecent()[0], home.getRecent()[3], home.getRecent()[4]);
        this.saturdayNight = new Shift("-", "-", home.getRecent()[2]);
        this.sunday = new Shift(home.getRecent()[1], home.getRecent()[2], home.getRecent()[0]);
        this.sundayNight = new Shift("-", "-", home.getRecent()[4]);

    }

    public Shift getMonday() {
        return monday;
    }

    public Shift getTuesday() {
        return tuesday;
    }

    public Shift getWednesday() {
        return wednesday;
    }

    public Shift getThursday() {
        return thursday;
    }

    public Shift getFriday() {
        return friday;
    }

    public Shift getSaturday() {
        return saturday;
    }

    public Shift getSaturdayNight() {
        return saturdayNight;
    }

    public Shift getSunday() {
        return sunday;
    }

    public Shift getSundayNight() {
        return sundayNight;
    }

    public String getTrashPicker() {
        return trashPicker;
    }

    public Home getHome() {
        return home;
    }

    @Override
    public String toString() {
        String temp = "Monday : " + this.monday + "\n" +
                "Tuesday : " + this.tuesday + "\n" +
                "Wednesday : " + this.wednesday + "\n" +
                "Thursday : " + this.thursday + "\n" +
                "Friday : " + this.friday + "\n" +
                "Saturday Morning : " + this.saturday + "\n" +
                "Saturday Night : " + this.saturdayNight + "\n" +
                "Sunday Morning : " + this.sunday + "\n" +
                "Sunday NIght : " + this.sundayNight + "\n" +
                "Trashpicker : " + this.trashPicker;

        if (this.home.getVacuumDate().getTime() - new Date().getTime() > 1) {
            temp += "\n" +
                    "HomeVacuum : " + this.home.getVacuumCleaner() + "\n"
                    + "HomeVacuumDate : " + this.home.getVacuumDate();
        }

        if (this.home.getRoom1().getVacuumLastDate().getTime() - new Date().getTime() > 1) {
            temp += "\n" +
                    "Room1 VacuumCleaner : " + this.home.getRoom1().getVacuumCleaner() + "\n"
                    + "Room1 VacuumDate : " + this.home.getRoom1().getVacuumLastDate();
        }

        if (this.home.getRoom1().getBathroomCleanLastDate().getTime() - new Date().getTime() > 1) {
            temp += "\n" +
                    "Room1 BathroomCleaner : " + this.home.getRoom1().getBathroomCleaner() + "\n"
                    + "Room1 BathroomDate : " + this.home.getRoom1().getBathroomCleanLastDate();
        }

        if (this.home.getRoom2().getVacuumLastDate().getTime() - new Date().getTime() > 1) {
            temp += "\n" +
                    "Room2 VacuumCleaner : " + this.home.getRoom2().getVacuumCleaner() + "\n"
                    + "Room2 VacuumDate : " + this.home.getRoom2().getVacuumLastDate();
        }

        if (this.home.getRoom2().getBathroomCleanLastDate().getTime() - new Date().getTime() > 1) {
            temp += "\n" +
                    "Room2 BathroomCleaner : " + this.home.getRoom2().getBathroomCleaner() + "\n"
                    + "Room2 BathroomDate : " + this.home.getRoom2().getBathroomCleanLastDate();
        }

        return temp;
    }
}