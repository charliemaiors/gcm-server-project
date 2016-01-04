package org.sample.gcm.server.beans;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by Carlo on 04/01/2016.
 */
@RestController
@RequestMapping("/gcm/rest/app")
public class RegistrationEndpoint {

    @Autowired private Gson mapper;
    private Logger logger;

    @PostConstruct
    private void init() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

}
