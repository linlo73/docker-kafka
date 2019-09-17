package com.it.ibm.kafka.connectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectorContext;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.it.ibm.kafka.connectors.source.JdbcSourceConnectorConfig;
import com.it.ibm.kafka.connectors.source.JdbcSourceTask;
import com.it.ibm.kafka.connectors.source.SourceMonitorThread;
import com.it.ibm.kafka.utilities.Version;

/**
 * @author lindalonardi
 *
 */

public class JdbcSourceConnector extends SourceConnector {

	private static final Logger logger = LogManager.getLogger(JdbcSourceConnector.class);
	private static final String CLASSNAME = "JdbcSourceConnector - ";

	private Map<String, String> configProperties;
	private JdbcSourceConnectorConfig config;


	/**
	 *
	 */
	@Override
	public String version() {
		logger.debug(CLASSNAME + Version.getVersion());
		return Version.getVersion();
	}

	/**
	 *
	 */
	@Override
	public void start(Map<String, String> props) throws ConnectException {
		logger.debug(CLASSNAME + "start");
		logger.debug("props" + props);
		setConfigProperties(props);
		setConfig(new JdbcSourceConnectorConfig(props));
		
	}

	/**
	 *
	 */
	@Override
	public Class<? extends Task> taskClass() {
		logger.debug(CLASSNAME + "taskClass");

		Class<JdbcSourceTask> j;
		try {
			j = JdbcSourceTask.class;
			return j;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		// stop monitoring for tables
		// monitor.shutdown();
	}

	/**
	 * Define the configuration for the connector.
	 * 
	 * @return The ConfigDef for this connector; may not be null.
	 */
	@Override
	public ConfigDef config() {
		logger.debug(CLASSNAME + "1 - config");
		return JdbcSourceConnectorConfig.conf();
	}

	/**
	 * @return the configProperties
	 */
	public Map<String, String> getConfigProperties() {
		return configProperties;
	}

	/**
	 * @param configProperties the configProperties to set
	 */
	public void setConfigProperties(Map<String, String> configProperties) {
		this.configProperties = configProperties;
	}

	/**
	 * @return the config
	 */
	public JdbcSourceConnectorConfig getConfig() {
		logger.debug("getConfig");
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(JdbcSourceConnectorConfig config) {
		this.config = config;
	}

}
