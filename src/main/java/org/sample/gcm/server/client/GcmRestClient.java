package org.sample.gcm.server.client;

import com.google.gson.Gson;
import org.sample.gcm.server.configurations.Gcm;
import org.sample.gcm.server.json.CustomData;
import org.sample.gcm.server.json.GcmResponse;
import org.sample.gcm.server.json.Message;
import org.sample.gcm.server.persistence.Account;
import org.sample.gcm.server.persistence.AccountRepository;
import org.sample.gcm.server.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * Created by Carlo on 10/01/2016.
 */
@Service
public class GcmRestClient {

    @Autowired private Gson mapper;
    @Autowired private AccountRepository repository;
    @Autowired private Gcm gcm;
    private Properties properties;
    private Logger logger;
    private RestTemplate template;

    @PostConstruct
    private void init() throws IOException {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.properties = ConfigReader.readConfig();
        this.template = new RestTemplate();
    }

    public List<GcmResponse> publicConfigurations(String accountName){

        List<GcmResponse> responses = new ArrayList<>();
        logger.info("[GCM-REST-CLIENT] sending new configurations for " + accountName + " at " + new Date().getTime());
        logger.debug("Sending configurations to account " + accountName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "key=" + gcm.getApiKeys());

        Account account = repository.findByAccountMail(accountName);
        CustomData datas = account.toCustomData();

        for (String id : account.getRegistrationIds()){
            logger.info("[GCM-REST-CLIENT] sending to " + id + " at " + new Date().getTime());
            Message message = new Message(id,"config_push",new Long("100"),true,datas);
            HttpEntity<String> sendEntity = new HttpEntity<>(mapper.toJson(message,Message.class),headers);
            logger.debug("MESSAGE IS " + mapper.toJson(message,Message.class));
            ResponseEntity<String> sendResponse = template.exchange(gcm.getServer(), HttpMethod.POST,sendEntity,String.class);

            if (sendResponse.getStatusCode() == HttpStatus.UNAUTHORIZED){
                logger.debug("API KEY expired: " + gcm.getApiKeys());
            }
            logger.debug("RESPONSE BODY " + sendResponse.getBody());
            responses.add(mapper.fromJson(sendResponse.getBody(),GcmResponse.class));

        }

        logger.info("[GCM-REST-CLIENT] sent to " + accountName + " at " + new Date().getTime());

        return responses;
    }

    //TODO aggiungere creazione di gruppi per account


}
