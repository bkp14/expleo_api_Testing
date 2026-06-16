package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class invalid_get {
  @Test
  public void get_prod_data() {
	  Response res = RestAssured.given().when().get("http://localhost:3000/products/100");
	  res.then().statusCode(404);
	 res.statusLine().equals("Not Found");
	 System.out.println(res.statusLine());
  }
}
