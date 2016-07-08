package com.thexfactor117.levels.events;

import com.thexfactor117.levels.Levels;
import com.thexfactor117.levels.capabilities.CapabilityEnemyLevel;
import com.thexfactor117.levels.capabilities.IEnemyLevel;
import com.thexfactor117.levels.leveling.EnemyLevel;
import com.thexfactor117.levels.network.PacketEnemyLevel;

import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventEntityJoinWorld 
{
	@SubscribeEvent
	public void onEntityJoinWorld(PlayerEvent.StartTracking event)
	{
		if (event.getTarget() instanceof EntityMob)
		{			
			if (!event.getTarget().worldObj.isRemote)
			{
				EntityMob mob = (EntityMob) event.getTarget();
				
				if (mob != null)
				{
					IEnemyLevel enemyLevel = mob.getCapability(CapabilityEnemyLevel.ENEMY_LEVEL_CAP, null);
					
					if (enemyLevel != null)
					{
						int level = EnemyLevel.getRandomLevel(event.getEntity().worldObj.rand).ordinal();
						enemyLevel.setEnemyLevel(level);
						Levels.NETWORK.sendToAll(new PacketEnemyLevel(enemyLevel.getEnemyLevel(), mob.getEntityId()));
					}
				}
			}
		}
	}
}
