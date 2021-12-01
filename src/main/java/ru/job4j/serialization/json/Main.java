package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Vehicle vehicle = new Vehicle(true, "Silverado", 2021,
                new LicensePlate("1111"), new String[] {"short bed", "four wheel drive"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(vehicle));
        final String vehicleJson =
                "{"  + "\"diesel\" : true,"
                     + "\"model\" : Silverado,"
                     + "\"year\" : 2021,"
                     + "\"License plate\" : "
                     + "{"
                     + "\"plate\" : \"1111\""
                     + "},"
                     + "\"misc\" : "
                     + "[\"short bed\", \"four wheel drive\"]"
                + "}";
        final Vehicle vehicleMod = gson.fromJson(vehicleJson, Vehicle.class);
        System.out.println(vehicleMod);
    }
}
