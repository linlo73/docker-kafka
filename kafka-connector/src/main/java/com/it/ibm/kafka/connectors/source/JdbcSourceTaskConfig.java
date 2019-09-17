package com.it.ibm.kafka.connectors.source;

import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcSourceTaskConfig extends JdbcSourceConnectorConfig {

	private static final Logger logger = LogManager.getLogger(JdbcSourceTaskConfig.class);
	public static final String TABLES_CONFIG = "tables";
	public static final String TABLES_CONFIG_DEFAULT = "";
	private static final String TABLES_DOC = "List of tables for this task to watch for changes.";

	static ConfigDef config = new ConfigDef()	//
			.define(	//
					TABLES_CONFIG, //
					Type.LIST, //
					TABLES_CONFIG_DEFAULT, //
					Importance.HIGH, //
					TABLES_DOC);

	public JdbcSourceTaskConfig(Map<String, String> definition) {
		super(definition);
	}
}
