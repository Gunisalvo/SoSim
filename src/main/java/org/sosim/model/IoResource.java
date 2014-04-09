package org.sosim.model;

import java.util.Random;

public enum IoResource {

	DISABLED("ico_io_disabled",false),
	HD("ico_io_disk",true),
	USB("ico_io_usb",true),
	ETHERNET("ico_io_ethernet",true);

	IoResource(String iconUrl, boolean selectable){
		this.iconUrl = iconUrl;
		this.selectable = selectable;
	}
	
	private final String iconUrl;
	
	private boolean selectable;

	public String getIconCode() {
		return iconUrl;
	}
	
	public boolean isSelectable() {
		return selectable;
	}

	public static IoResource getRandomResource(){
		
		Random generator = new Random();
		
		IoResource result;
		
		do{
			result = IoResource.values()[generator.nextInt(IoResource.values().length)];
		}while(!result.isSelectable());
		
		return result;
	}
}
