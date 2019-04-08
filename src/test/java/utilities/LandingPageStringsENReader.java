package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LandingPageStringsENReader {
	
	protected Properties landingPageStringsEN;
	
	public LandingPageStringsENReader() {
		
		landingPageStringsEN = new Properties();
		
		try {
			 FileInputStream inputProps = new FileInputStream("configs\\LandingPageStrings.en.config");
			 landingPageStringsEN.load(inputProps);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPropertyValue(String propName) {
		return landingPageStringsEN.getProperty(propName);
	}
}
