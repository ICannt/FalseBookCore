package com.bukkit.gemo.FalseBook.Core;

import com.bukkit.gemo.utils.Permissions.System.Permission;
import com.bukkit.gemo.utils.UtilPermissions;
import java.io.File;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class FalseBookCore extends JavaPlugin
{
  private static UtilPermissions Util;
  private static String pluginName;
  private static Server server;
  private static boolean usePermissionCaching = true;
  private static long permissionCacheTime = 15000L;
  private static FalseBookCoreWorldListener worldListener;
  private static FalseBookCorePlayerListener playerListener;

  public static void printInConsole(String str)
  {
    System.out.println("[ FalseBook Core ] " + str);
  }

  public void onDisable()
  {
    saveSettings();
    printInConsole(pluginName + " disabled!");
  }

  public void loadSettings() {
    try {
      File file = new File("plugins/FalseBook/FalseBookCore.yml");

      if (!file.exists()) {
        saveSettings();
        return;
      }

      YamlConfiguration config = new YamlConfiguration();
      config.load(file);
      usePermissionCaching = config.getBoolean("usePermissionCaching", true);
      permissionCacheTime = config.getLong("permissionCacheTime", permissionCacheTime);
      Permission.setPermissionCacheTime(permissionCacheTime);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void saveSettings() {
    try {
      File file = new File("plugins/FalseBook/FalseBookCore.yml");

      if (file.exists()) {
        file.delete();
      }
      YamlConfiguration config = new YamlConfiguration();

      config.set("usePermissionCaching", Boolean.valueOf(usePermissionCaching));
      config.set("permissionCacheTime", Long.valueOf(permissionCacheTime));
      config.save(file);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void onEnable()
  {
    loadVersion();
    server = getServer();
    Util = new UtilPermissions(getServer());

    worldListener = new FalseBookCoreWorldListener(this);
    playerListener = new FalseBookCorePlayerListener();
    getServer().getPluginManager().registerEvents(worldListener, this);
    getServer().getPluginManager().registerEvents(playerListener, this);

    loadSettings();
    printInConsole(pluginName + " enabled!");
  }

  private void loadVersion()
  {
    PluginDescriptionFile pdfFile = getDescription();
    pluginName = pdfFile.getName() + " v" + pdfFile.getVersion() + " by GeMo";
  }

  public static UtilPermissions getUtil() {
    return Util;
  }

  public static String getPluginName() {
    return pluginName;
  }

  public static void setPluginName(String ipluginName) {
    pluginName = ipluginName;
  }

  public static Server getMCServer() {
    return server;
  }

  public static void setMCServer(Server iserver) {
    server = iserver;
  }

  public static Player getPlayer(String name) {
    for (Player player : server.getOnlinePlayers()) {
      if (player.getName().equalsIgnoreCase(name))
        return player;
    }
    return null;
  }

  public static boolean usePermissionCaching() {
    return usePermissionCaching;
  }

  public static long getPermissionCacheTime()
  {
    return permissionCacheTime;
  }
}