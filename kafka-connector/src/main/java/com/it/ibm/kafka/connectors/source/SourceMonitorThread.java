package com.it.ibm.kafka.connectors.source;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SourceMonitorThread extends Thread {
	private static final Logger log = LogManager.getLogger(SourceMonitorThread.class);

	public SourceMonitorThread() {
		 log.debug("Starting SourceMonitorThread");
	}

	@Override
	public void run() {
		 log.debug("Starting thread to monitor tables.");
	}

}
