package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TogglePin extends Authentication {

    @BeforeClass
    public void setup() {
        createNote.testCreateNote();
    }

    @Test
    public void togglePin_validNoteId() {

        String url = get_baseurl();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", createNote.noteId)
                .when()
                .put(url + "toggle-pin/notes/{id}");

        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertTrue(res.jsonPath().getBoolean("success"));

        Assert.assertEquals(res.jsonPath().getString("message"),
                "Note pinned successfully");
        Assert.assertTrue(
                res.jsonPath().getBoolean("data.isPinned")
        );

        Assert.assertEquals( res.jsonPath().getString("data._id"),createNote.noteId);
    }


    @Test
    public void togglePin_invalidNoteId() {

        String url = get_baseurl();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", "507f1f77bcf86cd79k*439011")
                .when()
                .put(url + "toggle-pin/notes/{id}");

        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), 404);

        Assert.assertEquals(res.jsonPath().getString("message"),"Note not found");
    }
}