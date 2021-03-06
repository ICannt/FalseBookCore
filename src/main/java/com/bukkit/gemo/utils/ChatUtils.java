package com.bukkit.gemo.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void printLine(CommandSender sender, ChatColor color, String message) {
        if (sender == null) {
            return;
        }
        if ((sender instanceof Player)) {
            ((Player) sender).sendMessage(color + message);
        } else {
            sender.sendMessage(message);
        }
    }

    public static void printInfo(CommandSender sender, String pluginName, ChatColor color, String message) {
        if (sender == null) {
            return;
        }
        if ((sender instanceof Player)) {
            ((Player) sender).sendMessage(ChatColor.AQUA + pluginName + (pluginName.length() > 0 ? " " : "") + color + message);
        } else {
            sender.sendMessage(ChatColor.AQUA + pluginName + (pluginName.length() > 0 ? " " : "") + color + message);
        }
    }

    public static void printError(CommandSender sender, String pluginName, String message) {
        printInfo(sender, pluginName, ChatColor.RED, message);
    }

    public static void printSuccess(CommandSender sender, String pluginName, String message) {
        printInfo(sender, pluginName, ChatColor.GREEN, message);
    }

    public static void printWrongSyntax(CommandSender sender, String pluginName, String Syntax, String[] Examples) {
        printError(sender, pluginName, "Wrong Syntax! Use: " + Syntax);

        if (Examples.length == 1) {
            printInfo(sender, pluginName, ChatColor.GRAY, "Example:");
        } else if (Examples.length > 1) {
            printInfo(sender, pluginName, ChatColor.DARK_RED, "Examples:");
        }
        for (int i = 0; i < Examples.length; i++) {
            printInfo(sender, pluginName, ChatColor.GRAY, Examples[i]);
        }
    }
}