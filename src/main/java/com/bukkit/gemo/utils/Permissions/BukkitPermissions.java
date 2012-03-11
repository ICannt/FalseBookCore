package com.bukkit.gemo.utils.Permissions;

import com.platymuus.bukkit.permissions.Group;
import com.platymuus.bukkit.permissions.PermissionsPlugin;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BukkitPermissions extends SuperPermsPermissions
{
  private PermissionsPlugin handler = null;

  public BukkitPermissions() {
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("PermissionsBukkit");

    if (plugin == null) {
      return;
    }

    this.handler = ((PermissionsPlugin)plugin);
  }

  public boolean isActive()
  {
    return this.handler != null;
  }

  public List<String> getGroups(Player player)
  {
    List<String> groups = super.getGroups(player);

    if (this.handler == null) {
      return groups;
    }

    List<Group> found = this.handler.getGroups(player.getName());

    if (found.size() == 0) {
      return groups;
    }

    for (Group group : found) {
      groups.add(group.getName());
    }

    return groups;
  }

  public List<String> getGroups(String playerName, String worldName)
  {
    List<String> groups = super.getGroups(playerName, worldName);

    if (this.handler == null) {
      return groups;
    }

    List<Group> found = this.handler.getGroups(playerName);

    if (found.size() == 0) {
      return groups;
    }

    for (Group group : found) {
      groups.add(group.getName());
    }

    return groups;
  }

  public boolean permission(Player player, String node)
  {
    return player.hasPermission(node);
  }
}