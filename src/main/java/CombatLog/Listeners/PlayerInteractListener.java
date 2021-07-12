/*
 *  CombatLog is a plugin for the popular game Minecraft that strives to
 *  make PvP combat in the game more fair
 *  
 *  Copyright (C) 2018 Jarod Saxberg (iiSnipez)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerInteractListener implements Listener {

	CombatLog plugin;

	public PlayerInteractListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (plugin.blockTeleportationEnabled) {
			if (plugin.taggedPlayers.containsKey(player.getName())) {
				if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
						|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if (event.getMaterial().equals(Material.ENDER_PEARL)) {
						event.setCancelled(true);
						if (plugin.blockTeleportationMessageEnabled) {
							player.sendMessage(plugin.translateText(plugin.blockTeleportationMessage));
						}
					}
				}
			}
		}
	}
}