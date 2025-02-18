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

package CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import CombatLog.CombatLog;

public class PlayerCommandPreprocessListener implements Listener {

	public CombatLog plugin;

	public PlayerCommandPreprocessListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String cmd = event.getMessage();

		if (plugin.taggedPlayers.containsKey(player.getName()) && !cmd.equalsIgnoreCase("ct") && !cmd.equalsIgnoreCase("tag")) {
			cmd = cmd.replaceAll("/", "");
			if (plugin.blockCommandsEnabled) {
				if(plugin.commandNames.contains(cmd)) {
					event.setCancelled(true);
					if (plugin.blockCommandsMessageEnabled) {
						player.sendMessage(plugin.translateText(plugin.blockCommandsMessage + ""));
					}
				}
			} else if (plugin.whitelistModeEnabled) {
				if(!plugin.commandNames.contains(cmd)) {
					event.setCancelled(true);
					if (plugin.blockCommandsMessageEnabled) {
						player.sendMessage(plugin.translateText(plugin.blockCommandsMessage + ""));
					}
				}
			}
		}
	}
}