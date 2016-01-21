package org.sample.gcm.server.client;

import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    private RestTemplate template;

    @PostConstruct
    private void init() throws IOException {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.properties = ConfigReader.readConfig();
        this.url = properties.getProperty("gcm.server");
        this.template = new RestTemplate();
    }

    public List<GcmResponse> publicConfigurations(String accountName){

        List<GcmResponse> responses = new ArrayList<>();
        logger.debug("Sending configurations to account " + accountName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "key=" + properties.getProperty("gcm.apiKeys"));

        Account account = repository.findByAccountName(accountName);
        CustomData datas = account.toCustomData();

        for (String id : account.getRegistrationIds()){

            Message message = new Message(id,"config",new Long("100"),true,datas);
            HttpEntity<String> sendEntity = new HttpEntity<>(mapper.toJson(message,Message.class),headers);
            ResponseEntity<String> sendResponse = template.exchange(url, HttpMethod.POST,sendEntity,String.class);

            if (sendResponse.getStatusCode() == HttpStatus.UNAUTHORIZED){
                logger.debug("API KEY expired: " + properties.getProperty("gcm.apiKeys"));
                System.exit(0);
            }
            responses.add(mapper.fromJson(sendResponse.getBody(),GcmResponse.class));

        }

        return responses;
    }


}
