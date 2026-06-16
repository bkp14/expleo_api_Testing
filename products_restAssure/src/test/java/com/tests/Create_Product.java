package com.tests;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

class Product {

    private int id;
    private String name;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

public class Create_Product {

    @Test
    public void post_product() throws Exception {

        Product product = new Product();
        product.setId(10);
        product.setName("Laptop");
        product.setPrice(49999.99);

        ObjectMapper mapper = new ObjectMapper();

        String jsonPayload = mapper.writeValueAsString(product);

        Response response =
                RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(jsonPayload)
                .when()
                    .post("http://localhost:3000/products");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody().asString());
    }
}