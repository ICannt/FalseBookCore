package com.bukkit.gemo.utils.Permissions;

import com.bukkit.gemo.FalseBook.Core.FalseBookCore;
import java.util.ArrayList;
import java.util.List;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AnjoPermissions
  implements IPermissions
{
  private AnjoPermissionsHandler handler = null;

  public AnjoPermissions()
  {
    Plugin gmPlugin = Bukkit.getServer().getPluginManager().getPlugin("GroupManager");
    FalseBookCore.getMCServer().getPluginManager().enablePlugin(gmPlugin);
    this.handler = ((GroupManager)gmPlugin).getPermissionHandler();
  }

  public boolean isActive() {
    return this.handler != null;
  }

  public boolean permission(Player player, String node) {
    return (this.handler != null) && (this.handler.has(player, node));
  }

  public List<String> getGroups(String playerName, String worldName) {
    if (this.handler == null) {
      return null;
    }
    List<String> groups = new ArrayList<String>();
    String[] list = this.handler.getGroups(playerName);
    for (String str : list) {
      groups.add(str);
    }

    return groups;
  }

  public List<String> getGroups(Player player)
  {
    return getGroups(player.getName(), player.getWorld().getName());
  }
}