package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getAllNotes extends Authentication {
	static String id;
  @Test
  public void valid_withoutqparam() {
	  String url = BaseUrl.get_baseurl();
	  Response res = RestAssured.given().header("Authorization","Bearer "+Authentication.token)
			  .when().get(url+"getAll/notes");
	  res.then().statusCode(200);
	  System.out.println("Status Code: " + res.getStatusCode());
	  	
	  res.prettyPrint();
  }
  @Test
  public void valid_withqparam1() {
	  Map<String, Object> queryParams = new HashMap<>();
	  queryParams.put("page", 1);
	  queryParams.put("limit", 50);
	  queryParams.put("search", "Test");
	  queryParams.put("tags", "[qa]");
	  queryParams.put("isPinned", true);
	  queryParams.put("sortBy", "lastEdited");
	  queryParams.put("sortOrder", "asc");
	  String url = BaseUrl.get_baseurl();
	  Response res = RestAssured.given().header("Authorization","Bearer "+Authentication.token)
			  .queryParams(queryParams)
			  .when().get(url+"getAll/notes");
	  res.then().statusCode(200);
	  res.prettyPrint();
  }
  @Test
  public void valid_withqparam2() {
	  Map<String, Object> queryParams = new HashMap<>();
	  queryParams.put("page", 1);
	  queryParams.put("limit", 50);
	  queryParams.put("search", "Test");
	  queryParams.put("tags", "[qa,demo]");
	  queryParams.put("isPinned", false);
	  queryParams.put("sortBy", "title");
	  queryParams.put("sortOrder", "desc");
	  String url = BaseUrl.get_baseurl();
	  Response res = RestAssured.given().header("Authorization","Bearer "+Authentication.token)
			  .queryParams(queryParams)
			  .when().get(url+"getAll/notes");
	  res.then().statusCode(200);
	  res.prettyPrint();
  }
  @Test
  public void invalid_pageNumber() {

      String url = BaseUrl.get_baseurl();

      Map<String,Object> queryParams = new HashMap<>();

      queryParams.put("page", -1);
      queryParams.put("limit", 50);


      Response res = RestAssured.given()
              .header("Authorization", "Bearer " + Authentication.token)
              .queryParams(queryParams)
              .when()
              .get(url + "getAll/notes");
      res.prettyPrint();
      Assert.assertEquals(res.getStatusCode(), 400);
  }
  @Test
  public void invalid_limitValue() {

      String url = BaseUrl.get_baseurl();

      Map<String,Object> queryParams = new HashMap<>();

      queryParams.put("page", 1);
      queryParams.put("limit", "abc");


      Response res = RestAssured.given()
              .header("Authorization", "Bearer " + Authentication.token)
              .queryParams(queryParams)
              .when()
              .get(url + "getAll/notes");
      res.prettyPrint();
      Assert.assertEquals(res.getStatusCode(), 400);
  }
}
