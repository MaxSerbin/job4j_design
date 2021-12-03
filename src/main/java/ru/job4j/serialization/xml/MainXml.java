package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainXml {
    public static void main(String[] args) throws Exception {
        final VehicleXml vehicleXml = new VehicleXml(true, "Silverado", 2021,
                new LicensePlateXml("1111"), new String[] {"short bed", "four wheel drive"});
        JAXBContext context = JAXBContext.newInstance(VehicleXml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(vehicleXml, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            VehicleXml result = (VehicleXml) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
