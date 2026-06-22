package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteNote extends Authentication {

    @BeforeClass
    public void setup() {
        createNote.testCreateNote();
    }


    @Test
    public void delete_validNoteId() {

        String url = get_baseurl();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", createNote.noteId)
                .when()
                .delete(url + "delete/notes/ById/{id}");

        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), 200);

        Assert.assertTrue(
                res.jsonPath().getBoolean("success")
        );

        Assert.assertEquals(
                res.jsonPath().getString("message"),
                "Notes deleted successfully"
        );

        Assert.assertEquals(
                res.jsonPath().getInt("deletedCount"),
                1
        );
    }


    @Test
    public void delete_invalidNoteIdFormat() {

        String url = get_baseurl();

        Response res = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", "abc123")
                .when()
                .delete(url + "delete/notes/ById/{id}");

        res.prettyPrint();

        Assert.assertEquals(res.getStatusCode(), 400);

        Assert.assertEquals(
                res.jsonPath().getString("message"),
                "Invalid note ID format"
        );
    }
    }