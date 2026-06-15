package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserTest {
  @Test
  public void f() {
	  Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/1");
	  System.out.println("Response Body is =>  " + response.getStatusCode());
	  response.prettyPrint();
	  Assert.assertEquals(response.getStatusCode(),200);
	  response.prettyPrint();
	  
	  
	  String name = response.jsonPath().getString("name");
	Assert.assertEquals(name, "Leanne Graham");  
  }
}
