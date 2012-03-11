package com.bukkit.gemo.utils.Permissions;

import java.util.List;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultPermissions extends SuperPermsPermissions
{
  private Permission perms = null;

  public VaultPermissions() {
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Vault");

    if (plugin == null) {
      return;
    }

    RegisteredServiceProvider<Permission> rsp = Bukkit.getServicesManager().getRegistration(Permission.class);
    this.perms = rsp.getProvider();
  }

  public boolean isActive()
  {
    return this.perms != null;
  }

  public List<String> getGroups(Player player)
  {
    List<String> groups = super.getGroups(player);

    if (this.perms == null) {
      return groups;
    }

    String[] playerGroups = this.perms.getPlayerGroups(player);
    if (playerGroups != null) {
      for (String group : playerGroups)
        groups.add(group);
    }
    return groups;
  }

  public List<String> getGroups(String playerName, String worldName)
  {
    List<String> groups = super.getGroups(playerName, worldName);

    if (this.perms == null) {
      return groups;
    }

    String[] playerGroups = this.perms.getPlayerGroups(worldName, playerName);
    if (playerGroups != null) {
      for (String group : playerGroups)
        groups.add(group);
    }
    return groups;
  }

  public boolean permission(Player player, String node)
  {
    return this.perms.playerHas(player, node);
  }
}