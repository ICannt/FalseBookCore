package com.bukkit.gemo.FalseBook.Core;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.PluginManager;

public class FalseBookCoreWorldListener
  implements Listener
{
  private FalseBookCore plugin;

  public FalseBookCoreWorldListener(FalseBookCore instance)
  {
    this.plugin = instance;
  }
  @EventHandler
  public void onWorldLoad(WorldLoadEvent event) {
    if (this.plugin.getServer().getPluginManager().getPlugin("FalseBookBlock") != null) {
      FalseBookPlugin blockCore = (FalseBookPlugin)this.plugin.getServer().getPluginManager().getPlugin("FalseBookBlock");
      if (blockCore.isEnabled()) {
        blockCore.initWorld(event.getWorld().getName());
      }
    }
    if (this.plugin.getServer().getPluginManager().getPlugin("FalseBookCart") != null) {
      FalseBookPlugin cartCore = (FalseBookPlugin)this.plugin.getServer().getPluginManager().getPlugin("FalseBookCart");
      if (cartCore.isEnabled()) {
        cartCore.initWorld(event.getWorld().getName());
      }
    }
    if (this.plugin.getServer().getPluginManager().getPlugin("FalseBookExtra") != null) {
      FalseBookPlugin extraCore = (FalseBookPlugin)this.plugin.getServer().getPluginManager().getPlugin("FalseBookExtra");
      if (extraCore.isEnabled())
        extraCore.initWorld(event.getWorld().getName());
    }
  }
}