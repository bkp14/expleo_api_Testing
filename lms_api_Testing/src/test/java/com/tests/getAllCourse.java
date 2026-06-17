package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getAllCourse extends BaseUrl {
	@BeforeClass
	public void setupAuth() {
		Authentication auth = new Authentication();
		auth.authenticate_post();

		Assert.assertNotNull(Authentication.token, "Failed to initialize token. Authentication step failed!");
	}

	@Test
	public void get_course() {

		String url = get_baseurl();
		Response res = RestAssured.given().header("Authorization", "Bearer " + Authentication.token).when()
				.get(url + "courses-structure/getAll");

		res.then().statusCode(200);

		int status = res.getStatusCode();
		res.prettyPrint();
		Assert.assertEquals(status, 200);
	}
}
