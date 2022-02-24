package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;



public class ReadPropertyfile {
		
	
	
	public  Properties loadProperties()  {
		try {
		Properties prop=new Properties();
		String configPath =System.getProperty("user.dir")+"/src/test/resources/properties/LMS.properties";
		FileInputStream input = new FileInputStream(configPath);
		prop.load(input);
		return prop;
	}catch(Exception e) {
		e.printStackTrace();
	}
		return null;
	
}
}
