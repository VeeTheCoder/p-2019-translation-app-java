package com.translatetxml.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHandler {
	
	private String key;
		
	public ConfigHandler(String key) {
		this.key = key;
	}
	
	public String getConfigValue() {
		String value = null;
		
		 try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

	            Properties prop = new Properties();

	            if (input == null) {
	                System.out.println("config.properties not found");
	                return null;
	            }

	            prop.load(input);
	            value = prop.getProperty(key);


	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	            System.exit(1);
	        }		 
		return value;
	}

}
