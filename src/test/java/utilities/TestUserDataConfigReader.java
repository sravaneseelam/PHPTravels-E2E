package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestUserDataConfigReader {
	
	protected Properties testUserDataValues;
	
	public TestUserDataConfigReader() {
		
		testUserDataValues = new Properties();
		
		try {
			 FileInputStream inputProps = new FileInputStream("configs\\TestUserData.config");
			 testUserDataValues.load(inputProps);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public String getUserEmailID() {
		return testUserDataValues.getProperty("USER_EMAIL");
	}
	
	public String getPassword() {
		return testUserDataValues.getProperty("PASSWORD");
	}
	
	public String getIncorrectPassword() {
		return testUserDataValues.getProperty("INCORRECT_PASSWORD");
	}
	
}