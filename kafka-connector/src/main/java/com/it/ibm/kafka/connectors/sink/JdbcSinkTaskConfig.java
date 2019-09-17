package com.it.ibm.kafka.connectors.sink;

import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcSinkTaskConfig extends JdbcSinkConnectorConfig {

	private static final Logger logger = LogManager.getLogger(JdbcSinkTaskConfig.class);
	public static final String RENAMES_CONFIG = "renames";
	public static String getRenamesConfig() {
		return RENAMES_CONFIG;
	}

	public static final String RENAMES_CONFIG_DEFAULT = "";
	private static final String RENAMES_DOC = "List of tables for renames";

	static ConfigDef config = new ConfigDef()	//
			.define(	//
					RENAMES_CONFIG, //
					Type.LIST, //
					RENAMES_CONFIG_DEFAULT, //
					Importance.HIGH, //
					RENAMES_DOC);

	public JdbcSinkTaskConfig(Map<String, String> definition) {
		super(definition);
	}
}
