package data_Provider;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import utility.Read_ExcelData;

public class  DataProvider_Jobs {



	@DataProvider(name="JObsAPIPOST")
	String [][] JobAPIPOSTrecord() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\JobsAPI_testData.xlsx";
		Read_ExcelData JobapiPOSTdatas = new Read_ExcelData(path,"postdatas");
		return JobapiPOSTdatas.getInputData();
	}
	@DataProvider(name="JObsAPIPUT")
	String [][] JobAPIPUTrecord() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\JobsAPI_testData.xlsx";
		Read_ExcelData JobapiPUTdatas = new Read_ExcelData(path,"Putdatas");
		return JobapiPUTdatas.getInputData();
	}
	@DataProvider(name="JobsAPIDELETE")
	String [][] JobsAPI_DELETErecord() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\JobsAPI_testData.xlsx";
		Read_ExcelData DELId = new Read_ExcelData(path,"Deletedatas");
		return DELId.getInputData();
	}
}