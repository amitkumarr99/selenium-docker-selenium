package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class Utility {

	
	private WebDriver driver;
	static Properties prop = new Properties();
	
	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public Utility() {
	}


	public static Properties loadPropertiesFile(String fileName) {
		String user_dir = System.getProperty("user.dir");
		Properties prop = new Properties();
		try {
			// File path when executing from batch file
			String propertyFilePath = System.getProperty("PropertyFile");
			if (propertyFilePath != null) {
				user_dir = user_dir.replace("bin", "");
			}
			prop.load(new FileInputStream(user_dir + "//src//test//resources//Properties//" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Load properties file.
	 * 
	 * @param fileName the file name
	 * @return the properties
	 */

}