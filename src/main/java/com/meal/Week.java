package com.meal;

public class Week {

    private Shift monday, tuesday, wednesday, thursday, friday, saturday, saturdayNight, sunday, sundayNight;
    private String trashPicker;
    private Home home;

    Week(Home home) {
        this.home = home;
        this.setShifts();
        this.trashPicker = home.getRecent()[3];
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

    @Override
    public String toString() {
        return this.monday + "\n" +
                this.tuesday + "\n" +
                this.wednesday + "\n" +
                this.thursday + "\n" +
                this.friday + "\n" +
                this.saturday + "\n" +
                this.saturdayNight + "\n" +
                this.sunday + "\n" +
                this.sundayNight + "\n" +
                this.trashPicker;
    }
}