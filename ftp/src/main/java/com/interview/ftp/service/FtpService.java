package com.interview.ftp.service;


import com.interview.ftp.xml.RickAndMortyXml;
import com.interview.ftp.component.MarshalComponent;
import com.interview.ftp.component.RestComponent;
import com.interview.ftp.constant.IFilePaths;
import com.interview.ftp.constant.IUnirestURI;
import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
public class FtpService {
RestComponent restComponent;
MarshalComponent marshalComponent;
public String requestRmJson() throws IOException {
    return restComponent.sendUniresGetRequest(IUnirestURI.rickAndMortyApi);
}

public void saveRMJsonFile(String rmJson) throws IOException{
    restComponent.saveJsonFile(rmJson);
}
    public RickAndMortyXml toRickAndMortyXml(String RmJson) {
        return (RickAndMortyXml) restComponent.jsonToObject(new RickAndMortyXml(),RmJson);
    }
    public void marshalRickAndMorty(RickAndMortyXml dto) throws JAXBException {
        marshalComponent.marshal(dto, IFilePaths.RM_XML);
    }
    public RickAndMortyXml unMarshalRickAndMorty() throws JAXBException, IOException {
        return(RickAndMortyXml) marshalComponent.unmarshal(new RickAndMortyXml(),IFilePaths.RM_XML);
    }
public String RmXmlToString() throws IOException {
        return new String(
                Files.readAllBytes(Paths.get(IFilePaths.RM_XML)));
    }


}
