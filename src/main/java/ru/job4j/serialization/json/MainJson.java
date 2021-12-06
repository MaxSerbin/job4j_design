package ru.job4j.serialization.json;

import org.json.JSONObject;

public class MainJson {
    public static void main(String[] args) {
        final Vehicle vehicle = new Vehicle(true, "Silverado", 2021,
                new LicensePlate("1111"), new String[] {"short bed", "four wheel drive"});
        JSONObject jsonVehicle = new JSONObject();
        jsonVehicle.put("diesel", vehicle.isDiesel());
        jsonVehicle.put("model", vehicle.getModel());
        jsonVehicle.put("year", vehicle.getYear());
        jsonVehicle.put("plate", vehicle.getLpt());
        jsonVehicle.put("misc", vehicle.getMisc());
        System.out.println(jsonVehicle);
        System.out.println(new JSONObject(vehicle));
    }
}
