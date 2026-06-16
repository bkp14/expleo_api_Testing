package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class update_Project {
  @Test
  public void put_method() {
	  String payload = "{\n" +
              "\"id\": 1,\n" +
              "\"name\": \"Laptop\",\n" +
              "\"price\": 55000\n" +
              "}";
	  Response res = RestAssured.given().contentType(ContentType.JSON).body(payload).when().put("http://localhost:3000/products/2");
	  res.then().statusCode(200);
	  System.out.println( res.getStatusCode());
	  
  }
}
