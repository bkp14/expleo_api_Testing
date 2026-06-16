package com.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Delete_Product {

    @Test
    public void deleteProduct() {

        Response res = RestAssured.given()
        .when()
            .delete("http://localhost:3000/products/fn__5RMvaos");
        
            res.then().statusCode(200);
        System.out.println(res.getStatusCode());
        
    }
}