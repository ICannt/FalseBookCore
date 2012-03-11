package com.bukkit.gemo.utils.Permissions;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NijiPermissions
  implements IPermissions
{
  private PermissionHandler handler = null;

  public NijiPermissions() {
    Plugin permissionsPlugin = Bukkit.getServer().getPluginManager().getPlugin("Permissions");

    if (permissionsPlugin == null) {
      return;
    }

    this.handler = ((Permissions)permissionsPlugin).getHandler();
  }

  public boolean isActive() {
    return this.handler != null;
  }

  public boolean permission(Player player, String node) {
    return (this.handler != null) && (this.handler.has(player, node));
  }

  public List<String> getGroups(String playerName, String worldName)
  {
    if (this.handler == null) {
      return null;
    }

    List<String> groups = new ArrayList<String>();
    try
    {
      groups.addAll(Arrays.asList(this.handler.getGroups(worldName, playerName)));
    }
    catch (NoSuchMethodError e) {
      groups.add(this.handler.getGroup(worldName, playerName));
    }

    return groups;
  }

  public List<String> getGroups(Player player)
  {
    return getGroups(player.getName(), player.getWorld().getName());
  }
}