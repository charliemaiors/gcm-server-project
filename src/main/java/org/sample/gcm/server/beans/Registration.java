package org.sample.gcm.server.beans;

import com.google.gson.Gson;
import org.sample.gcm.server.json.IncomingRegistration;
import org.sample.gcm.server.persistence.Account;
import org.sample.gcm.server.persistence.AccountRepository;
import org.sample.gcm.server.persistence.Configuration;
import org.sample.gcm.server.utils.RegistrationEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

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
    public RegistrationEnum registerNewDevice(@RequestBody IncomingRegistration registration){

        logger.debug("Received new registration " + registration.toString());
        Account targetAccount = repository.findByAccountName(registration.getAccountName());
        try {
            if (targetAccount == null) {
                targetAccount = new Account(registration.getAccountName(),registration.getAccountMail());
                Set<String> regids = targetAccount.getRegistrationIds();
                regids.add(registration.getRegistrationId());
                targetAccount.setRegistrationIds(regids);
                repository.save(targetAccount);
                return RegistrationEnum.NEW_ACCOUNT_SEND_CONFIG;
            }
            Set<String> regids = targetAccount.getRegistrationIds();
            regids.add(registration.getRegistrationId());
            targetAccount.setRegistrationIds(regids);
            repository.save(targetAccount);
        }
        catch (DataAccessException e){
            return RegistrationEnum.REGISTRATION_FAILED;
        }
        return  RegistrationEnum.REGISTRATION_OK;
    }

    @RequestMapping(value = "/configuration", method = RequestMethod.POST)
    public boolean setConfiguration(){
        return true;
    }
}
