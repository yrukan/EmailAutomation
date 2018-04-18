package by.htp.mailruautomation.pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends AbstractPage {

	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://e.mail.ru/messages/inbox/?back=1";
	private final String SEND_EMAIL_TO = "y.rukan@mail.ru";
	private final String EMAIL_SUBJECT = "Test Mail from Yuliya";
	private final String EMAIL_TEXT = "Test text from Yuliya";
	private final String AUTOREPLY_EMAIL_TEXT = "Autoreply text from Yuliya";

	@FindBy(xpath = "//*[@id='b-toolbar__left']/div/div/div[2]/div/a")
	private WebElement buttonWriteEmail;

	@FindBy(xpath = "//textarea[@data-original-name='To']")
	private WebElement inputSendEmailTo;

	@FindBy(xpath = "//input[@name='Subject']")
	private WebElement inputEmailSubject;

	@FindBy(xpath = "//iframe[@title='{#aria.rich_text_area}']")
	private WebElement frameEmailText;	

	@FindBy(id = "tinymce")
	private WebElement inputEmailText;

	@FindBy(xpath = "//*[@id=\'b-toolbar__right\']/div[3]/div/div[2]/div[1]/div")	                
	private WebElement buttonSendEmail;

	@FindBy(xpath = "//*[@id=\'b-compose__sent\']/div")	
	private WebElement notificationEmailIsSent;
	
	@FindBy(xpath = "//*[@id=\'b-letters\']/div[1]/div[2]/div")
	private WebElement blockInboxEmails;
	
	@FindBy(xpath = "//div[@class='b-datalist__item js-datalist-item b-datalist__item_unread']")
	private WebElement unreadInboxEmail;
	
	@FindBy(xpath = "//*[@id=\'b-toolbar__right\']/div[3]/div[1]/div/div[2]/div[2]/div/div[1]")
	private WebElement buttonReply;	
	
	@FindBy(xpath = "//*[@id=\'b-toolbar__right\']/div[4]/div/div[2]/div[1]/div/span")
	private WebElement buttonSendReply;

	public InboxPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		//driver.navigate().to(BASE_URL);
		logger.info("Inbox page opened");
	}

	public void sendEmail() throws InterruptedException {
		buttonWriteEmail.click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		inputSendEmailTo.sendKeys(SEND_EMAIL_TO);
		inputEmailSubject.sendKeys(EMAIL_SUBJECT);
		driver.switchTo().frame(frameEmailText);
		inputEmailText.sendKeys(EMAIL_TEXT);	
		driver.switchTo().defaultContent();		
		buttonSendEmail.click();		
		logger.info("Email is sent");
	}

	public String getNotificationEmailIsSent() {
		return notificationEmailIsSent.getAttribute("class");
	}
	
	public void replyEmail() throws InterruptedException {
		unreadInboxEmail.click();
		Thread.sleep(1000);
		buttonReply.click();
		Thread.sleep(1000);		
		driver.switchTo().frame(frameEmailText);
		inputEmailText.sendKeys(AUTOREPLY_EMAIL_TEXT);
		Thread.sleep(1000);		
		driver.switchTo().defaultContent();
		buttonSendReply.click();
		logger.info("Email is replied");		
	}

}
