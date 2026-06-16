package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class get_product {
  @Test
  public void get_prod_data() {
	  Response res = RestAssured.given().when().get("http://localhost:3000/products/1");
	  res.then().statusCode(200);
	  System.out.println(res.getBody().asString());
  }
}
