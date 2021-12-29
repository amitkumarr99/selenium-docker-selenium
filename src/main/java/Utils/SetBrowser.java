package Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SetBrowser {

	WebDriver driver;

	public WebDriver invokeLocalDriver(String browserName) {

		if (browserName.toUpperCase().trim().equalsIgnoreCase("SAFARI")) {
			driver = new SafariDriver();

		} else if (browserName.toUpperCase().trim().equalsIgnoreCase("FIREFOX")) {
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
			FirefoxOptions options = new FirefoxOptions();

			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options);

		} else if (browserName.toUpperCase().trim().equalsIgnoreCase("CHROME")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.toUpperCase().trim().equalsIgnoreCase("IE")) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.setProperty("webdriver.ie.driver",
			// ".\\BrowserDrivers\\IEDriverServer.exe");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		} else if (browserName.toUpperCase().trim().equalsIgnoreCase("HeadlessChrome")) {
			// System.setProperty("webdriver.chrome.driver", "browserDriver//chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("window-size=1400,600");
			options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		} else {

			Assert.fail("Driver does not exist");
			driver = null;
		}
		if (driver != null) {

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String bv = cap.getVersion().toString();

		}
		return driver;
	}

	/*
	 * Browser ==> chrome, HUB_HOST==> localhost/ 10.20.30.40, url= "http://" + host +
	 * ":4444/wd/hub"
	 */
	public WebDriver invokeRemoteDriver(String browserName, String host) {

		if (browserName.toUpperCase().trim().equalsIgnoreCase("CHROME")) {
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			URL url = null;
			try {
				url = new URL("http://" + host + ":4444/wd/hub");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			driver = new RemoteWebDriver(url, dc);
		}

		else if (browserName.toUpperCase().trim().equalsIgnoreCase("FIREFOX")) {
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			URL url = null;
			try {
				url = new URL("http://" + host + ":4444/wd/hub");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			driver = new RemoteWebDriver(url, dc);
		}

		return driver;

	}

	public WebDriver launchBrowser(String browserName, String host, String runType) {

		if (runType.equalsIgnoreCase("local")) {
			driver = invokeLocalDriver(browserName);

		}

		if (runType.equalsIgnoreCase("remote")) {
			driver = invokeRemoteDriver(browserName, host);

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		return driver;
	}
}