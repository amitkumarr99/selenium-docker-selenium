package smoke.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.ConfigurationClass;
import Utils.SetBrowser;
import Utils.Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.lang.reflect.Method;
import java.util.Properties;

public class SearchvalueTest {
	
	
	public WebDriver driver;
	private Properties prop;
	private String sTCName;
	String browserName;
	String runType;
	String host;


	
	@BeforeMethod
	public void setUp(Method method) {
		browserName = ConfigurationClass.browser;
		runType = ConfigurationClass.runType;
		host =ConfigurationClass.hub_host;
		prop = Utility.loadPropertiesFile("config.properties");
		driver = new SetBrowser().launchBrowser(browserName, host, runType);
		}
	
	@Test
	public void SearchValueOne() {
		
		String url=prop.getProperty("url");
	
		driver.get(url);
		System.out.println("======== Browser opened ===========");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(prop.getProperty("val5"));
		System.out.println("======== Element found ===========");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		System.out.println("======== Search complete ===========");
		
	}
	
	@Test
	public void SearchValueTwo() {
		
		String url=prop.getProperty("url");
	
		driver.get(url);
		System.out.println("======== Browser opened ===========");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(prop.getProperty("val6"));
		System.out.println("======== Element found ===========");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		System.out.println("======== Search complete ===========");
	}
	
	@Test
	public void hitGetMethod() {
		
		RestAssured.baseURI="https://reqres.in";
		String Resource="/api/users?page=4";
		Response resp = given().log().all().when().get(Resource).
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
		System.out.println(resp.asString());
		
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
