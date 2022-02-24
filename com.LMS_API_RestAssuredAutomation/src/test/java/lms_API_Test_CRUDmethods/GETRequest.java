package lms_API_Test_CRUDmethods;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import org.testng.annotations.Test;

import data_Provider.DataProvider_LMS;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETRequest extends BaseclassforLogin {

	Response response;

	@Test
	void Get_allPrograms() {

		response = httprequest.request(Method.GET);
		Assert.assertEquals(response.getStatusCode(), 200);

		// Get response body validation

		String responseBody = response.getBody().asPrettyString();
		Assert.assertEquals(responseBody != null, true);
	
		// Json Schema Validation

		response.then().assertThat().body(matchesJsonSchemaInClasspath("LMSJsonSchema\\GetRequest_schema.json"));

	}

	

	@Test(dataProvider="ProgramId",dataProviderClass=DataProvider_LMS.class,priority=1)
	void Get_programid(String ProgramId,String statuscode)
	{


		response =  httprequest.request(Method.GET, ProgramId);
		
		System.out.println(" Status code   : " + response.getStatusCode());
		//System.out.println( "Response Body : " + responseBody);

		int sc = Integer.parseInt(statuscode);
		Assert.assertEquals(response.getStatusCode(), sc);



		// Validation using JSON path
		JsonPath path = JsonPath.from(response.getBody().asString());

		if(response.getStatusCode()==200 )
		{

			if(response.getBody().asString().contains(ProgramId)) {

				int actualid = path.get("programId");
				int expProgId = Integer.parseInt(ProgramId);

				Assert.assertSame(actualid, expProgId);

			}

			else Assert.assertNull(null);
		}

	}
}
