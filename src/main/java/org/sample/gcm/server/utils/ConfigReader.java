package org.sample.gcm.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Carlo on 29/11/2015.
 */

//Remake with spring autoconfigure
public class ConfigReader {

    private static Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public static Properties readConfig() throws IOException {

        Properties properties = new Properties();
        logger.debug("Reading config...");
        File f = new File("/etc/gcm-server/gcm-project.properties");

        if (f.exists()){
            logger.debug("File " + f.getAbsolutePath() + " exist, reading...");
            properties.load(new FileInputStream(f));
        }
        else{
            logger.debug("File not found, load local properties");
            properties.load(ConfigReader.class.getResourceAsStream("gcm-properties.properties"));
        }

        logger.debug("Loaded properties " + properties);
        return properties;
    }

}
