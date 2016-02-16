package org.sample.gcm.server.beans;

import org.sample.gcm.server.client.GcmRestClient;
import org.sample.gcm.server.json.CustomData;
import org.sample.gcm.server.json.IncomingConfiguration;
import org.sample.gcm.server.json.IncomingRegistration;
import org.sample.gcm.server.persistence.Account;
import org.sample.gcm.server.persistence.AccountRepository;
import org.sample.gcm.server.persistence.Configuration;
import org.sample.gcm.server.utils.RegistrationEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Carlo on 10/01/2016.
 * A stupid bean which receives new registrations and push them to the other devices
 */
@RestController
@RequestMapping(consumes = "application/json",value = "/gcm/registration")
public class Registration {

    @Autowired private AccountRepository repository;
    @Autowired private GcmRestClient client;
    private Logger logger;

    @PostConstruct
    private void init(){
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @RequestMapping(value = "/device",method = RequestMethod.POST)
    public RegistrationEnum registerNewDevice(@RequestBody IncomingRegistration registration){

        logger.info("[REGISTRATION] registering new device " + registration.getAccountMail() + " at " + new Date().getTime());
        logger.info("Received new registration " + registration.toString());
        Account targetAccount = repository.findByAccountMail(registration.getAccountMail());
        try {
            if (targetAccount == null){
                Set<String> regids = new HashSet<>();
                regids.add(registration.getRegistrationId());
                List<Configuration> configurationList = new ArrayList<>();

                for (int i = 0; i < 5; i++){

                    Configuration tmp = new Configuration("Config " + 1, UUID.randomUUID().toString());
                    configurationList.add(tmp);
                }

                targetAccount = new Account(registration.getAccountMail(),regids,configurationList);
                repository.save(targetAccount);
                client.publicConfigurations(registration.getAccountMail());
                logger.info("[REGISTRATION] registered new device " + registration.getAccountMail()  + " at " + new Date().getTime());
                return RegistrationEnum.NEW_ACCOUNT_SEND_CONFIG;
            }
            else {
                Set<String> regids = targetAccount.getRegistrationIds();
                regids.add(registration.getRegistrationId());
                targetAccount.setRegistrationIds(regids);
                repository.save(targetAccount);
                logger.info("[REGISTRATION] registered new device " + registration.getAccountMail() + " at " + new Date().getTime());
            }
        }
        catch (DataAccessException e){
            return RegistrationEnum.REGISTRATION_FAILED;
        }
        logger.info("[REGISTRATION] registered new device " + registration.getAccountMail());
        return  RegistrationEnum.REGISTRATION_OK;
    }

    @RequestMapping(value = "/configuration", method = RequestMethod.POST)
    public boolean setConfiguration(@RequestHeader ("Registration-Id") String registrationId, @RequestBody IncomingConfiguration configurations){

        if (registrationId == null){
            return false;
        }

        Account target = repository.findByAccountMail(configurations.getAccountMail());
        LinkedHashMap<String,String>  targetConfig = configurations.getConfigurations();
        LinkedHashMap<String,String> accountConfig = target.toCustomData().getValues();

        for (String keyConfig : targetConfig.keySet()){
            accountConfig.put(keyConfig, targetConfig.get(keyConfig));
        }

        List<Configuration> configurationList = target.fromCustomValues(accountConfig);
        target.setConfigurations(configurationList);
        repository.save(target);

        client.publicConfigurations(configurations.getAccountMail());

        return true;
    }


}
