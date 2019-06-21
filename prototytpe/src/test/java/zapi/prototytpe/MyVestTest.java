package zapi.prototytpe;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zapi.Utils.ResultParser;

public class MyVestTest {
	public static WebDriver driver;
	public static String cycleID = "";

	@BeforeMethod
	public void setup() throws URISyntaxException, IOException {
		System.setProperty("webdriver.chrome.driver", "/Users/sps/Downloads/chromedriver");
		driver = new ChromeDriver();
		ConnectionManager.getcycleID();
		System.out.println("CYCLEID" + cycleID);
		System.out.println("getCycle" +ConnectionManager.getcycleID());
		ConnectionManager.createTestCycle("Advisor_PA_ProposalCreation");
		ConnectionManager.createTestFolder();
		ConnectionManager.getFolderID();
		ConnectionManager.getTestID("AMMO-76027");
		ConnectionManager.addTestCaseToFolder();
		driver.get("https://tiaa.uatcan.myvest.com/u/auth/login.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test
	public void myVestLoginTest() {
		driver.findElement(By.xpath("//*[@id=\"j_login\"]")).sendKeys("dburban");
		driver.findElement(By.id("j_password")).sendKeys("mj");
		driver.findElement(By.id("login_submit")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver , 20);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"user-profile-dropdown\"]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("CurrentURL" + driver.getCurrentUrl());
		Assert.assertEquals("https://tiaa.uatcan.myvest.com/u/", driver.getCurrentUrl());

	}
	

	@AfterTest
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
		ResultParser.parse();
		ConnectionManager.executeTestStatus(ResultParser.parse());

	}

}
