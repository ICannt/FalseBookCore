package com.bukkit.gemo.utils.Permissions;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PEXPermissions extends SuperPermsPermissions
        implements IPermissions {

    public boolean isActive() {
        return true;
    }

    public boolean permission(Player player, String node) {
        boolean permission = super.permission(player, node);
        if (permission) {
            return true;
        }
        return PermissionsEx.getPermissionManager().has(player, node);
    }

    public List<String> getGroups(String playerName, String worldName) {
        PermissionUser user = PermissionsEx.getPermissionManager().getUser(playerName);
        if (user == null) {
            return new ArrayList<String>();
        }

        PermissionGroup[] groups = user.getGroups(worldName);
        ArrayList<String> groupMap = new ArrayList<String>();

        for (PermissionGroup group : groups) {
            getInheritedGroups(groupMap, group);
        }
        return groupMap;
    }

    public List<String> getGroups(Player player) {
        return getGroups(player.getName(), player.getWorld().getName());
    }

    public void getInheritedGroups(ArrayList<String> groupMap, PermissionGroup group) {
        if (!groupMap.contains(group.getName())) {
            groupMap.add(group.getName());
            PermissionGroup[] inhGroups = group.getParentGroups();
            for (PermissionGroup grp : inhGroups) {
                getInheritedGroups(groupMap, grp);
            }
        }
    }
}