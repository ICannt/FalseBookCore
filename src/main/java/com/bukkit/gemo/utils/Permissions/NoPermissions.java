package com.bukkit.gemo.utils.Permissions;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

public class NoPermissions
  implements IPermissions
{
  public boolean isActive()
  {
    return true;
  }

  public List<String> getGroups(Player player)
  {
    return new ArrayList<String>();
  }

  public List<String> getGroups(String playername, String worldName)
  {
    return new ArrayList<String>();
  }

  public boolean permission(Player player, String node)
  {
    return player.isOp();
  }
}