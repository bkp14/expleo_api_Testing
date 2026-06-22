package com.tests;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getallinstitutions extends BaseUrl {
	@Test
	public void institute_get() {

		String url = get_baseurl();
		Response res = RestAssured.given().when().get(url + "getAll/institution");
		res.then().statusCode(200).body("getAllInstitution[0].inst_id", equalTo("INS001"))
        .body("getAllInstitution[0].inst_name", equalTo("SmartCliff"));
		res.prettyPrint();
		
	}
	
	@Test
	public void institute_get_invalid_endpoint() {

	    String url = get_baseurl();

	    Response res = RestAssured.given()
	            .when()
	            .get(url + "getAll/institutionss");

	    res.then().statusCode(404);

	    System.out.println("Status Code: " + res.getStatusCode());
	    res.prettyPrint();
	}
	
}