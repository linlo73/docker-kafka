package com.it.ibm.kafka.connectors;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.it.ibm.kafka.connectors.sink.JdbcSinkConnectorConfig;
import com.it.ibm.kafka.utilities.MyDb;

class JdbcSinkConnectorTest {
	JdbcSourceConnector connector;
	HashMap props;
	MyDb db;
	void setup() {
		System.out.println("setup");
		connector = new JdbcSourceConnector();
	
	    db = new MyDb();
	    props = new HashMap<>();
	    props.put(JdbcSinkConnectorConfig.CONNECTION_URL, db.getUrl());
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
		fail("Not yet implemented");
	}

	@Test
	void testSinkConnector() {
		fail("Not yet implemented");
	}

	@Test
	void testConnector() {
		fail("Not yet implemented");
	}

	@Test
	void testInitializeConnectorContext() {
		fail("Not yet implemented");
	}

	@Test
	void testInitializeConnectorContextListOfMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testStartMapOfStringString1() {
		fail("Not yet implemented");
	}

	@Test
	void testReconfigure() {
		fail("Not yet implemented");
	}

	@Test
	void testTaskClass1() {
		fail("Not yet implemented");
	}

	@Test
	void testTaskConfigsInt1() {
		fail("Not yet implemented");
	}

	@Test
	void testValidate() {
		fail("Not yet implemented");
	}

	@Test
	void testConfig1() {
		fail("Not yet implemented");
	}

	@Test
	void testObject() {
		fail("Not yet implemented");
	}

	@Test
	void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	void testClone() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	void testWait() {
		fail("Not yet implemented");
	}

	@Test
	void testFinalize() {
		fail("Not yet implemented");
	}

}
