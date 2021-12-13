package com.edbootcamp.seleniumtest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.chrome.ChromeDriver;

@Test
public class SeleniumTest {
  
	public WebDriver driver;
	
	
	@BeforeTest
	public void beforeTest() {
		driver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}
	
	@Test(priority=1)
	public void openWebApp() {
		driver.get("http://localhost:8080/GFreshii")
		
	}


}
