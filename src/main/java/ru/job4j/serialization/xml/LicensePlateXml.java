package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LicensePlateXml")
public class LicensePlateXml {

    @XmlAttribute
    private String plate;

    public LicensePlateXml() {

    }

    public LicensePlateXml(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "LicensePlate{"
                + "plate='" + plate + '\''
                + '}';
    }
}
