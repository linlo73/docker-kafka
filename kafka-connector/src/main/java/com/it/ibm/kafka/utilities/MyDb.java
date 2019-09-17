package com.it.ibm.kafka.utilities;

public class MyDb {
	private String url ="jdbc:mysql://docker_testsql_1:3306/dbname?user=root&password=adm";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	


}
