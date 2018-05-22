package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {

	public static Properties property;
	public static FileInputStream fis;

	public static String getPropertyFromDoc(String whatProperty) {
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\selenium-tests.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property.getProperty(whatProperty);
	}

	public static String getBrowser() {
		return getPropertyFromDoc("browser");
	}
	
	public static String getUrl1() {
		return getPropertyFromDoc("url1");
	}

	public static String getLogin() {
		return getPropertyFromDoc("login");
	}

	public static String getPassword() {
		return getPropertyFromDoc("password");
	}

}