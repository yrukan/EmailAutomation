package by.htp.mailruautomation.steps;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import by.htp.mailruautomation.driver.DriverSingleton;
import by.htp.mailruautomation.pages.InboxPage;
import by.htp.mailruautomation.pages.LoginPage;

public class Steps {
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public void loginMailRu(String username, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);

	}

	public String getLoginEmail() {
		LoginPage loginPage = new LoginPage(driver);
		logger.info("Actual username: " + loginPage.getLoggedInUserName());
		return loginPage.getLoggedInUserName();
	}

	public boolean isLoggedIn(String username) {
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName();
		logger.info("Actual username: " + actualUsername);
		return actualUsername.equals(username);
	}

	public void sendEmail() throws InterruptedException {
		InboxPage inbox = new InboxPage(driver);
		inbox.openPage();
		inbox.sendEmail();
	}

	public boolean isEmailSent() {
		InboxPage inbox = new InboxPage(driver);
		boolean emailIsSent = false;
		if (inbox.getNotificationEmailIsSent() != null)
			emailIsSent = true;
		logger.info("Sent email notification is opened: " + emailIsSent);
		return emailIsSent;
	}
	
	public void replyEmail() throws InterruptedException {
		InboxPage inbox = new InboxPage(driver);
		inbox.openPage();
		inbox.replyEmail();
	}
	
	public boolean isEmailReplied() {
		InboxPage inbox = new InboxPage(driver);
		boolean emailIsReplied = false;
		if (inbox.getNotificationEmailIsSent() != null)
			emailIsReplied = true;
		logger.info("Replied email notification is opened: " + emailIsReplied);
		return emailIsReplied;
	}
	

}