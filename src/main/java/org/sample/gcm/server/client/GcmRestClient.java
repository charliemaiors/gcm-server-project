package org.sample.gcm.server.client;

import com.google.gson.Gson;
import org.sample.gcm.server.persistence.AccountRepository;
import org.sample.gcm.server.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Carlo on 10/01/2016.
 */
@Service
public class GcmRestClient {

    @Autowired private Gson mapper;
    @Autowired private AccountRepository repository;
    private Properties properties;
    private Logger logger;
    private String url;

    @PostConstruct
    private void init() throws IOException {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.properties = ConfigReader.readConfig();
        this.url = properties.getProperty("");
    }




}
