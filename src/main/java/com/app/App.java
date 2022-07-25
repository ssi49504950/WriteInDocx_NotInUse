package com.app;

import org.apache.log4j.PropertyConfigurator;

import com.app.config.ConfigReader;
import com.app.utils.DocxManager;

public class App {

	public static void main(String[] args) {
		// Below 2 lines are for removing default logs to be generated from Console
		String log4jConfPath = System.getProperty("user.dir") + "/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		        
		System.out.println("Application Started...");
				
		String dirPath= ConfigReader.getDirPath();
		String newDocxName= ConfigReader.getNewDocxFileName();
		int imageHeight= ConfigReader.getImageHeight();
		int imageWeight= ConfigReader.getImageWidth();
		
		DocxManager manager = new DocxManager();
				
		manager.manage(dirPath, newDocxName, imageHeight, imageWeight);
				
		System.out.println("Successfully Created " + newDocxName + " file");
		System.out.println("Application Shutting Down with Success...");

	}

}
