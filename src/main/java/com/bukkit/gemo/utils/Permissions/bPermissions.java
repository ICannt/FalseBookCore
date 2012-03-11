package com.bukkit.gemo.utils.Permissions;

import de.bananaco.bpermissions.api.User;
import de.bananaco.bpermissions.api.WorldManager;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class bPermissions extends SuperPermsPermissions
{
  private WorldManager handler;

  public bPermissions()
  {
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("bPermissions");

    if (plugin == null) {
      return;
    }

    this.handler = WorldManager.getInstance();
  }

  public boolean isActive()
  {
    return this.handler != null;
  }

  public List<String> getGroups(Player player)
  {
    de.bananaco.bpermissions.api.World bPermWorld = this.handler.getWorld(player.getWorld().getName());
    User bPermUser = bPermWorld.getUser(player.getName());
    return new ArrayList<String>(bPermUser.getGroupsAsString());
  }

  public List<String> getGroups(String playerName, String worldName)
  {
    de.bananaco.bpermissions.api.World bPermWorld = this.handler.getWorld(worldName);
    User bPermUser = bPermWorld.getUser(playerName);
    return new ArrayList<String>(bPermUser.getGroupsAsString());
  }

  public boolean permission(Player player, String node)
  {
    return player.hasPermission(node);
  }
}