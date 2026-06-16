package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class patch_update {

    @Test
    public void patch_method() {

        String payload = "{\n" +
                         "\"name\": \"Laptop_patch\"\n" +
                         "}";

        Response res = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch("http://localhost:3000/products/2");

        System.out.println(res.getStatusCode());
        System.out.println(res.asString());

        res.then().statusCode(200);
    }
}