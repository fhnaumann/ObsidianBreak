package me.wand555.ObsidianBreak;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class ObsidianBreak extends JavaPlugin {

	private static ObsidianBreak plugin;
	private static HashMap<Location, Integer> obsidian = new HashMap<>();
	
	public void onEnable() {
		plugin = this;
		new FormEvent(this);
		new RightClickObsidian(this);
		loadConfig();
	}
	
	public void onDisable() {
		
	}
	
	public static ObsidianBreak getInstance() {
		return plugin;
	}
	
	private void loadConfig() {
		this.getConfig().options().copyDefaults(true);		
		this.getConfig().addDefault("Hits", 5);
		//this.getConfig().addDefault("#in seconds", "");
		this.getConfig().addDefault("RegenerationTime", 60*60);
		this.getConfig().addDefault("OnlyDiamondPickaxe", true);
		this.saveConfig();
	}

	/**
	 * @return the obsidian
	 */
	public static HashMap<Location, Integer> getObsidian() {
		return obsidian;
	}
	
	public static Integer getStateFromLocation(Location loc) {
		if(!obsidian.containsKey(loc)) return null;
		else {
			return obsidian.get(loc);
		}
	}
	
	public static void addObsidian(Location loc) {
		if(!obsidian.containsKey(loc)) obsidian.put(loc, ObsidianBreak.getInstance().getConfig().getInt("Hits"));
	}
	
	public static void removeObsidian(Location loc) {
		if(obsidian.containsKey(loc)) obsidian.remove(loc);
	}
	
	public static void incrementStability(Location loc) {
		if(obsidian.containsKey(loc)) {
			if(obsidian.get(loc) < ObsidianBreak.getInstance().getConfig().getInt("Hits")) {
				obsidian.put(loc, obsidian.get(loc)+1);
			}			
		}
	}
	
	public static void decrementStability(Location loc) {
		if(obsidian.containsKey(loc)) obsidian.put(loc, obsidian.get(loc)-1);
	}
}
