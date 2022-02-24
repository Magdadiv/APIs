package lms_API_Test_CRUDmethods;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import data_Provider.DataProvider_LMS;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PUTRequest extends BaseclassforLogin{
	Response response;

	@Test(dataProvider="LMSPUT",dataProviderClass=DataProvider_LMS.class)
	void updateProgram(String XLprogId, String XLprogNam,String XLprogDes,String XLonline ,String statuscode)
	{

		boolean XL_ONLINE =Boolean.parseBoolean(XLonline);  

		JSONObject requestParams = new JSONObject();
		requestParams.put("programId",XLprogId );
		requestParams.put("programName", XLprogNam);
		requestParams.put("programDescription", XLprogDes);
		requestParams.put("online", XLonline);

		//add header stating the request body is json
		httprequest.header("Content-Type","application/json");
		//add json body of the request
		httprequest.body(requestParams.toJSONString());

		//send put request
		response = httprequest.request(Method.PUT,XLprogId);
		int sc = Integer.parseInt(statuscode);

		System.out.println("Json body" + response.getBody().asPrettyString());

		Assert.assertEquals(response.getStatusCode(), sc);


		//JSON Representation from Response Body
		JsonPath path = JsonPath.from(response.getBody().asString());

		//validation after updating record
		if(response.getStatusCode()==200) 
		{

			String progname = path.getString("programName");   
			String progdes = path.get("programDescription"); 
			boolean Online =path.get("online");				

			//			System.out.println("Program Name is :" + progname);
			//			System.out.println("Program Description is : " + progdes);
			//			System.out.println("online is : " + Online);


			Assert.assertEquals(progname,XLprogNam);
			Assert.assertEquals(progdes,XLprogDes);
			Assert.assertEquals(Online, XL_ONLINE);

			//  Schema Validation for the Put response(same schema for post and put)

			response.then().assertThat().body(matchesJsonSchemaInClasspath("LMSJsonSchema\\PostReq_schema.json"));


		}
	}
}
