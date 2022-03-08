package jobs_API_Test_CRUDmethods;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import data_Provider.DataProvider_Jobs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class POSTRequest {


	@Test(dataProvider="JObsAPIPOST",dataProviderClass=DataProvider_Jobs.class)
	void JobsPOSTreq(String JobId, String Jobtitle,String Joblocation,String JobcompanyName ,String Jobtype,
			String Jobposttime,String JobDescription,String statuscode) 
	{
		RestAssured.baseURI = "https://jobs123.herokuapp.com/Jobs";
		RequestSpecification httprequest= RestAssured.given();


		JSONObject requestParams = new JSONObject();
		requestParams.put("Job Id",JobId);
		requestParams.put("Job Title",Jobtitle);
		requestParams.put("Job Location",Joblocation);
		requestParams.put("Job Company Name",JobcompanyName);
		requestParams.put("Job Type",Jobtype);
		requestParams.put("Job Posted time",Jobposttime);
		requestParams.put("Job Description",JobDescription);


		httprequest.header("Content-Type","application/json");
		httprequest.body(requestParams.toJSONString());
		Response response = httprequest.request(Method.POST);

		int sc = Integer.parseInt(statuscode);
		System.out.println("Status code :"+ response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(),sc);


		//response body validation using Json path

		if(response.getStatusCode()==200) {

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
			String actualJobposted = path.get("data.\"Job Posted time\"."+jobIdkey+"");
			String actualJobdes = path.get("data.\"Job Description\"."+jobIdkey+"");


			Assert.assertEquals(actualJobTit,Jobtitle);
			Assert.assertEquals(actualJobloc,Joblocation);
			Assert.assertEquals(actualJobcomnam,JobcompanyName);
			Assert.assertEquals(actualJobTyp,Jobtype);
			Assert.assertEquals(actualJobposted,Jobposttime);
			Assert.assertEquals(actualJobdes,JobDescription);

		}
	}


}













//System.out.println(" job title" + path.get("data.\"Job Title\"."+jobIdkey+""));
//System.out.println(" job loc" + path.get("data.\"Job Location\"."+jobIdkey+""));
//System.out.println(" job comp nam" + path.get("data.\"Job Company Name\"."+jobIdkey+""));
//System.out.println(" job type" + path.get("data.\"Job Type\"."+jobIdkey+""));
//System.out.println(" job posted time" + path.get("data.\"Job Posted time\"."+jobIdkey+""));
//System.out.println(" job des" + path.get("data.\"Job Description\"."+jobIdkey+""));

// Response body validation using contains

//if(response.getStatusCode()==200)
//
//{
//Response getresponse = httprequest.request(Method.GET);
//			
//Assert.assertTrue(getresponse.getBody().asString().contains(JobId));
//Assert.assertTrue(getresponse.getBody().asString().contains(Jobtitle));
//Assert.assertTrue(getresponse.getBody().asString().contains(Joblocation));
//Assert.assertTrue(getresponse.getBody().asString().contains(JobcompanyName));
//Assert.assertTrue(getresponse.getBody().asString().contains(Jobtype));
//Assert.assertTrue(getresponse.getBody().asString().contains(Jobposttime));
//Assert.assertTrue(getresponse.getBody().asString().contains(JobDescription));
//}
//}

