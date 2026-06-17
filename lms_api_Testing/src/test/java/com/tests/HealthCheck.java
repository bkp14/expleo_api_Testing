package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.containsString;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HealthCheck extends BaseUrl {
  @Test
  public void Get_method() {
	  String url = get_baseurl();
	  Response res = RestAssured.given().when().get(url);
	  res.then().statusCode(200).body(containsString("API Running"));
	  int status = res.getStatusCode();
	  res.prettyPrint();
	  Assert.assertEquals(status, 200);
	  
	  
  }
}
