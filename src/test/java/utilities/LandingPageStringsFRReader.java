package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LandingPageStringsFRReader {

	protected Properties landingPageStringsFR;
	
	public LandingPageStringsFRReader() {
		
		landingPageStringsFR = new Properties();
		
		try {
			 FileInputStream inputProps = new FileInputStream("configs\\LandingPageStrings.fr.config");
			 landingPageStringsFR.load(inputProps);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPropertyValue(String propName) {
		return landingPageStringsFR.getProperty(propName);
	}
}
