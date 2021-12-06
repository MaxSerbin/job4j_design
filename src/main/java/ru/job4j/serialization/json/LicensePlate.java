package ru.job4j.serialization.json;

public class LicensePlate {
    private final String plate;

    public LicensePlate(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public String toString() {
        return "LicensePlate{"
                + "plate='" + plate + '\''
                + '}';
    }
}
