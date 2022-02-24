package data_Provider;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import utility.Read_ExcelData;

public class  DataProvider_LMS {


	@DataProvider(name="ProgramId") 
	String [][] getProgrmaId() throws IOException 
	{ 
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LMS TestDatas.xlsx";
		Read_ExcelData programIds  = new Read_ExcelData(path,"GetProgramId"); 
		return programIds.getInputData();

	}

	@DataProvider(name="LMS_POST")
	String [][] LMS_POSTrecord() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LMS TestDatas.xlsx";
		Read_ExcelData POSTdatas = new Read_ExcelData(path,"POSTrecord");
		return POSTdatas.getInputData();
	}
	@DataProvider(name="LMSPUT")
	String [][] LMS_PUTrecord() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LMS TestDatas.xlsx";
		Read_ExcelData PUTdatas = new Read_ExcelData(path,"PUTdata");
		return PUTdatas.getInputData();
	}
	@DataProvider(name="LMSDELETE")
	String [][] LMS_DELETErecord() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\LMS TestDatas.xlsx";
		Read_ExcelData DELId = new Read_ExcelData(path,"Delete");
		return DELId.getInputData();
	}

}