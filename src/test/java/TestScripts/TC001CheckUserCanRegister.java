package TestScripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementrep.HomePage;
import elementrep.RegPage;
import genericlibrary.BaseClass;
import genericlibrary.DataUtility;

@Listeners(genericlibrary.ListenerImplementation.class)
public class TC001CheckUserCanRegister extends BaseClass {
	

	@Test(dataProvider = "dataProvider")
	public void register(String firstName, String lastName, String email, String password, String confirmPassword)
			throws IOException {
		   HomePage hg=new HomePage(driver);
		driver.get(dataUtility.getDataFromProperties("url"));
		
		hg.getReg().click();
		RegPage rp = new RegPage(driver);
		rp.getGender_male().click();
		rp.getFirst_Name().sendKeys(firstName);
		rp.getLast_Name().sendKeys(lastName);
		rp.getEmail().sendKeys(email);
		rp.getPassword().sendKeys(password);
		rp.getConfirm_password().sendKeys(confirmPassword);
		rp.getReg().click();
		Assert.assertEquals(driver.getTitle(),"Demo Web Shop. Register");
	}

	@DataProvider
	public Object[][] dataProvider() throws EncryptedDocumentException, IOException {
		return dataUtility.getAllData("DemoWebShop");
	}
}
