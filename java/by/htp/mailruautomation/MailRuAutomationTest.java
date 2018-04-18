package by.htp.mailruautomation;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.mailruautomation.steps.Steps;

public class MailRuAutomationTest {

	private Steps steps;
	private final String USERNAME = "y.rukan";
	private final String PASSWORD = "P@ssw0rd";
	private final String EMAIL = "y.rukan@mail.ru";

	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(description = "Login to Mail.ru")
	public void oneCanLoginMailRu() throws InterruptedException {
		steps.loginMailRu(USERNAME, PASSWORD);
		// Assert.assertEquals(steps.getLoginEmail(), EMAIL, "Account is not correct");
		Assert.assertTrue(steps.isLoggedIn(EMAIL), "Account is not correct");
	}

	@Test(description = "Send email from Mail.ru")
	public void oneCanSendEmailFromMailRu() throws InterruptedException {
		steps.loginMailRu(USERNAME, PASSWORD);
		steps.sendEmail();
		Assert.assertTrue(steps.isEmailSent(), "Email is not sent");
	}
	
	@Test(description = "Reply email from Mail.ru")
	public void oneCanReplyEmailFromMailRu() throws InterruptedException {
		steps.loginMailRu(USERNAME, PASSWORD);
		steps.replyEmail();
		Assert.assertTrue(steps.isEmailReplied(), "Email is not replied");
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		steps.closeDriver();
	}

}