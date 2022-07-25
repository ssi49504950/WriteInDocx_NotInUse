package com.app.config;

import org.aeonbits.owner.ConfigFactory;

public final class ConfigReader {
	
	private ConfigReader() {}
	
	private static final AppConfig appConfig = ConfigFactory.create(AppConfig.class);
	
	public static String getDirPath() {
		String dirPath = appConfig.getDirPath();
		int lastIndex = dirPath.length() - 1;
		char ch = dirPath.charAt(lastIndex);
		
		if(ch == '/' || ch == '\\') {
			dirPath = dirPath.substring(0, lastIndex);
		}
		
		return dirPath.trim();
	}
	
	public static String getNewDocxFileName() {
		String name = appConfig.getNewDocxFileName();
		
		if(!name.contains(".docx")){
			name = name + ".docx";
		}
		
		return name.trim();
	} 
	
	public static int getImageHeight() {
		return appConfig.getImageHeight();
	}
	
	public static int getImageWidth() {
		return appConfig.getImageWidth();
	}

}
