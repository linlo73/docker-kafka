package com.it.ibm.kafka.connectors.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.utils.SystemTime;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.it.ibm.kafka.utilities.Constants;
import com.it.ibm.kafka.utilities.JdbcConnection;
import com.it.ibm.kafka.utilities.Version;
import com.it.ibm.utilities.DatabaseUtils;

public class JdbcSourceTask extends SourceTask {
	private static final Logger logger = LogManager.getLogger(JdbcSourceTask.class);
	private static final String CLASSNAME = "JdbcSourceTask - ";
	private String topic;
	private int maxTask;
	private int pollIntervalMs;
	private SystemTime time = new SystemTime();
	private final AtomicBoolean running = new AtomicBoolean(false);
	private Map<String, String> tables;

	// provo la connessione al db X volte
	private JdbcConnection jc = new JdbcConnection();
	private JdbcSourceTaskConfig config;
	
	public JdbcSourceTask() {
		this.setTime(new SystemTime());
	}

	@Override
	public void start(Map<String, String> props) {
		logger.debug(CLASSNAME + "start");
		 try {
		      config = new JdbcSourceTaskConfig(props);
		    } catch (ConfigException e) {
		      throw new ConnectException("Couldn't start JdbcSourceTask due to configuration error", e);
		    }
		// get topic
		try {
			JdbcSourceTaskConfig config = new JdbcSourceTaskConfig(props);
			topic = props.get(config.getTopicConfig());
			try {
				maxTask = Integer.parseInt(props.get(config.getTaskMaxConfig()));
			} catch (NumberFormatException e) {
				maxTask = config.TASK_MAX_CONFIG_DEFAULT;
				logger.info("No configuration for Max Tasks: use default "
						+ config.TASK_MAX_CONFIG_DEFAULT);
			}
			try {
				pollIntervalMs = Integer.parseInt(props.get(config.getPollIntervalMsConfig()));
			} catch (NumberFormatException e) {
				pollIntervalMs = config.POLL_INTERVAL_MS_CONFIG_DEFAULT;
				logger.info(
						"No configuration for Delay: use default " + config.POLL_INTERVAL_MS_CONFIG_DEFAULT);
			}

		} catch (ConfigException e) {
			throw new ConnectException("Configuration error", e);
		}

		// get connection
		HashMap<String, String> hmConnection = new HashMap<String, String> ();
		hmConnection.put(Constants.CONN_DRIVER, props.get(config.CONNECTION_DRIVER_CLASS));
		hmConnection.put(Constants.CONN_DB, props.get(config.CONNECTION_DB));
		hmConnection.put(Constants.CONN_PW, props.get(config.CONNECTION_PASSWORD));
		hmConnection.put(Constants.CONN_URL, props.get(config.CONNECTION_URL));
		
		
		logger.debug(CLASSNAME + " connectionUrl" + hmConnection.get(Constants.CONN_URL));
		try {
			jc.connectionOpen(hmConnection);
			// set list of tables to scan
			tables = DatabaseUtils.getTablesNames(jc, hmConnection.get(Constants.CONN_DB));
			
			List<Map<String, String>> partitions = new ArrayList<>(tables.size());
			Map<String, String> map = new HashMap<String, String>();
			map.put("table", "table");
			partitions.add(map);
			Map<Map<String, String>, Map<String, Object>>  offsets =null;
			if (context!=null) {
				offsets = context.offsetStorageReader().offsets(partitions);
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> context config" + context.configs());
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> context offsets" + context.offsetStorageReader().offsets(partitions));
			}
		} catch (ClassNotFoundException e) {
			logger.debug("JdbcSourceTask - ClassNotFoundException");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.debug("JdbcSourceTask - SQLException");
			e.printStackTrace();
		}
		running.set(true);
		logger.debug(CLASSNAME + " start - runnig a true " + topic);
	}

	@Override
	public List<SourceRecord> poll() throws InterruptedException {

		ArrayList<SourceRecord> records = new ArrayList<>();

		try {
			logger.debug(CLASSNAME + " poll - runnig " + running.get() + " - tables: " + tables);
			while (running.get()) {

				for (Map.Entry<String, String> table : tables.entrySet()) {
					ResultSet rs = null;
					rs = DatabaseUtils.executeQuery(jc, "select * from " + table.getValue());

					while (rs.next()) {
//						Map<String, Object> sourceOffset = new HashMap<>();
//						sourceOffset.put("id", "idAAA");
//						sourceOffset.put("idvalue", 2);
						
						
						Map<String, Object> sourcePartition = Collections.singletonMap("tablename", table.getValue());
						Schema schema = getSchema(table.getKey());
						Struct record = new Struct(schema)//
								.put("id", rs.getObject(1))//
								.put("surname", rs.getString(2));
//						String topic =  table.getKey();
						SourceRecord s = new SourceRecord(sourcePartition, null, topic, schema, record);
						Long timestamp = System.currentTimeMillis();
						s.newRecord(topic, null, null, null, null, record, timestamp);

						records.add(s);
					}

				}
				time.sleep(pollIntervalMs);
				logger.debug("Source records:" + records);
				return records;
			}
			// close resources
			return null;
		} catch (Exception e) {
			logger.debug(CLASSNAME + "exception poll " + e);
		}

		return null;
//		// for each table execute query
//		ResultSet rs = null;
//		for (Map.Entry<String, String> table : tables.entrySet()) {
//			System.out.println(table.getKey() + "/" + table.getValue());
//			
//			rs = DatabaseUtils.executeQuery(jc, "select * from " + table.getValue());
//
//			Schema valueSchema = null;
//			Schema schema = SchemaBuilder.struct().name(table.getValue()).field("id", Schema.INT8_SCHEMA)
//					.field("surname", Schema.STRING_SCHEMA).build();
//
//			Struct record = new Struct(schema);
//			record.schema();
//			JSONArray recordValue = Convertor.convertToJSON(rs);
//			SourceRecord s;
//			while (records.isEmpty()) {
//				Map<String, Object> sourcePartition = new HashMap<String, Object>();
//				sourcePartition.put("db", table.getValue());
//				sourcePartition.put("table", table.getValue());
//				
//				Map<String, Object> sourceOffset = null;// Collections.singletonMap("position", streamOffset);
//				Integer kafkaPartition;
//				Object key = null;
//				Object value = null;
//				Long timestamp = System.currentTimeMillis();
//				
//				Map<String, ?> offset = null;
//				
//				s = new SourceRecord(sourcePartition, offset, topic, null, schema, key, schema, value);
//						// SourceRecord(sourcePartition, offset,topic, schema, value);
//				records.add(s);
//				logger.debug("Source record:" + s);
//				rs.next();
//			}
//			time.sleep(delay);
//		}
	}

	private Schema getSchema(String tablename) {
		Schema schema = SchemaBuilder.struct() //
				.name(tablename).field("id", Schema.INT32_SCHEMA)//
				.field("surname", Schema.STRING_SCHEMA)//
				.build();
		return schema;
	}

	@Override
	public void stop() {
		try {
			logger.info(CLASSNAME + "Stop");

			running.set(false);
			if (jc != null)
				jc.connectionClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String version() {
		logger.debug("version " + Version.getVersion());
		return Version.getVersion();
	}

	/**
	 * @return the time
	 */
	public SystemTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(SystemTime time) {
		this.time = time;
	}

}
