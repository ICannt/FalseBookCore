package com.bukkit.gemo.utils;

import org.bukkit.ChatColor;

public enum EnumColors {

    AQUA("aqua", ChatColor.AQUA),
    BLACK("black", ChatColor.BLACK),
    BLUE("blue", ChatColor.BLUE),
    DARK_AQUA("darkaqua", ChatColor.DARK_AQUA),
    DARK_BLUE("darkblue", ChatColor.DARK_BLUE),
    DARK_GRAY("darkgray", ChatColor.DARK_GRAY),
    DARK_GREEN("darkgreen", ChatColor.DARK_GREEN),
    DARK_PURPLE("darkpurple", ChatColor.DARK_PURPLE),
    DARK_RED("darkred", ChatColor.DARK_RED),
    GOLD("gold", ChatColor.GOLD),
    GRAY("gray", ChatColor.GRAY),
    GREEN("green", ChatColor.GREEN),
    LIGHT_PURPLE("lightpurple", ChatColor.LIGHT_PURPLE),
    RED("red", ChatColor.RED),
    WHITE("white", ChatColor.WHITE),
    YELLOW("yellow", ChatColor.YELLOW);
    private final ChatColor color;
    private final String name;

    private EnumColors(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public static ChatColor getColor(String name) {
        name = name.replace("_", "");
        for (EnumColors enums : values()) {
            if (enums.getName().equalsIgnoreCase(name)) {
                return enums.getColor();
            }
        }
        return null;
    }
}