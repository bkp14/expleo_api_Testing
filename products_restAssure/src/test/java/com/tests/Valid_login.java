package com.tests;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Valid_login {
  @Test
  public void post_auth() {
	  Map <String, Object> payload = new HashMap<String, Object>();
	  payload.put("username", "admin");
	  payload.put("password", "admin123");
	  Response res = RestAssured.given().contentType(ContentType.JSON).body(payload).when()
			  .post("http://localhost:5000/login");
	  
	  Assert.assertEquals(res.getStatusCode(), 200);
	  String token = res.jsonPath().getString("token");
	  Assert.assertNotNull(token);
	  System.out.println("Token: " + token);
  }
}
