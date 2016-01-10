package org.sample.gcm.server.beans;

import com.google.gson.Gson;
import org.sample.gcm.server.json.IncomingRegistration;
import org.sample.gcm.server.persistence.AccountRepository;
import org.sample.gcm.server.persistence.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by Carlo on 10/01/2016.
 */
@RestController
@RequestMapping(consumes = "application/json",value = "/gcm/registration")
public class Registration {

    @Autowired private Gson mapper;
    @Autowired private AccountRepository repository;
    private Logger logger;

    @PostConstruct
    private void init(){
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @RequestMapping(value = "/device",method = RequestMethod.POST)
    public boolean registerNewDevice(@RequestBody IncomingRegistration registration){

    }

}
