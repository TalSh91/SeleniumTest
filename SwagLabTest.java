package TalProject.Mavenjava;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwagLabTest {
	//@Test
	public void addItemToCart() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		List<WebElement> items = driver
				.findElements(By.cssSelector("button[class='btn btn_primary btn_small btn_inventory']"));
		items.stream().forEach(s -> s.click());
		String cartSize = driver.findElement(By.cssSelector("span[class='shopping_cart_badge']")).getText();
		int cartSizeInt = Integer.parseInt(cartSize);
		Assert.assertEquals(cartSizeInt, items.size());

	}
	@Test
	public void VerifyPrice() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		List<WebElement> items = driver
				.findElements(By.cssSelector("button[class='btn btn_primary btn_small btn_inventory']"));
		items.stream().forEach(s -> s.click());
		List <WebElement> itemPrice = driver.findElements(By.cssSelector("div[class=inventory_item_price]"));
		float priceSum = 0;
		
		for(int i = 0;i<itemPrice.size();i++) {
			String pricenum = itemPrice.get(i).getText().replace("$", "");
			float priceNumber = Float.parseFloat(pricenum);
			priceSum = priceSum + priceNumber;
			
			
		}
		driver.findElement(By.cssSelector("a[class='shopping_cart_link']")).click();
		List <WebElement> cartPrice = driver.findElements(By.cssSelector("div[class$='inventory_item_price']"));
		float priceSumCart = 0;
		for(int i = 0;i<cartPrice.size();i++) {
			String pricenum = cartPrice.get(i).getText().replace("$", "");
			float priceNumber = Float.parseFloat(pricenum);
			priceSumCart = priceSumCart + priceNumber;
			
			
		}
		Assert.assertEquals(priceSum, priceSumCart);
		
	}

}
