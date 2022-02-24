package lms_API_Test_CRUDmethods;

import java.util.Properties;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utility.ReadPropertyfile;

public class BaseclassforLogin {

	 public static RequestSpecification httprequest;
	
	@BeforeTest
	public void loginSetup() {
		
		ReadPropertyfile readprops= new ReadPropertyfile();
		Properties pros = readprops.loadProperties();
		RestAssured.baseURI =pros.getProperty("baseURI");
		httprequest = RestAssured.given().auth().basic(pros.getProperty("username"), pros.getProperty("password"));
		
	}


}
