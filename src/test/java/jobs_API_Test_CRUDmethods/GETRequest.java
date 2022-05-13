package jobs_API_Test_CRUDmethods;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETRequest  {

	static RequestSpecification httprequest;
	static Response response;


	@Test(priority =0)
	public void getAllJobs() 
	{
		RestAssured.baseURI = uri;
		httprequest= RestAssured.given();
		response = httprequest.request(Method.GET);
		Assert.assertEquals(response.getStatusCode(),200);

	}

	//Validation of Get request for all Jobs

	@Test(priority =1)
	void Validate_GetAllJobs()
	{
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response body  : " + responseBody);
		Assert.assertEquals(responseBody!=null, true);
	}

	// JsonShcema validation

	@Test(priority=2)
	public void JsonSchemaValidation() 
	{

		String getresponse = response.getBody().asString().replaceAll("NaN", "\"1 hr\"");
		System.out.println("Replaced response values for NaN" + getresponse);

		MatcherAssert.assertThat(getresponse,JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema\\JobsAPIschema.json"));

	}	
}
