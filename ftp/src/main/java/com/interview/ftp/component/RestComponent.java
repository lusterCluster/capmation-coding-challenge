package com.interview.ftp.component;

import com.google.gson.Gson;
import com.interview.ftp.constant.IFilePaths;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class RestComponent {
    public String sendUniresGetRequest(String url) throws IOException {
        String responseString = "";
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get(url).asString();
            responseString = response.getBody().toString();
        } catch (UnirestException e) {
            System.out.println(e);
        }
        if(responseString == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "the following api: "+ url +"did not respond");
        }
        return responseString;


    }
    public Object jsonToObject (Object object, String json) {
        Gson gson = new Gson();
        Object dto = gson.fromJson(json,object.getClass());
        return  dto;
    }
    public void saveJsonFile(String json) {

        try (FileWriter file = new FileWriter(IFilePaths.RM_JSON)) {
            file.write(json);
            System.out.println("JSON guardado en " + IFilePaths.RM_JSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
