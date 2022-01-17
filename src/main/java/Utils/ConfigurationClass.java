package Utils;


import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class ConfigurationClass {
	
	public static String BROWSER;
	public static String HUB_HOST;
	public static String RUNTYPE;
	public static String environment;
	public static String instanceUrl;
	public static String client;
	public static String secret;
	public static String locationKey;
	public static String market;

	//private static Properties frameworkProp;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "environment", "instanceUrl", "BROWSER", "HUB_HOST", "client", "secret", "locationKey",
			"RUNTYPE", "market" })
	public void initFramework(@Optional("") final String environment, @Optional("") final String instanceUrl,
			@Optional("") final String BROWSER, @Optional("") final String HUB_HOST, @Optional("") final String client,
			@Optional("") final String secret, @Optional("") final String locationKey,
			@Optional("") final String RUNTYPE, @Optional("") final String market) {
		
		//loadFrameworkPropertiesFile();
		
		setFinalFrameworkProperties(environment, instanceUrl, BROWSER, HUB_HOST, client, secret, locationKey,
				RUNTYPE, market);
		printFrameworkConfigurations();
	}

	private void setFinalFrameworkProperties(final String environment, final String instanceUrl, final String BROWSER,
			final String HUB_HOST, final String client, final String secret, final String locationKey,
			final String RUNTYPE, final String market) {
		ConfigurationClass.environment = getValue("environment", environment);
		ConfigurationClass.instanceUrl = getValue("instanceUrl", instanceUrl);
		ConfigurationClass.BROWSER = getValue("BROWSER", BROWSER);
		ConfigurationClass.HUB_HOST = getValue("HUB_HOST", HUB_HOST);
		ConfigurationClass.client = getValue("client", client);
		ConfigurationClass.secret = getValue("secret", secret);
		ConfigurationClass.locationKey = getValue("locationKey", locationKey);
		ConfigurationClass.RUNTYPE = getValue("RUNTYPE", RUNTYPE);
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
		Reporter.log("BROWSER: " + ConfigurationClass.BROWSER, true);
		Reporter.log("HUB_HOST: " + ConfigurationClass.HUB_HOST, true);
		Reporter.log("RUNTYPE: " + ConfigurationClass.RUNTYPE, true);
		Reporter.log("instanceUrl: " + ConfigurationClass.instanceUrl, true);
		Reporter.log("Environment: " + ConfigurationClass.environment, true);
		Reporter.log("client: " + ConfigurationClass.client, true);
		Reporter.log("secret: " + ConfigurationClass.secret, true);
		Reporter.log("locationKey: " + ConfigurationClass.locationKey, true);
		Reporter.log("market: " + ConfigurationClass.market, true);
		Reporter.log("-----------------------------------------------------", true);
	}
	
}