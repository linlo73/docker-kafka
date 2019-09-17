package com.it.ibm.kafka.utilities;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Version {
	private static String version = "N/A";
	private static final Logger logger = LogManager.getLogger(Version.class);
	private static final String PROPS_PATH = "/kafka-connector.properties";
	
	static {
	    try (InputStream stream = Version.class.getResourceAsStream(PROPS_PATH)) {
	      Properties props = new Properties();
	      props.load(stream);
	      version = props.getProperty("version", version).trim();
	    } catch (Exception e) {
	     
	    	logger.warn("Error while loading version:", e);
	    }
	}

	public static String getVersion() {
		return version;
	}

}
