package com.fastcampus.ch3.di3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("setting.properties")
public class SystemInfo {

	@Value("#{systemProperties['user.timezone']}")
	String timeZone;
	
	@Value("#{systemEnvironment['USERNAME']}")
	String currDir;
	
	@Value("${autosaveDir}")
	String autosaveDir;
	
	@Value("${autosaveInterval}")
	int autosaveInterval;
	
	@Value("${autosave}")
	boolean autosave;
	
	@Override
	public String toString() {
		return "SystemInfo [timeZone=" + timeZone + ", currDir=" + currDir + ", autosaveDir=" + autosaveDir
				+ ", autosaveInternal=" + autosaveInterval + ", autosave=" + autosave + "]";
	}

}
