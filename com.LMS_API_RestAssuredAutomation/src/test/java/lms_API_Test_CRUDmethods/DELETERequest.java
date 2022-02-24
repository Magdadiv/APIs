package lms_API_Test_CRUDmethods;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import data_Provider.DataProvider_LMS;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DELETERequest extends BaseclassforLogin{
	
	static Response response;
	
	@Test(dataProvider="LMSDELETE",dataProviderClass=DataProvider_LMS.class)

	void DeleteId(String programId,String statuscode)
	{

		//send delete request
		response = httprequest.request(Method.DELETE, programId);
		System.out.println("Response body : " + response.getBody().asPrettyString());

		int sc = Integer.parseInt(statuscode);
		Assert.assertEquals(response.getStatusCode(), sc);
		
					
		//validating deleted Id is having null response body
		Response getresponse = httprequest.request(Method.GET,programId);
				
		if(getresponse.body()==null)
			Assert.assertEquals(getresponse.body(),null);

	}


}


