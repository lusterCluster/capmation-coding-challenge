package com.interview.ftp.service;

import com.interview.ftp.JsonRespRepository;
import com.interview.ftp.domain.JsonResp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RMService {
private JsonRespRepository repository;
    public void saveRMJson(String json) {
        JsonResp jsonResp = new JsonResp();
        jsonResp.setValue(json);
        repository.save(jsonResp);
    }

}
