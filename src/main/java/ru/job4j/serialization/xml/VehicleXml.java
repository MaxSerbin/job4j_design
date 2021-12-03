package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "vehicleXml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleXml {

    @XmlAttribute
    private boolean diesel;

    @XmlAttribute
    private String model;

    @XmlAttribute
    private int year;

    private LicensePlateXml lpt;

    @XmlElementWrapper
    @XmlElement(name="misc")
    private String[] misc;

    public VehicleXml() {

    }

    public VehicleXml(boolean diesel, String model, int year, LicensePlateXml lpt, String[] misc) {
        this.diesel = diesel;
        this.model = model;
        this.year = year;
        this.lpt = lpt;
        this.misc = misc;
    }

    @Override
    public String toString() {
        return "VehicleXml{"
                + "diesel=" + diesel
                + ", model='" + model + '\''
                + ", year=" + year
                + ", lpt=" + lpt
                + ", misc=" + Arrays.toString(misc)
                + '}';
    }
}
