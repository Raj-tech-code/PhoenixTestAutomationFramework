package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {

	private static Properties prop = new Properties();

	private ConfigManagerOLD() {
		// To avoid create object for this class
	}

	static {

		File configFile = new File(
				System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");


		FileReader configReader = null;

		try {
			configReader = new FileReader(configFile);
			prop.load(configReader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);

	}
}
