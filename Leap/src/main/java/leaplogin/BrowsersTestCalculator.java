package leaplogin;

import org.openqa.selenium.*;

public class BrowsersTestCalculator {
	private WebDriver driver;

	public BrowsersTestCalculator(WebDriver driver) {
		this.driver = driver;
	}
	
	public void calculate(String clientAge, String spouseAge, String deathBenefit, String termYears, String premium)
			throws Exception {
		
		/*driver.findElement(By.cssSelector("div.tools.item > div.text")).click();
		driver.findElement(By.cssSelector("div.content-panel-padding.cf")).click();
		driver.findElement(By.xpath("//div[@id='_tools']/div/div/div[2]/div/div")).click();
		driver.findElement(By.cssSelector("a.button > span")).click();*/
		driver.findElement(By.id("clientBegLifeHorizon")).sendKeys(clientAge);
		driver.findElement(By.id("spouseBegLifeHorizon")).sendKeys(spouseAge);
		driver.findElement(By.id("dBenefit")).sendKeys(deathBenefit);
		driver.findElement(By.id("policyLength")).sendKeys(termYears);
		driver.findElement(By.id("aPremium")).sendKeys(premium);
		// testing navigation
		//driver.findElement(By.linkText("Next")).click();
		System.out.println();
	}
}
