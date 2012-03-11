package com.bukkit.gemo.utils.Permissions;

import com.bukkit.gemo.FalseBook.Core.FalseBookCore;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class SuperPermsPermissions
  implements IPermissions
{
  private String groupPrefix;

  public SuperPermsPermissions()
  {
    this.groupPrefix = "falsebook.group.";
  }

  public boolean isActive() {
    return true;
  }

  public boolean permission(Player player, String node) {
    try {
      return player.hasPermission(node);
    }
    catch (NoSuchMethodError localNoSuchMethodError) {
    }
    return player.isOp();
  }

  public List<String> getGroups(Player player)
  {
    return getGroups(player.getName(), player.getWorld().getName());
  }

  public List<String> getGroups(String playerName, String worldName)
  {
    Player player = FalseBookCore.getPlayer(playerName);
    if (player == null) {
      return new ArrayList<String>();
    }
    List<String> groups = new ArrayList<String>();
    try {
      Method method = CraftHumanEntity.class.getDeclaredMethod("getEffectivePermissions", new Class[0]);
      if (method != null) {
        for (PermissionAttachmentInfo pai : player.getEffectivePermissions()) {
          if (pai.getPermission().startsWith(this.groupPrefix)) {
            groups.add(pai.getPermission().substring(this.groupPrefix.length()));
          }
        }
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    return groups;
  }
}