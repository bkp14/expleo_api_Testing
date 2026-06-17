package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Login extends BaseUrl {
  @Test
  public void Post_Login() {
	  String url = get_baseurl(); 
	  Map <String,Object> payload = new HashMap<>();
	  payload.put("email","sam@gmail.com");
	  payload.put("password", "123");
	  
	  Response res = RestAssured.given().contentType(ContentType.JSON)
			  .body(payload).post(url+"user/login");
	       res.then().statusCode(201);
  }
}
