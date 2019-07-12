package com.meal;

public class Shift {
    private String cleaner;
    private String chef1, chef2;

    Shift(String chef1, String chef2, String cleaner) {
        this.chef1 = chef1;
        this.chef2 = chef2;
        this.cleaner = cleaner;
    }

    public String getCleaner() {
        return cleaner;
    }

    public String getChef1() {
        return chef1;
    }

    public String getChef2() {
        return chef2;
    }

    @Override
    public String toString() {
        return "Chef1 : " + this.chef1 + ", Chef2: " + this.chef2 + ", DishCleaner: " + this.cleaner;
    }
}