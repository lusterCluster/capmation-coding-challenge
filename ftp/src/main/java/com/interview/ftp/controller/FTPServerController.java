package com.interview.ftp.controller;

import com.interview.ftp.component.FtpComponent;
import com.interview.ftp.service.RMService;
import com.interview.ftp.xml.RickAndMortyXml;
import com.interview.ftp.service.FtpService;
import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequestMapping(value = "/interview")
@RestController
@AllArgsConstructor
public class FTPServerController {

    FtpService ftpService;
    FtpComponent ftpComponent;
    RMService rmService;

    @GetMapping(value = "/ftp")
    public ResponseEntity sendXML() {
        RickAndMortyXml xml = new RickAndMortyXml();
        //request json and save the file
        try {
            String json = ftpService.requestRmJson();
            System.out.println(json);
            ftpService.saveRMJsonFile(json);
            // convert json to xml and save in a local file
            xml = ftpService.toRickAndMortyXml(json);
            ftpService.marshalRickAndMorty(xml);
            //print the resulting xml
            String xmlString = ftpService.RmXmlToString();
            System.out.println("THE XML IS...: "+xmlString);
            //upload the json
            ftpComponent.uploadRMJson();

        } catch (IOException | JAXBException e) {
            System.out.println(e);
        }
        if(xml != null) {
            return new ResponseEntity( xml, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/mysql")
    public ResponseEntity saveJsonToDB() {

        String json = null;
        try {
            json = ftpService.requestRmJson();
            rmService.saveRMJson(json);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);

            // convert json to xml and save in a local f

return new ResponseEntity(HttpStatus.OK);

    }
}
