package com.interview.ftp;


import com.interview.ftp.domain.JsonResp;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface JsonRespRepository extends  CrudRepository<JsonResp,String> {

}
