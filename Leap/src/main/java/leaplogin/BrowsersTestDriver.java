package leaplogin;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

public class BrowsersTestDriver {
	private WebDriver driver;
	private String url;
	private String browser;
	public int defaultHeightSize;
	public int defaultWidthSize;
	long threadId;

	public BrowsersTestDriver() {
		// TODO Auto-generated constructor stub
	}

	public BrowsersTestDriver(String browserName, String version, String platform, String url)
			throws MalformedURLException {
		System.out.println("browserName = " + browserName);
		System.out.println("version = " + version);
		System.out.println("platform = " + platform);
		System.out.println("url = " + url);
		threadId = Thread.currentThread().getId();

		DesiredCapabilities cap = null;
		this.browser = browserName;
		if (browserName.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
		} else if (browserName.equalsIgnoreCase("safari")) {
			cap = DesiredCapabilities.safari();
		} else {
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		}
		if ((version != null) & (!version.equals(""))) {
			cap.setVersion(version);
		}
		if (platform.equalsIgnoreCase("mac"))
			cap.setPlatform(org.openqa.selenium.Platform.MAC);
		else
			cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);

		System.out.println(cap.getPlatform());
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		driver.get(url);
		this.url = url;

		System.out.println("Driver = " + driver.toString());
//		defaultWidthSize = driver.manage().window().getSize().getWidth();
//		defaultHeightSize = driver.manage().window().getSize().getHeight();
	}

	public void loadPage() {
		this.driver.get(url);
	}

	public boolean isChrome() {
		return (this.browser.equalsIgnoreCase("chrome") ? true : false);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public long getThreadId() {
		return this.threadId;
	}

	public void login() throws InterruptedException {
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

		driver.findElement(By.cssSelector("div.tools.item > div.text")).click();
		// driver.findElement(By.cssSelector("div.content-panel-padding.cf")).click();
		driver.findElement(By.xpath("//div[@id='_tools']/div/div/div[2]/div/div[1]")).click();
		driver.findElement(By.cssSelector("a.button > span")).click();

	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clearCache() {
		driver.manage().deleteAllCookies();

	}
}
