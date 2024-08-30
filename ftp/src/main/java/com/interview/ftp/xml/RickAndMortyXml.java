package com.interview.ftp.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
public class RickAndMortyXml {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String origin1;
    private Location location;
    private Origin origin;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    @XmlRootElement
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Location {
        String name;
        String url;
    }
    @XmlRootElement
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Origin {
        String name;
        String url;
    }
}





