package com.bukkit.gemo.commands;

import com.bukkit.gemo.utils.ChatUtils;
import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class SuperCommand extends Command {

    private Command[] subCommands;
    private boolean hasFunction;
    private String pluginName = "";

    public SuperCommand(String syntax, String arguments, String node, boolean hasFunction, Command[] subCommands) {
        super(syntax, arguments, node);
        this.hasFunction = hasFunction;
        this.subCommands = subCommands;
    }

    public SuperCommand(String pluginName, String syntax, String arguments, String node, boolean hasFunction, Command[] subCommands) {
        this(syntax, arguments, node, hasFunction, subCommands);
        if (pluginName != null) {
            this.pluginName = pluginName;
        }
    }

    public SuperCommand(String syntax, String arguments, String node, Command[] subCommands) {
        this(syntax, arguments, node, false, subCommands);
    }

    public abstract void execute(String[] paramArrayOfString, CommandSender paramCommandSender);

    public void run(String[] args, CommandSender sender) {
        if (args.length == 0) {
            if (this.hasFunction) {
                ChatUtils.printInfo(sender, this.pluginName, ChatColor.GRAY, getHelpMessage());
            } else {
                printSubcommands(sender);
            }
            return;
        }

        if (!runSubCommand(args, sender)) {
            super.run(args, sender);
        }
    }

    private void printSubcommands(CommandSender sender) {
        ChatUtils.printInfo(sender, this.pluginName, ChatColor.GOLD, "Possible commands:");

        for (Command command : getSubCommands()) {
            ChatUtils.printLine(sender, ChatColor.GRAY, command.getHelpMessage(getSyntax()));
        }
    }

    protected boolean runSubCommand(String[] args, CommandSender sender) {
        if ((args != null) && (args.length == 0)) {
            return false;
        }
        for (Command com : this.subCommands) {
            if (com.getSyntax().equalsIgnoreCase(args[0])) {
                com.run(Arrays.copyOfRange(args, 1, args.length), sender);
                return true;
            }
        }
        return false;
    }

    protected Command[] getSubCommands() {
        return this.subCommands;
    }
}