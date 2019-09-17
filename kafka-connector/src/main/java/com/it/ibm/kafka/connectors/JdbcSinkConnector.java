package com.it.ibm.kafka.connectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.it.ibm.kafka.connectors.sink.JdbcSinkConnectorConfig;
import com.it.ibm.kafka.connectors.sink.JdbcSinkTask;
import com.it.ibm.kafka.utilities.Version;

/**
 * @author lindalonardi
 *
 */
public class JdbcSinkConnector extends SinkConnector {
	private static final Logger logger = LogManager.getLogger(JdbcSinkConnector.class);
	private static final String CLASSNAME = "JdbcSinkConnector - ";
	private JdbcSinkConnectorConfig config;
	private Map<String, String> configProperties;


	/**
	 *
	 */
	@Override
	public String version() {
		logger.debug("version " + Version.getVersion());
		return Version.getVersion();
	}

	/**
	 *
	 */
	@Override
	public void start(Map<String, String> props) {
		logger.debug("start");
		setConfig(new JdbcSinkConnectorConfig(props));
		setConfigProperties(props);
		
	}



	/**
	 *
	 */
	@Override
	public Class<? extends Task> taskClass() {
		logger.debug("taskClass");
		return JdbcSinkTask.class;
	}

	/**
	 *
	 */
	@Override
	public List<Map<String, String>> taskConfigs(int maxTasks) {
		logger.debug(CLASSNAME + "taskConfigs" + maxTasks);
		final List<Map<String, String>> configs = new ArrayList<>(maxTasks);
		for (int i = 0; i < maxTasks; ++i) {
			configs.add(configProperties);
		}
		return configs;
	}

	/**
	 *
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	/**
	 *
	 */
	@Override
	public ConfigDef config() {
		logger.debug("config");
		final ConfigDef configDef = JdbcSinkConnectorConfig.conf();
//		configDef.define(Constants.CONFIG_REDIS_ADDRESS, Type.STRING, "redis://localhost:6379", Importance.HIGH,
//				"Redis address (redis://<host>:<port>)");
//		configDef.define(Constants.CONFIG_GREETING_LIST_KEY, Type.STRING, "greetings", Importance.HIGH,
//				"Redis key for greeting list");

		return configDef;
	}
	public Map<String, String> getConfigProperties() {
		return configProperties;
	}

	public void setConfigProperties(Map<String, String> configProperties) {
		logger.debug("config " + configProperties);
		this.configProperties = configProperties;
	}

	/**
	 * @return the config
	 */
	public JdbcSinkConnectorConfig getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	private ConfigDef setConfig(JdbcSinkConnectorConfig jdbcSinkConnectorConfig) {
		logger.debug(CLASSNAME + "1 - config");
		 return JdbcSinkConnectorConfig.conf();
		
	}
}
