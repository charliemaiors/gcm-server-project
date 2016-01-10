package org.sample.gcm.server.client;

import com.google.gson.Gson;
import org.sample.gcm.server.persistence.AccountRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
