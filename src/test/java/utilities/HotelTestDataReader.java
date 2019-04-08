package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class HotelTestDataReader {
	
	protected Properties testData4HotelSearch;
	
	public HotelTestDataReader() {
		
		testData4HotelSearch = new Properties();
		
		try {
			 FileInputStream inputProps = new FileInputStream("configs\\HotelsTestData.config");
			 testData4HotelSearch.load(inputProps);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPropertyValue(String propName) {
		return testData4HotelSearch.getProperty(propName);
	}
	
}