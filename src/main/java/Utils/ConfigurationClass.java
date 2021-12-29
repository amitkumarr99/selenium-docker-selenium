package Utils;


import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class ConfigurationClass {
	
	public static String browser;
	public static String hub_host;
	public static String environment;
	public static String instanceUrl;
	public static String client;
	public static String secret;
	public static String locationKey;
	public static String runType;
	public static String market;

	//private static Properties frameworkProp;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "environment", "instanceUrl", "browser", "hub_host", "client", "secret", "locationKey",
			"runType", "market" })
	public void initFramework(@Optional("") final String environment, @Optional("") final String instanceUrl,
			@Optional("") final String browser, @Optional("") final String hub_host, @Optional("") final String client,
			@Optional("") final String secret, @Optional("") final String locationKey,
			@Optional("") final String runType, @Optional("") final String market) {
		
		//loadFrameworkPropertiesFile();
		
		setFinalFrameworkProperties(environment, instanceUrl, browser, hub_host, client, secret, locationKey,
				runType, market);
		printFrameworkConfigurations();
	}

	private void setFinalFrameworkProperties(final String environment, final String instanceUrl, final String browser,
			final String hub_host, final String client, final String secret, final String locationKey,
			final String runType, final String market) {
		ConfigurationClass.environment = getValue("environment", environment);
		ConfigurationClass.instanceUrl = getValue("instanceUrl", instanceUrl);
		ConfigurationClass.browser = getValue("browser", browser);
		ConfigurationClass.hub_host = getValue("hub_host", hub_host);
		ConfigurationClass.client = getValue("client", client);
		ConfigurationClass.secret = getValue("secret", secret);
		ConfigurationClass.locationKey = getValue("locationKey", locationKey);
		ConfigurationClass.runType = getValue("runType", runType);
		ConfigurationClass.market = getValue("market", market);
	}

	private static String getValue(final String key, final String value) {
// Maven command line
		if (System.getProperty(key) != null) {
			return System.getProperty(key);
		}

// TestNG
		if (!value.isEmpty()) {
			return value;
		}

/* Value from framework.properties file
		if (frameworkProp.get(key) != null) {
			return (String) frameworkProp.get(key);
		} */

		return null;
	}

	private void printFrameworkConfigurations() {
		Reporter.log("------------------Automation Test started-------------------", true);
		Reporter.log("instanceUrl: " + ConfigurationClass.instanceUrl, true);
		Reporter.log("Environment: " + ConfigurationClass.environment, true);
		Reporter.log("Browser: " + ConfigurationClass.browser, true);
		Reporter.log("hub_host: " + ConfigurationClass.hub_host, true);
		Reporter.log("client: " + ConfigurationClass.client, true);
		Reporter.log("secret: " + ConfigurationClass.secret, true);
		Reporter.log("locationKey: " + ConfigurationClass.locationKey, true);
		Reporter.log("runType: " + ConfigurationClass.runType, true);
		Reporter.log("market: " + ConfigurationClass.market, true);
		Reporter.log("-----------------------------------------------------", true);
	}
	
}