package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getAllCourse extends Authentication {
	
	@Test
	public void get_course() {

		String url = get_baseurl();
		Response res = RestAssured.given().header("Authorization", "Bearer " + Authentication.token).when()
				.get(url + "courses-structure/getAll");

		res.then().statusCode(200);

		int status = res.getStatusCode();
		res.prettyPrint();
		Assert.assertEquals(status, 200);
	    Assert.assertNotNull(res.jsonPath().getString("data[0]._id"));

	    Assert.assertNotNull(res.jsonPath().getString("data[0].institution"));
	}
	@Test
	public void get_course_without_token() {

	    String url = get_baseurl();

	    Response res = RestAssured.given()
	            .when()
	            .get(url + "courses-structure/getAll");
     res.then().statusCode(401);
	    Assert.assertEquals(res.getStatusCode(), 401);

	    res.prettyPrint();
	}
}
