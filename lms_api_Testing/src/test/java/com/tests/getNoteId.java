package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getNoteId extends Authentication {
    @BeforeMethod
    public void setup() {
    	createNote.testCreateNote();
    }
    @Test
    public void validGetByid() {

        String url = BaseUrl.get_baseurl();
        String note_id = createNote.noteId;

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", note_id)
                .when()
                .get(url + "getById/notes/{id}");

        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(
                res.jsonPath().getString("data._id"),
                note_id);
        Assert.assertEquals(
                res.jsonPath().getString("data.title"),
                "API Test Note");

    }
    @Test
    public void invalid_noteId() {

        String url = BaseUrl.get_baseurl();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", "507f1f77bcf86cd799439011")
                .when()
                .get(url + "getById/notes/{id}");

        System.out.println("Status = " + res.getStatusCode());
        res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), 404);
        Assert.assertFalse(res.jsonPath().getBoolean("success"));
        Assert.assertEquals(
                res.jsonPath().getString("message"),
                "Note not found");
    }
}