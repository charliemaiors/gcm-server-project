package org.sample.gcm.server.beans;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Carlo on 10/01/2016.
 */
@RestController
@RequestMapping(consumes = "application/json",value = "/gcm/registration")
public class Registration {

    @Autowired private Gson mapper;
    private Logger logger;


}
