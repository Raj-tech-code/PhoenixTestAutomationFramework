package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mozilla.javascript.tools.shell.Environment;

public class ConfigManager {

	private static Properties prop = new Properties();

	private static String env;
	private static String path;

	private ConfigManager() {
		// created private constructor to avoid create object for this class
	}

	static {

		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();

		switch (env) {
		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";

		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {

			throw new RuntimeException("File is not found at the path " + path);
		}

		try {
			prop.load(input);

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
