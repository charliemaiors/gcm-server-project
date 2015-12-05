package org.sample.gcm.server.beans;

import org.jivesoftware.smack.XMPPConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Carlo on 05/12/2015.
 */
@Configuration
public class ConfigurationBeans {

    @Bean
    public XMPPConnection getConnectionConfiguration(){

        return null;
    }

}
