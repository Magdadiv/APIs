package jobs_API_Test_CRUDmethods;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data_Provider.DataProvider_Jobs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PUTRequest {

	RequestSpecification httprequest;
	Response response ;

	@Test(dataProvider="JObsAPIPUT",dataProviderClass=DataProvider_Jobs.class)
	void JobsPUTreq(String JobId, String Jobtit,String Jobloc,String JobcomNam,
			String Jobtyp,String statuscode)
	{

		RestAssured.baseURI = uri;
		httprequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("Job Id",JobId);
		requestParams.put("Job Title",Jobtit);
		requestParams.put("Job Location",Jobloc);
		requestParams.put("Job Company Name",JobcomNam);
		requestParams.put("Job Type",Jobtyp);

		httprequest.header("Content-Type","application/json");
		httprequest.body(requestParams.toJSONString());
		//send post request
		response = httprequest.request(Method.PUT);

		int sc = Integer.parseInt(statuscode);
		System.out.println("Json response body" + response.getBody().asPrettyString());
		Assert.assertEquals(response.getStatusCode(), sc);

		// Validation for updated record

		if(response.getStatusCode()==200) 	{

			Response getresponse = httprequest.request(Method.GET);

			JsonPath path = JsonPath.from(getresponse.getBody().asString().replaceAll("NaN", "null"));
			Map<String,String> jobids =	path.get("data.\"Job Id\"");

			//	System.out.println(" key value pair of all JobIds " + jobids);

			String jobIdkey=null;
			for (String key : jobids.keySet())
			{
				// getting key for the actual jobId value
				String IdValue = jobids.get(key);
				//System.out.println(" Job Id values " + IdValue);

				if(IdValue!=null) {
					if(jobids.get(key).equals(JobId)){
						System.out.println(" Key of the actual Job Id " + key);
						jobIdkey=key;
						break;
					}
				}
			}

			String actualJobTit = path.get("data.\"Job Title\"."+jobIdkey+"");
			String actualJobloc = path.get("data.\"Job Location\"."+jobIdkey+"");
			String actualJobcomnam = path.get("data.\"Job Company Name\"."+jobIdkey+"");
			String actualJobTyp = path.get("data.\"Job Type\"."+jobIdkey+"");

			Assert.assertEquals(actualJobTit,Jobtit);
			Assert.assertEquals(actualJobloc,Jobloc);
			Assert.assertEquals(actualJobcomnam,JobcomNam);
			Assert.assertEquals(actualJobTyp,Jobtyp);


			//		Assert.assertEquals(response.getBody().asPrettyString().contains(JobId),true);
			//		Assert.assertEquals(response.getBody().asPrettyString().contains(Jobtit),true);
			//		Assert.assertEquals(response.getBody().asPrettyString().contains(Jobloc),true);
			//		Assert.assertEquals(response.getBody().asPrettyString().contains(JobcomNam),true);
			//		Assert.assertEquals(response.getBody().asPrettyString().contains(Jobtyp),true);
			//		


		}

	}
}
