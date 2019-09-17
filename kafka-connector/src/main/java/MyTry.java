import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.data.Schema;

import com.it.ibm.kafka.connectors.JdbcSinkConnector;
import com.it.ibm.kafka.connectors.JdbcSourceConnector;
import com.it.ibm.kafka.connectors.sink.JdbcSinkTask;
import com.it.ibm.kafka.connectors.source.JdbcSourceConnectorConfig;
import com.it.ibm.kafka.connectors.source.JdbcSourceTask;
import com.it.ibm.kafka.utilities.JdbcConnection;
import com.it.ibm.utilities.DatabaseUtils;

public class MyTry {

	static Map<String, String> props;
	
	public static void main(String[] args) {

//		Schema schema = null;
//		
//		DatabaseUtils.mapSchema(schema);
//		//		runSink();
		runSource();
		
		
		
		JdbcSourceConnector j = new JdbcSourceConnector();
		j.config();
		
		j.config();
		j.start(props);
		j.taskClass();
		JdbcSourceTask t = new JdbcSourceTask();
		t.start(props);
	}

	private static void runSink() {
		// TODO Auto-generated method stub
		props = returnSinkProps();
		System.out.println(props);
		JdbcSinkConnector sc = new JdbcSinkConnector();
		sc.start(props);
		sc.taskConfigs(1);
		JdbcSinkTask t = new JdbcSinkTask();
		t.start(props);
		t.put(null);
	}

	private static void runSource() {
		props = returnProps();
		JdbcSourceTask st = new JdbcSourceTask();
		st.start(props);
		try {
			st.poll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Map<String, String> returnProps() {
		Map<String, String> props = new HashMap<String,String>();
		 Properties prop = new Properties();
	           

			 try {
				BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/connect-jdbc-source.properties"));
				  String line;
				  while ((line = reader.readLine()) != null)
				  {
				    if (line.trim().length()==0) continue;
				    if (line.charAt(0)=='#') continue;
				    String delimiter = "=";
					// assumption here is that proper lines are like "String : http://xxx.yyy.zzz/foo/bar",
				    // and the ":" is the delimiter
				    int delimPosition = line.indexOf(delimiter);
				    String key = line.substring(0, delimPosition).trim();
				    String value = line.substring(delimPosition+1).trim();
				    props.put(key, value);
				  }
				  reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		
	
//		props.compute(key, remappingFunction)
		return props;
	}
	private static Map<String, String> returnSinkProps() {
		Map<String, String> props = new HashMap<String,String>();
		 Properties prop = new Properties();
	           

			 try {
				BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/connect-jdbc-sink.properties"));
				  String line;
				  while ((line = reader.readLine()) != null)
				  {
				    if (line.trim().length()==0) continue;
				    if (line.charAt(0)=='#') continue;
				    String delimiter = "=";
					// assumption here is that proper lines are like "String : http://xxx.yyy.zzz/foo/bar",
				    // and the ":" is the delimiter
				    int delimPosition = line.indexOf(delimiter);
				    String key = line.substring(0, delimPosition).trim();
				    String value = line.substring(delimPosition+1).trim();
				    props.put(key, value);
				  }
				  reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		
	
//		props.compute(key, remappingFunction)
		return props;
	}

}
