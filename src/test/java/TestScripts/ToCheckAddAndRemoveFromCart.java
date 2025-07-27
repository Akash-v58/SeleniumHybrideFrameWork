package TestScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import genericlibrary.BaseClass;

public class ToCheckAddAndRemoveFromCart extends BaseClass {

	@Test(groups = "smoke")
	public void checkCart() throws InterruptedException, AWTException {
		driver.findElement(By.xpath("(//a[contains(text(),'Digital downloads')])[3]")).click();
		assertEquals(driver.getTitle(), "Demo Web Shop. Digital downloads", "Digital not opened");
		List<WebElement> cart = driver.findElements(By.xpath("//input[@value=\"Add to cart\"]"));
		for (int i = 0; i < cart.size(); i++) {
			cart.get(i).click();
			Thread.sleep(2000);
		}
		driver.findElement(By.xpath("//a[contains(text(),'Shopping cart')]")).click();
		assertEquals(driver.getTitle(), "Demo Web Shop. Shopping Cart", "Shopping cart not opened");
		List<WebElement> addedCart = driver.findElements(By.xpath("//td[@class=\"remove-from-cart\"]"));
		assertEquals(cart.size(), addedCart.size(), "All not added");
		for (WebElement webElement : addedCart) {
			webElement.click();
			Thread.sleep(2000);
		}
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.ENTER).perform();
		actions.keyUp(Keys.ENTER).perform() ;
		String text = driver.findElement(By.xpath("//div[@class=\"order-summary-content\"]")).getText();
		// assertFalse(text.contains("Your Shopping Cart is empty!"),"All are not
		// removed");

	}

}
