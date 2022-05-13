package jobs_API_Test_CRUDmethods;

import java.net.URI;
import java.util.HashMap;

import org.bouncycastle.asn1.ocsp.Request;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data_Provider.DataProvider_Jobs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DELETErequest {

	RequestSpecification httprequest;
	Response response;

	@Test (dataProvider="JobsAPIDELETE",dataProviderClass=DataProvider_Jobs.class)

	void JobsDeletereq(String JobID,String statuscode)
	{
		RestAssured.baseURI = uri;
		httprequest= RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("Job Id",JobID);

		httprequest.header("Content-Type","application/json");
		httprequest.body(requestParams.toJSONString());

		Response response = httprequest.request(Method.DELETE);
		int sc = Integer.parseInt(statuscode);

		System.out.println("Json body" + response.getStatusCode());
		System.out.println("Json body" + response.getBody().asPrettyString());
		Assert.assertEquals(response.getStatusCode(), sc);

		//Validating deleted Id using GET request

		if(response.getStatusCode()==200) {
			Response getresponse = httprequest.request(Method.GET);
			Assert.assertEquals(getresponse.getBody().asPrettyString().contains(JobID),false);
		}
	}
}
