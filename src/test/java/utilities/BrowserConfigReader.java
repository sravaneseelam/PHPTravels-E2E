package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowserConfigReader {

	protected Properties browserConfigValues;
	
	public BrowserConfigReader() {
		
		browserConfigValues = new Properties();
		
		try {
			 FileInputStream inputProps = new FileInputStream("configs\\BrowserProperties.config");
			 browserConfigValues.load(inputProps);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getBrowserType() {
		return browserConfigValues.getProperty("Browser");
	}
	
	public String getBaseURL() {
		return browserConfigValues.getProperty("BaseURL");
	}
	
}
