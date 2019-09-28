package me.wand555.ObsidianBreak;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class RightClickObsidian implements Listener {

	public RightClickObsidian(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onRightClickObsidian(PlayerInteractEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
			if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if(event.getClickedBlock().getType() == Material.OBSIDIAN) {
					if(ObsidianBreak.getInstance().getConfig().getBoolean("OnlyDiamondPickaxe")) {
						if(event.getMaterial() == Material.DIAMOND_PICKAXE) {	
							Location loc = event.getClickedBlock().getLocation();				
							if(ObsidianBreak.getObsidian().containsKey(loc)) {
								ObsidianBreak.decrementStability(loc);
								if(ObsidianBreak.getStateFromLocation(loc) == 0) {
									loc.getWorld().dropItemNaturally(loc, new ItemStack(event.getClickedBlock().getType(), 1));
									loc.getWorld().getBlockAt(loc).setType(Material.AIR);
									ObsidianBreak.removeObsidian(loc);
								}
							}
							else {
								ObsidianBreak.addObsidian(loc);
								ObsidianBreak.decrementStability(loc);
								if(ObsidianBreak.getStateFromLocation(loc) == 0) {
									loc.getWorld().dropItemNaturally(loc, new ItemStack(event.getClickedBlock().getType(), 1));
									loc.getWorld().getBlockAt(loc).setType(Material.AIR);					
									ObsidianBreak.removeObsidian(loc);
								}
							}	
						}
					}	
					else {
						Location loc = event.getClickedBlock().getLocation();				
						if(ObsidianBreak.getObsidian().containsKey(loc)) {
							ObsidianBreak.decrementStability(loc);
							if(ObsidianBreak.getStateFromLocation(loc) == 0) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(event.getClickedBlock().getType(), 1));
								loc.getWorld().getBlockAt(loc).setType(Material.AIR);
								ObsidianBreak.removeObsidian(loc);
							}
						}
						else {
							ObsidianBreak.addObsidian(loc);
							ObsidianBreak.decrementStability(loc);
							if(ObsidianBreak.getStateFromLocation(loc) == 0) {
								loc.getWorld().dropItemNaturally(loc, new ItemStack(event.getClickedBlock().getType(), 1));
								loc.getWorld().getBlockAt(loc).setType(Material.AIR);					
								ObsidianBreak.removeObsidian(loc);
							}
						}
					}
				}		
			}
		}
		
	}
}
