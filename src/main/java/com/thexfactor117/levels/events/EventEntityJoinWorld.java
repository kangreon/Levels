package com.thexfactor117.levels.events;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.leveling.EnemyLevel;
import com.thexfactor117.levels.network.PacketEnemyLevel;

import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventEntityJoinWorld 
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof EntityMob)
		{
			if (!event.getEntity().worldObj.isRemote)
			{
				EntityMob mob = (EntityMob) event.getEntity();
				IEnemyLevel enemyLevel = mob.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);
				
				if (enemyLevel != null)
				{
					int level = EnemyLevel.getRandomLevel(event.getEntity().worldObj.rand).ordinal();
					enemyLevel.setEnemyLevel(level);
					Levels.NETWORK.sendToAll(new PacketEnemyLevel(enemyLevel.getEnemyLevel()));
				}
			}
		}
	}
}
