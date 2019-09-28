package me.wand555.ObsidianBreak;

import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class ObsidianRunnable extends BukkitRunnable {

	@Override
	public void run() {
		for(Entry<Location, Integer> entry : ObsidianBreak.getObsidian().entrySet()) {
			if(entry.getValue() == 5) continue;
			else {
				ObsidianBreak.incrementStability(entry.getKey());
			}
		}
	}

}
