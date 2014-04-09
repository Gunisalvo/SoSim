package org.sosim.util;

import java.util.ResourceBundle;

public class PropertiesRepository {

	private static final String PROPERTIES_DIRECTORY = "properties/";
	
	private final ResourceBundle properties;
	
	private final String name;
	
	PropertiesRepository(String name){
		
		this.name = name;
		
		this.properties = ResourceBundle.getBundle( PROPERTIES_DIRECTORY + name );

	}

	public String getName() {
		
		return name;
	
	}

	public String getParameter(String key){
		
		return properties.getString(key);
	
	}

}
