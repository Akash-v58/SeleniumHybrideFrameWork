package TestScripts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericlibrary.BaseClass;

public class WishListAction extends BaseClass {
@Test(groups = "Regression")
	public void wishList()
	{
	driver.findElement(By.xpath("(//a[contains(text(),'Books')])[3]")).click();
	assertEquals(driver.getTitle(),"Demo Web Shop. Books","Books pages is not displayed");
	driver.findElement(By.xpath("//a[contains(text(),'Fiction EX')]")).click();
	driver.findElement(By.xpath("//input[@value='Add to wishlist']")).click();
	driver.findElement(By.xpath("//a[contains(text(),'Wishlist')]")).click();
	assertEquals(driver.getTitle(),"Demo Web Shop. Wishlist","wishlist not displayed");
	WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'Fiction EX')]"));
	assertEquals(ele.getText(),"Fiction EX","Fiction EX displayed in wishlist");
	driver.findElement(By.xpath("//td[@class=\"remove-from-cart\"]")).click();
	driver.findElement(By.xpath("//input[@name=\"updatecart\"]")).click();
	WebElement dis = driver.findElement(By.xpath("//div[@class=\"wishlist-content\"]"));
	assertEquals(dis.getText(),"The wishlist is empty!","wishlist is not empty");
	}
}
