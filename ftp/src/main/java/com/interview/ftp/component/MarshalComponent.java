package com.interview.ftp.component;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MarshalComponent {
    //Convierte el parametro obj a xml
    public void marshal(Object object, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(object, new File(filePath));
    }
    public Object unmarshal( Object object, String filePath) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        return (Object) context.createUnmarshaller()
                .unmarshal(new FileReader(filePath));
    }
    public String XmltoString(String filePath) throws IOException {
        String xml = new String(
                Files.readAllBytes(Paths.get(filePath)));
        return xml;
    }
}
