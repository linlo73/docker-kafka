package com.it.ibm.kafka.connectors.sink;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.it.ibm.kafka.connectors.source.JdbcSourceConnectorConfig;

public class JdbcSinkConnectorConfig extends AbstractConfig {
	private static final Logger logger = LogManager.getLogger(JdbcSinkConnectorConfig.class);
	public static final String CONNECTION_URL = "connection.url";
	public static final String CONNECTION_URL_DOC = "mysql URL to connect.";
	
	private static final String TOPIC_CONFIG = "topics";
	private static final String TOPIC_CONFIG_DOC = "topic";
	private static final String TOPIC_CONFIG_DISPLAY = "Topic d";
	

	public JdbcSinkConnectorConfig(ConfigDef definition, Map<?, ?> originals) {
		super(definition, originals);
	}

	public JdbcSinkConnectorConfig(Map<String, String> definition) {
		this(conf(), definition);
		logger.debug("JdbcSinkConfig");
	}

	public static ConfigDef conf() {
		return new ConfigDef() //
				.define(CONNECTION_URL, Type.STRING, Importance.HIGH, CONNECTION_URL_DOC)
				.define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_CONFIG_DOC);
	}

	public static String getConnectionUrl() {
		return CONNECTION_URL;
	}

	public static String getConnectionUrlDoc() {
		return CONNECTION_URL_DOC;
	}

	/**
	 * @return the topicConfig
	 */
	public static String getTopicConfig() {
		return TOPIC_CONFIG;
	}

	/**
	 * @return the topicConfigDisplay
	 */
	public static String getTopicConfigDisplay() {
		return TOPIC_CONFIG_DISPLAY;
	}

}
