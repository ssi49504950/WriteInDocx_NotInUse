package com.app.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
					"system:properties",
					"system:env",
					"file:${user.dir}/app.properties"
				})
public interface AppConfig extends Config{
	
	@DefaultValue("")
	@Key("dirPath")
	String getDirPath();
	
	@DefaultValue("doc")
	@Key("newDocxFileName")
	String getNewDocxFileName();
	
	@DefaultValue("400")
	@Key("imageHeight")
	int getImageHeight();
	
	@DefaultValue("400")
	@Key("imageWidth")
	int getImageWidth();

}

