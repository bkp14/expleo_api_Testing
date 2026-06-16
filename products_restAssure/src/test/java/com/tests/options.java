package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class options {
  @Test
  public void options_method() {
	  Response res = RestAssured.given().when().options("http://localhost:3000/products");
	  System.out.println(res.statusLine());
	  
  }
}
