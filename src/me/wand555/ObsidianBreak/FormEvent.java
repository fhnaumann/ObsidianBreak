package me.wand555.ObsidianBreak;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FormEvent implements Listener {

	public FormEvent(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onFormEvent(BlockFormEvent event) {
		ObsidianBreak.addObsidian(event.getBlock().getLocation());
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(ObsidianBreak.getObsidian().containsKey(event.getBlock().getLocation())) {
					ObsidianBreak.incrementStability(event.getBlock().getLocation());
				}
				else {
					this.cancel();
				}
				
			}
		}.runTaskTimer(ObsidianBreak.getInstance(), 0L, 20*ObsidianBreak.getInstance().getConfig().getLong("RegenerationTime"));
	}
	
	@EventHandler
	public void onPlaceEvent(BlockPlaceEvent event) {
		ObsidianBreak.addObsidian(event.getBlock().getLocation());
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(ObsidianBreak.getObsidian().containsKey(event.getBlock().getLocation())) {
					ObsidianBreak.incrementStability(event.getBlock().getLocation());
				}
				else {
					this.cancel();
				}
				
			}
		}.runTaskTimer(ObsidianBreak.getInstance(), 0L, 20*ObsidianBreak.getInstance().getConfig().getLong("RegenerationTime"));
	}
	
	@EventHandler
	public void onNaturalBreakEvent(BlockBreakEvent event) {
		ObsidianBreak.removeObsidian(event.getBlock().getLocation());
	}
}
