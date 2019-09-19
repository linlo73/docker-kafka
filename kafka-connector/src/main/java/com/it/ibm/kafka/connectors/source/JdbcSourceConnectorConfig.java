package com.it.ibm.kafka.connectors.source;

import java.util.Arrays;
import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Width;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcSourceConnectorConfig extends AbstractConfig {
	private static final Logger logger = LogManager.getLogger(JdbcSourceConnectorConfig.class);
	private static final String CLASSNAME = "JdbcSourceConnectorConfig - ";

	public static final String CONNECTION_URL = "connection.url";
	public static final String CONNECTION_URL_DOC = "URL to connect.";
	public static final String CONNECTION_URL_DISPLAY = null;

	public static final String CONNECTION_DRIVER_CLASS = "connection.driver.class";
	public static final String CONNECTION_DRIVER_CLASS_DOC = "connection driver class";
	public static final String CONNECTION_DRIVER_CLASS_DISPLAY = null;
	
	public static final String CONNECTION_USER = "connection.user";


	public static final String CONNECTION_USER_DOC = "USER to connect.";
	public static final String CONNECTION_USER_DISPLAY = null;
	
	public static final String CONNECTION_PASSWORD = "connection.password";
	public static final String CONNECTION_PASSWORD_DOC = "PWD to connect.";
	public static final String CONNECTION_PASSWORD_DISPLAY = null;
	
	public static final String CONNECTION_DB = "connection.db";
	public static final String CONNECTION_DB_DOC = "dbL to connect.";
	public static final String CONNECTION_DB_DISPLAY = null;
	
	private static final String TOPIC_CONFIG = "topics";
	private static final String TOPIC_CONFIG_DOC = "topics";
	private static final String TOPIC_CONFIG_DISPLAY = "topics d";

	private static final String TASK_MAX_CONFIG = "tasks.max";
	private static final String TASK_MAX_CONFIG_DOC = "Task max";
	private static final String TASK_MAX_CONFIG_DISPLAY = "Task max d";
	public static final int TASK_MAX_CONFIG_DEFAULT = 1;
	
	private static final String POLL_INTERVAL_MS_CONFIG = "poll.interval.ms";
	private static final String POLL_INTERVAL_MS_CONFIG_DOC = "POLL_INTERVAL_MS";
	private static final String POLL_INTERVAL_MS_CONFIG_DISPLAY = "POLL_INTERVAL_MS d";
	public static final int POLL_INTERVAL_MS_CONFIG_DEFAULT = 5000;
	

	// scan these tables
	private static final String TABLE_WHITELIST_CONFIG = "table.whitelist";
	private static final Object TABLE_WHITELIST_DEFAULT = "";
	private static final String TABLE_WHITELIST_DOC = "table whitelist";
	private static final String TABLE_WHITELIST_DISPLAY = "table whitelist d";

	// do not scan these tables
	private static final String TABLE_BLACKLIST_CONFIG = "table.blacklist";
	private static final Object TABLE_BLACKLIST_DEFAULT = "";
	private static final String TABLE_BLACKLIST_DOC = "table blacklist";
	private static final String TABLE_BLACKLIST_DISPLAY = "table blacklist d";

	private static final String DATABASE_GROUP = "Database";
	private static final String CONNECTOR_GROUP = "Connector Group";

	public static final ConfigDef CONFIG_DEF = conf();

	public JdbcSourceConnectorConfig(Map<String, String> definition) {
		super(CONFIG_DEF, definition);
		logger.debug(CLASSNAME + " JdbcSourceConnectorConfig");
	}

	public static ConfigDef conf() {
		logger.debug(CLASSNAME + " conf");
		int orderInGroup = 0;

		ConfigDef c = new ConfigDef() //
				.define(CONNECTION_URL, //
						Type.STRING, //
						Importance.HIGH, //
						CONNECTION_URL_DOC, //
						DATABASE_GROUP, ++orderInGroup, //
						Width.LONG, //
						CONNECTION_URL_DISPLAY, //
						Arrays.asList(TABLE_WHITELIST_CONFIG, TABLE_BLACKLIST_CONFIG))
				.define(CONNECTION_DB, //
						Type.STRING, //
						Importance.HIGH, //
						CONNECTION_DB_DOC, //
						DATABASE_GROUP, ++orderInGroup, //
						Width.LONG, //
						CONNECTION_DB_DISPLAY)
				.define(CONNECTION_USER, //
						Type.STRING, //
						Importance.LOW, //
						CONNECTION_USER_DOC, //
						DATABASE_GROUP, ++orderInGroup, //
						Width.LONG, //
						CONNECTION_USER_DISPLAY)
				.define(TASK_MAX_CONFIG, //
						Type.INT, //
						TASK_MAX_CONFIG_DEFAULT, //
						Importance.HIGH, //
						TASK_MAX_CONFIG_DOC, CONNECTOR_GROUP, //
						++orderInGroup, //
						Width.LONG, //
						TASK_MAX_CONFIG_DISPLAY)
				.define(TOPIC_CONFIG, //
						Type.STRING, //
						Importance.HIGH, //
						TOPIC_CONFIG_DOC, //
						CONNECTOR_GROUP, //
						++orderInGroup, Width.LONG, //
						TOPIC_CONFIG_DISPLAY)
				.define(TABLE_WHITELIST_CONFIG, //
						Type.LIST, //
						TABLE_WHITELIST_DEFAULT, //
						Importance.MEDIUM, //
						TABLE_WHITELIST_DOC, //
						DATABASE_GROUP, //
						++orderInGroup, //
						Width.LONG, //
						TABLE_WHITELIST_DISPLAY)
				.define(TABLE_BLACKLIST_CONFIG, //
						Type.LIST, //
						TABLE_BLACKLIST_DEFAULT, //
						Importance.MEDIUM, //
						TABLE_BLACKLIST_DOC, //
						DATABASE_GROUP, //
						++orderInGroup, //
						Width.LONG, //
						TABLE_BLACKLIST_DISPLAY);
		return c;

	}

	/**
	 * @return the connectionUrl
	 */
	public static String getConnectionUrl() {
		return CONNECTION_URL;
	}

	/**
	 * @return the connectionDb
	 */
	public static String getConnectionDb() {
		return CONNECTION_DB;
	}
	
	/**
	 * @return the topicConfig
	 */
	public static String getTopicConfig() {
		return TOPIC_CONFIG;
	}

	/**
	 * @return the taskMaxConfig
	 */
	public static String getTaskMaxConfig() {
		return TASK_MAX_CONFIG;
	}
	
	/**
	 * @return the pollIntervalMs
	 */
	public static String getPollIntervalMsConfig() {
		return POLL_INTERVAL_MS_CONFIG;
	}

	/**
	 * @return the connectionUser
	 */
	public static String getConnectionUser() {
		return CONNECTION_USER;
	}
	
	public static void main(String[] args) {
		logger.debug(CLASSNAME + " main");
	}
}
