package leaplogin;

import org.testng.annotations.Test;

import data.calcdata;

import org.testng.AssertJUnit;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BrowsersTestMain {

	private WebDriver driver;
	private BrowsersTestCalculator btc;
	private BrowsersTestDriver btd;
	long threadID ;

	@Parameters({ "browserName", "version", "platform", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String version, String platform, String url)
			throws MalformedURLException, InterruptedException {
		btd = new BrowsersTestDriver(browserName, version, platform, url);
		driver = btd.getDriver();
		this.threadID = Thread.currentThread().getId();

		for (int second = 0;; second++) {
			if (second >= 60)
				AssertJUnit.fail("timeout");
			try {
				System.out.println(driver + ", " + btd.getDriver().getTitle());
				System.out.println("Thread=" + this.threadID);
				if ("Home - Leap Systems Application".equals(btd.getDriver().getTitle()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		btd.clearCache();
		if (btd.isChrome()) {
			btd.loadPage();
		}
		btd.login();
		btc = new BrowsersTestCalculator(btd.getDriver());

	}

	@Test(dataProvider = "calcdata", dataProviderClass = calcdata.class)
	public void testLeaplogin(String clientAge, String spouseAge, String deathBenefit, String termYears, String premium)
			throws Exception {
		System.out.println("Begin calculations...(thread)" + this.threadID);
		System.out.println("clientAge = " + clientAge + ", spouseAge=" + spouseAge + ", deathBenefit=" + deathBenefit
				+ ", termYears=" + termYears + ", premium=" + premium);
		btc.calculate(clientAge, spouseAge, deathBenefit, termYears, premium);
		System.out.println("End calculations...(thread)" + this.threadID);
	}

	@AfterTest
	public void afterTest() {
		// Close the browser
		// driver.close();
		driver.quit();
		btc = null;
		btd = null;
		driver = null;
	}

}
