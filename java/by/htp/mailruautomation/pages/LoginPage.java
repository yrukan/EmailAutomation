package by.htp.mailruautomation.pages;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://mail.ru/";

	@FindBy(id = "mailbox:login")
	private WebElement inputLogin;

	@FindBy(id = "mailbox:password")
	private WebElement inputPassword;

	@FindBy(xpath = "//*[@id='mailbox:submit']/input")
	private WebElement buttonSubmit;

	@FindBy(id = "PH_user-email")
	private WebElement linkLoggedInUser;

	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
		logger.info("Login page opened");
	}

	public void login(String username, String password) throws InterruptedException
	{
		inputLogin.sendKeys(username);
		inputPassword.sendKeys(password);
		buttonSubmit.click();
		Thread.sleep(2000);
		logger.info("Login performed");
	}

	public String getLoggedInUserName()
	{
		return linkLoggedInUser.getText();
	}

}
