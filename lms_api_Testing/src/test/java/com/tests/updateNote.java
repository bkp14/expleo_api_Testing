package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class updateNote extends Authentication{
    @BeforeClass
    public void setup() {
    	createNote.testCreateNote();
    }
  @Test
  public void update_title_content() {

      String url = get_baseurl();

      Map<String,Object> payload = new HashMap<>();
      payload.put("title", "API Test Note (edited)");
      payload.put("content", "Updated content");

      Response res = RestAssured.given()
              .header("Authorization", "Bearer " + token)
              .contentType(ContentType.JSON)
              .pathParam("id", createNote.noteId)
              .body(payload)
              .when()
              .put(url + "update/notes/{id}");

      res.prettyPrint();

      Assert.assertEquals(res.getStatusCode(), 200);
      Assert.assertTrue(res.jsonPath().getBoolean("success"));
      Assert.assertEquals(
              res.jsonPath().getString("message"),
              "Note updated successfully");

      Assert.assertEquals(
              res.jsonPath().getString("data.title"),
              "API Test Note (edited)");

      Assert.assertEquals(
              res.jsonPath().getString("data.content"),
              "Updated content");
  }
  @Test
  public void update_invalid_noteId() {

      String url = get_baseurl();

      Map<String, Object> payload = new HashMap<>();
      payload.put("title", "Updated Title");

      Response res = RestAssured.given()
              .header("Authorization", "Bearer " + token)
              .contentType(ContentType.JSON)
              .pathParam("id", "507f1f77bcf86cd799439011")
              .body(payload)
              .when()
              .put(url + "update/notes/{id}");

      Assert.assertEquals(res.getStatusCode(), 404);

      Assert.assertEquals(
              res.jsonPath().getString("message"),
              "Note not found");
      res.prettyPrint();
  }
}
