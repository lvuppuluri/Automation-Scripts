package leaplogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BrowsersTestLogin {
	private WebDriver driver;

	public BrowsersTestLogin(WebDriver driver) {
		this.driver = driver;
	}

	public void login() {
		driver.findElement(By.linkText("Login")).click();
		for (int second = 0;; second++) {
			if (second >= 60)
				Assert.fail("timeout");
			try {
				if (isElementPresent(By.id("txtUsername")))
					break;
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys("Lakshmi.vuppuluri@levatas.com");
		for (int second = 0;; second++) {
			if (second >= 60)
				Assert.fail("timeout");
			try {
				if (isElementPresent(By.id("txtPassword")))
					break;
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("Password1!");
		driver.findElement(By.xpath("//form[@id='loginForm']/div[3]")).click();
		for (int second = 0;; second++) {
			if (second >= 60)
				Assert.fail("timeout");
			try {
				if ("Home - Leap Cloud Bundle".equals(driver.getTitle()))
					break;
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean isElementPresent(By id) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clearCache() {
		driver.manage().deleteAllCookies();

	}
}
