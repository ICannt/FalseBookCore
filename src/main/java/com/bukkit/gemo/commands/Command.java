package com.bukkit.gemo.commands;

import com.bukkit.gemo.utils.ChatUtils;
import com.bukkit.gemo.utils.UtilPermissions;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Command {

    public static final String NO_RIGHT = "You are not allowed to use this command!";
    protected String description = "";
    private String syntax;
    private String arguments;
    protected String permissionNode;
    protected String pluginName = "";
    private final int argumentCount;

    public Command(String syntax, String arguments, String node) {
        this.syntax = syntax;
        this.arguments = arguments;
        this.permissionNode = node;
        this.argumentCount = countArguments();
    }

    public Command(String pluginName, String syntax, String arguments, String node) {
        this(syntax, arguments, node);
        if (pluginName != null) {
            this.pluginName = pluginName;
        }
    }

    public void run(String[] args, CommandSender sender) {
        if (!hasRights(sender)) {
            ChatUtils.printError(sender, this.pluginName, "You are not allowed to use this command!");
            return;
        }

        if (!hasCorrectSyntax(args)) {
            ChatUtils.printInfo(sender, this.pluginName, ChatColor.GRAY, getHelpMessage());
            return;
        }

        execute(args, sender);
    }

    public abstract void execute(String[] paramArrayOfString, CommandSender paramCommandSender);

    protected boolean hasRights(CommandSender sender) {
        if ((sender instanceof Player)) {
            return (this.permissionNode.length() == 0) || (UtilPermissions.playerCanUseCommand((Player) sender, getPermissionNode()));
        }
        return true;
    }

    protected boolean hasCorrectSyntax(String[] args) {
        return args.length == this.argumentCount;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHelpMessage(String parentCommand) {
        return ChatColor.DARK_AQUA + (parentCommand.length() > 0 ? parentCommand + " " : "") + getSyntax() + " " + getArguments() + ChatColor.GRAY + " : " + getDescription();
    }

    public String getHelpMessage() {
        return getHelpMessage("");
    }

    public String getSyntax() {
        return this.syntax;
    }

    public String getArguments() {
        return this.arguments;
    }

    public String getPermissionNode() {
        return this.permissionNode;
    }

    public int getArgumentCount() {
        return this.argumentCount;
    }

    private int countArguments() {
        if (this.arguments.isEmpty()) {
            return 0;
        }
        int counter = 0;
        for (int i = 0; i < this.arguments.length(); i++) {
            if (this.arguments.charAt(i) == '<') {
                counter++;
            }
        }
        return counter;
    }
}