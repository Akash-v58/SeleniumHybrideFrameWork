package elementrep;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement login;
	
	@FindBy(xpath = "//a[ @href='/register']")
	private WebElement reg;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getLogin() {
		return login;
	}

	public WebElement getReg() {
		return reg;
	}
	//d
	
}
