package com.edbootcamp.seleniumtest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class SeleniumTest {

	public WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\jba\\chromedriver96.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://google.com");
		
	}

	@AfterTest
	public void afterTest() {
		//driver.close();
	}

	@Test(priority = 1)
	public void openWebApp() {
		driver.get("http://localhost:8080/GFreshii/");
	}
	
	@Test(priority = 2)
	public void registerNewUser() throws InterruptedException {
		driver.findElement(By.cssSelector("#inputName")).sendKeys("Amanda");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputLastName")).sendKeys("Hugankiss");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputUserName")).sendKeys("kissandhug");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputPassword")).sendKeys("kisses");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputEmail")).sendKeys("testing1@tester.com");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#register")).click();
		Thread.sleep(2500);
	}
	
	@Test(priority = 3)
	public void goHomeAfterRegistering() throws InterruptedException{;
		Thread.sleep(250);
		driver.findElement(By.linkText("Home")).click();
	}
	
	@Test(priority = 4)
	public void login() throws InterruptedException{
		driver.get("http://localhost:8080/GFreshii/login");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputUserName")).sendKeys("kissandhug");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputPassword")).sendKeys("kisses");
		Thread.sleep(250);
	    driver.findElement(By.cssSelector("#signin")).click();
	}
	@Test(priority = 5)
	public void newRecipe() throws InterruptedException{
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#rname")).sendKeys("Pizza");
		Thread.sleep(250);
	    driver.findElement(By.cssSelector("#addName")).click();
	    Thread.sleep(250);
	}
	
	@Test(priority = 6)
	public void newIngredient() throws InterruptedException{
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#amount")).sendKeys("1");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#unit")).sendKeys("cup");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#name")).sendKeys("mozzarella cheese");
		Thread.sleep(250);
	    driver.findElement(By.cssSelector("#addIngredient")).click();
	    Thread.sleep(250);
	    driver.findElement(By.cssSelector("#amount")).sendKeys("1");
	    Thread.sleep(250);
		driver.findElement(By.cssSelector("#unit")).sendKeys("cup");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#name")).sendKeys("tomato sauce");
		Thread.sleep(250);
	    driver.findElement(By.cssSelector("#addIngredient")).click();
	    Thread.sleep(250);
	    driver.findElement(By.cssSelector("#amount")).sendKeys("1");
	    Thread.sleep(250);
		driver.findElement(By.cssSelector("#unit")).sendKeys("cup");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#name")).sendKeys("pepperoni");
		Thread.sleep(250);
	    driver.findElement(By.cssSelector("#addIngredient")).click();
	    Thread.sleep(500);
	}
	
	@Test(priority = 7)
	public void newIntruction() throws InterruptedException{
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#instruction")).sendKeys(" Start by setting the oven to 400 degrees");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#addInstruction")).click();
	    Thread.sleep(500);
	}
	
	@Test(priority = 8)
	public void deleteIngredient() throws InterruptedException{;
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#ingredientDelete")).click();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#ingredientDelete")).click();
	}
	
	@Test(priority = 9)
	public void editIngredient() throws InterruptedException {
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#ingredientEdit")).click();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#amount")).clear();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#amount")).sendKeys("2");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#unit")).clear();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#unit")).sendKeys("cups");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#name")).clear();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#name")).sendKeys("Pepperoni");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#addIngredient")).click();
	}
	
	@Test(priority = 10)
	public void editRecipeName() throws InterruptedException{;
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#editRecipename")).click();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#rname")).clear();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#rname")).sendKeys("Pepperoni Pizza");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#addName")).click();
	}
	
	@Test(priority = 11)
	public void deleteLastIngredient() throws InterruptedException{;
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#ingredientDelete")).click();
	}
	
	@Test(priority = 12)
	public void deleteRecipe() throws InterruptedException{;
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#deleteRecipe")).click();
	}
	
	@Test(priority = 13)
	public void logout() throws InterruptedException{;
		Thread.sleep(250);
		driver.findElement(By.linkText("Logout")).click();
	}
	
	@Test(priority = 14)
	public void goHome() throws InterruptedException{;
		Thread.sleep(250);
		driver.findElement(By.linkText("Home")).click();
	}
	
	@Test(priority = 15)
	public void logBackIn() throws InterruptedException{;
		driver.get("http://localhost:8080/GFreshii/login");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputUserName")).sendKeys("kissandhug");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputPassword")).sendKeys("kisses");
		Thread.sleep(250);
	    driver.findElement(By.cssSelector("#signin")).click();
	}
	
	@Test(priority = 16)
	public void accountSettings() throws InterruptedException{;
		driver.findElement(By.linkText("Account")).click();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#editUser")).click();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#password")).clear();
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#password")).sendKeys("VerySecurePasword");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#userEdit")).click();
		Thread.sleep(250);
		driver.findElement(By.linkText("Home")).click();
		Thread.sleep(250);
	}
	
	@Test(priority = 17)
	public void loginNewCredentials() throws InterruptedException{;
		driver.get("http://localhost:8080/GFreshii/login");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputUserName")).sendKeys("kissandhug");
		Thread.sleep(250);
		driver.findElement(By.cssSelector("#inputPassword")).sendKeys("VerySecurePasword");
		Thread.sleep(250);
	    driver.findElement(By.cssSelector("#signin")).click();
	    Thread.sleep(250);
	    driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(250);
	}
	
}
