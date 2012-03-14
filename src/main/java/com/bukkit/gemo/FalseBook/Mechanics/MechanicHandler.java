package com.bukkit.gemo.FalseBook.Mechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

@SuppressWarnings("rawtypes")
public class MechanicHandler {

    private Map<Class, List<MechanicListener>> registeredEvents;
    private Map<String, MechanicListener> registeredListeners;

    public MechanicHandler() {
        registerEvents();
    }

    private void registerEvents() {
        this.registeredListeners = new HashMap<String, MechanicListener>();
        this.registeredEvents = new HashMap<Class, List<MechanicListener>>();

        this.registeredEvents.put(BlockBreakEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(BlockPlaceEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(BlockPistonExtendEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(BlockPistonRetractEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(BlockRedstoneEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(SignChangeEvent.class, new ArrayList<MechanicListener>());

        this.registeredEvents.put(EntityChangeBlockEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(EntityExplodeEvent.class, new ArrayList<MechanicListener>());

        this.registeredEvents.put(PlayerInteractEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(PlayerLoginEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(PlayerPreLoginEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(PlayerQuitEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(PlayerRespawnEvent.class, new ArrayList<MechanicListener>());
        this.registeredEvents.put(PlayerTeleportEvent.class, new ArrayList<MechanicListener>());
    }

    public boolean registerEvent(Class eventClass, MechanicListener listener) {
        return this.registeredEvents.get(eventClass).add(listener);
    }

    public boolean unregisterEvent(Class eventClass, MechanicListener listener) {
        return this.registeredEvents.get(eventClass).remove(listener);
    }

    public boolean registerMechanic(String name, MechanicListener listener) {
        return this.registeredListeners.put(name, listener) != null;
    }

    public boolean unregisterMechanic(String name) {
        return this.registeredListeners.remove(name) != null;
    }

    public MechanicListener getMechanic(String name) {
        return this.registeredListeners.get(name);
    }

    public Map<String, MechanicListener> getAllMechanics() {
        return this.registeredListeners;
    }

    public boolean hasMechanic(String name) {
        return this.registeredListeners.containsKey(name);
    }

    public void onLoad() {
        for (MechanicListener listener : this.registeredListeners.values()) {
            listener.onLoad();
        }
    }

    public void onUnload() {
        for (MechanicListener listener : this.registeredListeners.values()) {
            listener.onUnload();
        }
    }

    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (MechanicListener listener : this.registeredListeners.values()) {
            listener.onCommand(sender, command, label, args);
        }
    }

    public void onBlockBreak(BlockBreakEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onBlockBreak(event);
        }
        mechanicList = null;
    }

    public void onBlockPlace(BlockPlaceEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onBlockPlace(event);
        }
        mechanicList = null;
    }

    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onBlockRedstoneChange(event);
        }
        mechanicList = null;
    }

    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onBlockPistonExtend(event);
        }
        mechanicList = null;
    }

    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onBlockPistonRetract(event);
        }
        mechanicList = null;
    }

    public void onSignChange(SignChangeEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onSignChange(event);
        }
        mechanicList = null;
    }

    public void onPlayerInteract(PlayerInteractEvent event, boolean isWallSign, boolean isSignPost) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onPlayerInteract(event, isWallSign, isSignPost);
        }
        mechanicList = null;
    }

    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onPlayerPreLogin(event);
        }
        mechanicList = null;
    }

    public void onPlayerLogin(PlayerLoginEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onPlayerLogin(event);
        }
        mechanicList = null;
    }

    public void onPlayerQuit(PlayerQuitEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onPlayerQuit(event);
        }
        mechanicList = null;
    }

    public void onPlayerTeleport(PlayerTeleportEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onPlayerTeleport(event);
        }
        mechanicList = null;
    }

    public void onPlayerRespawn(PlayerRespawnEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onPlayerRespawn(event);
        }
        mechanicList = null;
    }

    public void onEntityExplode(EntityExplodeEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onEntityExplode(event);
        }
        mechanicList = null;
    }

    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        List<MechanicListener> mechanicList = this.registeredEvents.get(event.getClass());
        if (mechanicList == null) {
            return;
        }
        for (MechanicListener listener : mechanicList) {
            listener.onEntityChangeBlock(event);
        }
        mechanicList = null;
    }
}