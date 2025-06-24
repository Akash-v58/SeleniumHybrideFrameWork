package elementrep;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegPage {
	@FindBy(id = "gender-male")
	private WebElement gender_male;

	@FindBy(id = "gender-female")
	private WebElement gender_female;

	@FindBy(id="FirstName")
	private WebElement first_Name;

	@FindBy(id="LastName")
	private WebElement last_Name;

	@FindBy(id="Email")
	private WebElement email;

	@FindBy(id = "Password")
	private WebElement password;

	@FindBy(id = "ConfirmPassword")
	private WebElement confirm_password;

	@FindBy(id = "register-button")
	private WebElement reg;

	public RegPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getGender_male() {
		return gender_male;
	}

	public WebElement getGender_female() {
		return gender_female;
	}

	public WebElement getFirst_Name() {
		return first_Name;
	}

	public WebElement getLast_Name() {
		return last_Name;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getConfirm_password() {
		return confirm_password;
	}

	public WebElement getReg() {
		return reg;
	}

}
