package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
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
	       Assert.assertEquals(
	               res.jsonPath().getString("message.value"),"[Admin logged in successfully]" );
	       Assert.assertEquals(res.jsonPath().getString("user.status"),"active");
  }
  
  @Test
  public void Post_Login_InvalidCredentials() {

      String url = get_baseurl();

      Map<String, Object> payload = new HashMap<>();
      payload.put("email", "wrong@gmail.com");
      payload.put("password", "wrong123");

      Response res = RestAssured.given()
              .contentType(ContentType.JSON)
              .body(payload)
              .post(url + "user/login");

      res.prettyPrint();

      res.then().statusCode(400); 
      Assert.assertEquals(
  		    res.jsonPath().getString("message[0].value"),"Email is invalid");
  }
  
  @Test
  public void Post_Login_BlankEmail() {

      String url = get_baseurl();

      Map<String, Object> payload = new HashMap<>();
      payload.put("email", "asa@gamail.com");
      payload.put("password", "123");

      Response res = RestAssured.given()
              .contentType(ContentType.JSON)
              .body(payload)
              .post(url + "user/login");

      res.prettyPrint();

      res.then().statusCode(400);
      Assert.assertEquals(
  		    res.jsonPath().getString("message[0].value"),"Email is invalid");
  }
  
  @Test
  public void Post_Login_BlankPassword() {

      String url = get_baseurl();

      Map<String, Object> payload = new HashMap<>();
      payload.put("email", "sam@gmail.com");
      payload.put("password", "121");

      Response res = RestAssured.given()
              .contentType(ContentType.JSON)
              .body(payload)
              .post(url + "user/login");

      res.prettyPrint();

      res.then().statusCode(400);
      Assert.assertEquals(
  		    res.jsonPath().getString("message[0].value"),"Password is incorrect");
  }
  @Test
  public void Post_Login_BlankFields() {

      String url = get_baseurl();

      Map<String, Object> payload = new HashMap<>();
      payload.put("email", "");
      payload.put("password", "");

      Response res = RestAssured.given()
              .contentType(ContentType.JSON)
              .body(payload)
              .post(url + "user/login");

      res.prettyPrint();

      res.then().statusCode(400);
      Assert.assertEquals(
    		    res.jsonPath().getString("message[0].value"),"All fields are required");
  }
}
