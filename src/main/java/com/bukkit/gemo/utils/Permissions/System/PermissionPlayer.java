package com.bukkit.gemo.utils.Permissions.System;

import java.util.HashMap;
import org.bukkit.entity.Player;

public class PermissionPlayer
{
  private HashMap<String, Permission> cachedPermissions;

  public PermissionPlayer()
  {
    this.cachedPermissions = new HashMap<String, Permission>();
  }

  public boolean hasPermission(Player player, String permissionNode, boolean forceUpdate)
  {
    Permission permission = this.cachedPermissions.get(permissionNode);
    if (permission == null) {
      permission = new Permission(player, permissionNode);
      this.cachedPermissions.put(permissionNode, permission);
    }
    return permission.hasPermission(player, forceUpdate);
  }

  public void removePermission(String permissionNode)
  {
    this.cachedPermissions.remove(permissionNode);
  }

  public void clearPermissions()
  {
    this.cachedPermissions = new HashMap<String, Permission>();
  }
}