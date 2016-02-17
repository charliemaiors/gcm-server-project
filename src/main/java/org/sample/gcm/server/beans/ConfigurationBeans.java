package org.sample.gcm.server.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Carlo on 05/12/2015.
 */
@Configuration
public class ConfigurationBeans {

    @Bean
    public Gson getMapper(){
        return new GsonBuilder().create();
    }

}
