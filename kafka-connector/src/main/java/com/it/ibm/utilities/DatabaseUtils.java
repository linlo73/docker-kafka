package com.it.ibm.utilities;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;

import com.it.ibm.kafka.connectors.source.JdbcSourceConnectorConfig;
import com.it.ibm.kafka.utilities.JdbcConnection;

public class DatabaseUtils {

	public static Map<String,String> getTablesNames(JdbcConnection jc, String dbname) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			DatabaseMetaData metadata = jc.getConn().getMetaData();
			// Specify the type of object; in this case we want tables
			String[] types = { "TABLE" };
			ResultSet resultSet = metadata.getTables(null, null, "%", types);
			System.out.println("resultSet -" +resultSet.toString() ) ;
			while (resultSet.next()) {
				String tableName = resultSet.getString(3);
				String tableCatalog = resultSet.getString(1);
				String tableSchema = resultSet.getString(2);
				if(tableCatalog!=null && tableCatalog.equalsIgnoreCase(dbname)) {
					
					map.put(tableCatalog+tableName,tableName);
				}
				System.out.println("Table : " + tableName + " - nCatalog : " + tableCatalog + " - nSchema : " + tableSchema);

			}
			
		} catch (SQLException e) {

			System.out.println("Could not get database metadata " + e.getMessage());
		}
		return map;
	}

	public static ResultSet executeQuery(JdbcConnection jc, String stm) throws SQLException {
		ResultSet rs = jc.executeQuery(stm);
		return rs;
	}

	public static Map<String,String> mapSchema(Schema schema) {
		Map<String,String> mapSchema = new HashMap<String, String>();
		for (Field f:schema.fields()) {
			mapSchema.put(f.name(),  f.schema().type().getName());
		}
		return mapSchema;
	}
}
