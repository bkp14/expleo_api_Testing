package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ApiValiation {
  @Test
  public void f() {
	  String email = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users/1").jsonPath().getString("email");
	  System.out.println("email:"+ email);
	  Assert.assertEquals(email, "Sincere@april.biz");
	  
	  String street = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users/1").jsonPath().getString("address.geo.lat");
	  System.out.println("street:"+ street);
	  Assert.assertEquals(street, "-37.3159");
  }
}
