package com.tests;

import static org.hamcrest.Matchers.containsString;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getallinstitutions extends BaseUrl {
	@Test
	public void institute_get() {

		String url = get_baseurl();
		Response res = RestAssured.given().when().get(url + "getAll/institution");
		res.then().statusCode(200);
		int status = res.getStatusCode();
		res.prettyPrint();
		Assert.assertEquals(status, 200);
	}
}