package com.bukkit.gemo.commands;

import com.bukkit.gemo.utils.ChatUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandList {

    private HashMap<String, Command> commandList;
    private String pluginName = "";

    public CommandList(Command[] commands) {
        initCommandList(commands);
    }

    public CommandList(String pluginName, Command[] commands) {
        this(commands);
        if (pluginName != null) {
            this.pluginName = pluginName;
        }
    }

    public void handleCommand(CommandSender sender, String label, String[] args) {
        if (!label.startsWith("/")) {
            label = "/" + label;
        }

        Command cmd = this.commandList.get(label + "_" + args.length);
        if (cmd != null) {
            cmd.run(args, sender);
        } else {
            cmd = this.commandList.get(label);
            if (cmd != null) {
                cmd.run(args, sender);
            } else {
                ChatUtils.printError(sender, this.pluginName, "Command '" + label + "' not found.");

                LinkedList<Command> cmdList = new LinkedList<Command>();
                for (Entry<String, Command> entry : this.commandList.entrySet()) {
                    if (((String) entry.getKey()).startsWith(label)) {
                        cmdList.add((Command) entry.getValue());
                    }
                }

                for (Command command : cmdList) {
                    ChatUtils.printInfo(sender, this.pluginName, ChatColor.GRAY, command.getSyntax() + " " + command.getArguments());
                }
            }
        }
    }

    private void initCommandList(Command[] cmds) {
        this.commandList = new HashMap<String, Command>();
        for (Command cmd : cmds) {
            String key = "";

            if (((cmd instanceof ExtendedCommand)) || ((cmd instanceof SuperCommand))) {
                key = cmd.getSyntax();
            } else {
                key = cmd.getSyntax() + "_" + cmd.getArgumentCount();
            }
            this.commandList.put(key, cmd);
        }
    }
}