package com.it.ibm.kafka.connectors;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.it.ibm.kafka.connectors.source.JdbcSourceConnectorConfig;
import com.it.ibm.kafka.utilities.MyDb;

class JdbcSourceConnectorTest {
	JdbcSourceConnector connector;
	HashMap props;
	MyDb db;
	
	void setup() {
		System.out.println("setup");
		connector = new JdbcSourceConnector();
	
	    db = new MyDb();
	    props = new HashMap<>();
	    props.put(JdbcSourceConnectorConfig.CONNECTION_URL, db.getUrl());
	    System.out.println(props);
	}
	
	@Test
	void testStop() {
		fail("Not yet implemented");
	}

	@Test
	void testVersion() {
		fail("Not yet implemented");
	}

	@Test
	void testStartMapOfStringString() {
		
	    setup();
		
	}

	@Test
	void testTaskClass() {
		fail("Not yet implemented");
	}

	@Test
	void testTaskConfigsInt() {
		fail("Not yet implemented");
	}

	@Test
	void testConfig() {
		 System.out.println("testconfig " + JdbcSourceConnectorConfig.getConnectionUrl());
	}

}
