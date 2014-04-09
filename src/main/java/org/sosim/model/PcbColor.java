package org.sosim.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum PcbColor {
	
	DISABLED("ico_pcb_disabled",false),
	RED("ico_pcb_red",true),
	GREEN("ico_pcb_green",true),
	BLUE("ico_pcb_blue",true),
	GRAY("ico_pcb_gray",true);
	
	PcbColor(String iconUrl, boolean selectable){
		this.iconUrl = iconUrl;
		this.seletable = selectable;
	}
	
	private final String iconUrl;
	
	private final boolean seletable;

	public String getIconCode() {
		return iconUrl;
	}

	public boolean isSelectable() {
		return seletable;
	}
	
	public static List<PcbColor> getSelectableList(){
		
		List<PcbColor> result = new ArrayList<PcbColor>();
		
		for(PcbColor value : PcbColor.values()){
			if(value.isSelectable()){
				result.add(value);
			}
		}
		
		return result;
	}

	public static PcbColor getRandomColor() {
		Random generator = new Random();
		
		PcbColor result;
		
		do{
			result = PcbColor.values()[generator.nextInt(PcbColor.values().length)];
		}while(!result.isSelectable());
		
		return result;
	}
}
