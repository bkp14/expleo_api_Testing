package com.tests;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Authentication extends BaseUrl {

    static String token;

    @BeforeClass
    public void authenticate_post() {

        String url = get_baseurl();

        Map<String,Object> payload = new HashMap<>();
        payload.put("Role", "Admin");
        payload.put("email","sam@gmail.com");
        payload.put("password","123");

        Response res =
            RestAssured.given()
            .contentType(ContentType.JSON)
            .body(payload)
            .when()
            .post(url + "user/login");

        res.then().statusCode(201);

        token = res.jsonPath().getString("token");

        System.out.println("TOKEN = " + token);
    }
}