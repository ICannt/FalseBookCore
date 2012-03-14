package com.bukkit.gemo.utils.Permissions;

import java.util.List;
import org.bukkit.entity.Player;

public abstract interface IPermissions {

    public abstract boolean isActive();

    public abstract boolean permission(Player paramPlayer, String paramString);

    public abstract List<String> getGroups(Player paramPlayer);

    public abstract List<String> getGroups(String paramString1, String paramString2);
}