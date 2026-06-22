package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getAllRole extends Authentication {


	@Test
	public void get_role() {

		String url = get_baseurl();
		Response res = RestAssured.given().header("Authorization", "Bearer " + Authentication.token).when()
				.get(url + "roles/getAll");
		res.then().statusCode(200);
		res.prettyPrint();
	    Assert.assertEquals(res.getStatusCode(), 200);

	    Assert.assertEquals(
	            res.jsonPath().getString("message[0].key"),"success");

	    Assert.assertEquals(
	            res.jsonPath().getString("message[0].value"),"Role Retrieved successfully");

	    Assert.assertEquals(
	            res.jsonPath().getString("roles[0].originalRole"), "Admin");
	}
	
	@Test
	public void get_role_invalid_token() {

	    String url = get_baseurl();

	    Response res = RestAssured.given()
	            .header("Authorization", "Bearer invalidtoken123")
	            .when()
	            .get(url + "roles/getAll");

	    res.prettyPrint();

	    Assert.assertEquals(res.getStatusCode(), 401);
	}
	@Test
	public void get_role_invalid_endpoint() {

	    String url = get_baseurl();

	    Response res = RestAssured.given()
	            .header("Authorization", "Bearer " + token)
	            .when()
	            .get(url + "roles/getAlll");

	    res.prettyPrint();

	    Assert.assertEquals(res.getStatusCode(), 404);
	}
}
