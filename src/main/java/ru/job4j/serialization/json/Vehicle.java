package ru.job4j.serialization.json;

import java.util.Arrays;

public class Vehicle {
    private final boolean diesel;
    private final String model;
    private final int year;
    private final LicensePlate lpt;
    private final String[] misc;


    public Vehicle(boolean diesel, String model, int year, LicensePlate lpt, String[] misc) {
        this.diesel = diesel;
        this.model = model;
        this.year = year;
        this.lpt = lpt;
        this.misc = misc;
    }

    public boolean isDiesel() {
        return diesel;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public LicensePlate getLpt() {
        return lpt;
    }

    public String[] getMisc() {
        return misc;
    }

    @Override
    public String toString() {
        return "Vehicle{"
                + "diesel=" + diesel
                + ", model='" + model + '\''
                + ", year=" + year
                + ", lpt=" + lpt
                + ", misc=" + Arrays.toString(misc)
                + '}';
    }
}
