package com.it.ibm.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtils {
	private static final Logger logger = LogManager.getLogger(FileUtils.class);
	public static Properties readConfigFile(String fileName) {
		 Properties prop = new Properties();
		 try (InputStream input = FileUtils.class.getClassLoader().getResourceAsStream(fileName)) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            logger.debug(prop.getProperty("name"));
	            logger.debug(prop.getProperty("connector.class"));
	            logger.debug(prop.getProperty("tasks.max"));
	            logger.debug(prop.getProperty("topics"));
	            logger.debug(prop.getProperty("connection.url"));
	            
	            return prop;

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		return null;
	}

}
