package com.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class createNote extends BaseUrl {

    @BeforeClass
    public void setupAuth() {
        Authentication auth = new Authentication();
        auth.authenticate_post();
        
        Assert.assertNotNull(Authentication.token, "Failed to initialize token. Authentication failed!");
    }

    @Test
    public void testCreateNote() {
        String url = get_baseurl();
        
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "API Test Note");
        payload.put("content", "Created by tester");
        
        List<String> tagsList = new ArrayList<>();
        tagsList.add("qa");
        tagsList.add("demo");
        payload.put("tags", tagsList);
        
        payload.put("color", "#ffeb3b");
        payload.put("isPinned", false);

        Response res = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + Authentication.token)
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .post(url + "create/notes"); 

        res.prettyPrint();

        res.then()
           .statusCode(201)
           .body("success", equalTo(true))
           .body("message", equalTo("Note created successfully"))
           .body("data.title", equalTo("API Test Note"))
           .body("data.isPinned", equalTo(false));

        String noteId = res.jsonPath().getString("data._id");
        System.out.println("Extracted Note ID: " + noteId);
    }
}