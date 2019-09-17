package com.it.ibm.utilities;

public abstract class MyQuery implements Comparable<MyQuery> {

	protected final String tableName;
	protected final long lastUpdate;
//	  protected final String query;
	public MyQuery(String tableName) {
		super();
		this.tableName = tableName;
		this.lastUpdate = 0;
	}
	
}
