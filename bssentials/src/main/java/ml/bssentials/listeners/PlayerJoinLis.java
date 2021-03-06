package ml.bssentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ml.bssentials.main.Bssentials;

/**
 * PlayerJoinEvent Listener.
 * 
 * @author Bssentials
 **/
public class PlayerJoinLis implements Listener {

	private Bssentials main;
	
	public PlayerJoinLis(Bssentials bs) {
		main = bs;
	}
	
	@SuppressWarnings("static-access") 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPlayedBefore()) {
		    main.getConfig().set("playerdata."+player.getName()+"uuid", player.getUniqueId().toString());
		    if (main.getWarpConfig().getConfigurationSection("warps.spawn") == null) {
                player.sendMessage(ChatColor.RED + "Spawn has not been set!");
            } else {
                World w = Bukkit.getServer().getWorld(main.getWarpConfig().getString("warps.spawn.world"));
                double x = main.getWarpConfig().getDouble("warps.spawn.x");
                double y = main.getWarpConfig().getDouble("warps.spawn.y");
                double z = main.getWarpConfig().getDouble("warps.spawn.z");
                player.teleport(new Location(w, x, y, z));
                player.sendMessage(ChatColor.GREEN + "Warping to spawn");
            }
            Bukkit.broadcastMessage(main.prefix + " Please welcome " + player.getName() + " to the server!");
		}
		
		if (main.getConfig().getString("playerdata." + player.getName() + ".nick") != null) {
			player.setDisplayName(main.getConfig().getString("playerdata." + player.getName() + ".nick"));
			player.sendMessage(Bssentials.PREFIX + ChatColor.GOLD + "Found a nick name you set! type /disnick to disable your nickname!");
		}
	}
}
