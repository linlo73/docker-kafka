package com.it.ibm.kafka.connectors.sink;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.apache.kafka.connect.sink.SinkTaskContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.it.ibm.kafka.connectors.source.JdbcSourceTaskConfig;
import com.it.ibm.kafka.utilities.Version;
import com.it.ibm.utilities.DatabaseUtils;

public class JdbcSinkTask extends SinkTask {
	private static final Logger logger = LogManager.getLogger(JdbcSinkTask.class);
	private static final String CLASSNAME = "JdbcSinkTask - ";
	private JdbcSinkConnectorConfig config;
	private String topic;
	private String renames;
	private Map mapRenames;

	public void initialize(SinkTaskContext context) {
	      this.context = context;
	  }
	@Override
	public String version() {
		logger.debug("version " + Version.getVersion());
		return Version.getVersion();
	}

	@Override
	public void start(Map<String, String> props) {
		logger.debug(CLASSNAME + "Starting JDBC Sink task");
		config = new JdbcSinkConnectorConfig(props);
		// get topic
		try {
			JdbcSinkTaskConfig config = new JdbcSinkTaskConfig(props);
			topic = props.get(config.getTopicConfig());
			renames = props.get(config.getRenamesConfig());
			
			mapRenames = new HashMap<String, String>();
			String[] pairs = renames.split(",");
			for (int i=0;i<pairs.length;i++) {
			    String pair = pairs[i];
			    String[] keyValue = pair.split(":");
			    mapRenames.put(keyValue[0],keyValue[1]);
			}
			
			logger.debug(CLASSNAME + " TOPIC " + topic +  " - renames >> " + renames);

		} catch (ConfigException e) {
			throw new ConnectException("Configuration error", e);
		}
	}

	@Override
	public void put(Collection<SinkRecord> records) {
		logger.debug("put - recordsize ->>>>>>>>>> " + records.size());
		logger.debug("put " + records.toString());
		if (records.isEmpty()) {
			return;
		}
		for (SinkRecord record : records) {
			logger.debug("TOPIC ->>>> " + record.topic());
			//logger.debug("first ->>>> " + record.toString());
			writeRecord(record);
			
			
		}

	}

	private void writeRecord(SinkRecord record) {
		Schema schema = record.valueSchema();
		Object value = record.value();
		long kafkaOffset = record.kafkaOffset();
		Integer kPartition = record.kafkaPartition();
		
		Map mapSchema = DatabaseUtils.mapSchema(schema);
		logger.debug("schema list fields ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + schema);
		logger.debug("value ->>>> " + value);
		logger.debug("offset ->>>> " + kafkaOffset);
		logger.debug("partition ->>>> " + kPartition);
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		logger.debug("stop ");
	}

}
