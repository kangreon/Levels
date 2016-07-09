package com.thexfactor117.levels.init;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.events.EventItemTooltip;
import com.thexfactor117.levels.events.EventLivingDeath;
import com.thexfactor117.levels.events.EventLivingDrops;
import com.thexfactor117.levels.events.EventLivingHurt;
import com.thexfactor117.levels.events.EventLivingUpdate;
import com.thexfactor117.levels.events.EventPlayerTracking;
import com.thexfactor117.levels.events.EventRenderOverlay;
import com.thexfactor117.levels.handlers.ConfigHandler;

import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModEvents 
{
	public static void registerEvents()
	{
		Levels.LOGGER.info("Registering events...");

		if (ConfigHandler.LEVELING_SYSTEM)
		{
			Levels.LOGGER.info("Weapon Leveling system activating...");
			MinecraftForge.EVENT_BUS.register(new EventLivingHurt());
			MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
			MinecraftForge.EVENT_BUS.register(new EventLivingDeath());
			MinecraftForge.EVENT_BUS.register(new EventLivingUpdate());
		}
		
		if (ConfigHandler.ENEMY_LEVELING)
		{
			MinecraftForge.EVENT_BUS.register(new EventPlayerTracking());
			MinecraftForge.EVENT_BUS.register(new EventRenderOverlay());
		}
		
		if (ConfigHandler.MOB_DROPS)
		{
			Levels.LOGGER.info("Mob droppings have been enabled...");
			MinecraftForge.EVENT_BUS.register(new EventLivingDrops());
		}

		Levels.LOGGER.info("Event registration has finished.");
	}
}
