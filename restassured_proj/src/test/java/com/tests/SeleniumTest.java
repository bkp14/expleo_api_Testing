package com.tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver; 
public class SeleniumTest {
  @Test

  
  public void f()
  {
	WebDriver driver = new ChromeDriver();

	driver.get("https://jsonplaceholder.typicode.com/");
	driver.manage().window().maximize();
	driver.quit();
  }
}
