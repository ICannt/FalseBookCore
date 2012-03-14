package com.bukkit.gemo.utils;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ICUtils {

    public static void switchLever(Sign signBlock, boolean isOn) {
        switchLever(signBlock, isOn, 2);
    }

    public static void switchLever(Sign signBlock, boolean isOn, int distance) {
        if (getLeverPos(signBlock, distance).getBlock().getTypeId() != Material.LEVER.getId()) {
            return;
        }
        if (getLeverPos(signBlock, distance).getBlock().getType().equals(Material.LEVER)) {
            byte data = getLeverPos(signBlock, distance).getBlock().getData();

            int old = 0;
            if ((isOn) && (data < 8)) {
                data = (byte) (data | 0x8);
            } else if ((!isOn) && (data > 7)) {
                old = 15;
                data = (byte) (data ^ 0x8);
            }

            Location loc = getLeverPos(signBlock, distance);
            setBlockToLever(signBlock, loc, data);
            sendRedstoneUpdate(loc, old, old == 15 ? 0 : 15);
        }
    }

    public static void switchLeverLeft(Sign signBlock, boolean isOn) {
        switchLeverLeft(signBlock, isOn, 1);
    }

    public static void switchLeverLeft(Sign signBlock, boolean isOn, int distance) {
        if (getLeverPosLeft(signBlock, distance).getBlock().getTypeId() != Material.LEVER.getId()) {
            return;
        }
        if (getLeverPosLeft(signBlock, distance).getBlock().getType().equals(Material.LEVER)) {
            byte data = getLeverPosLeft(signBlock, distance).getBlock().getData();

            int old = 0;
            if ((isOn) && (data < 8)) {
                data = (byte) (data | 0x8);
            } else if ((!isOn) && (data > 7)) {
                old = 15;
                data = (byte) (data ^ 0x8);
            }

            Location loc = getLeverPosLeft(signBlock, distance);
            setBlockToLever(signBlock, loc, data);
            sendRedstoneUpdate(loc, old, data);
        }
    }

    public static void switchLeverRight(Sign signBlock, boolean isOn) {
        switchLeverRight(signBlock, isOn, 1);
    }

    public static void switchLeverRight(Sign signBlock, boolean isOn, int distance) {
        if (getLeverPosRight(signBlock, distance).getBlock().getTypeId() != Material.LEVER.getId()) {
            return;
        }
        if (getLeverPosRight(signBlock, distance).getBlock().getType().equals(Material.LEVER)) {
            byte data = getLeverPosRight(signBlock, distance).getBlock().getData();

            int old = 0;
            if ((isOn) && (data < 8)) {
                data = (byte) (data | 0x8);
            } else if ((!isOn) && (data > 7)) {
                old = 15;
                data = (byte) (data ^ 0x8);
            }

            Location loc = getLeverPosRight(signBlock, distance);
            setBlockToLever(signBlock, loc, data);
            sendRedstoneUpdate(loc, old, data);
        }
    }

    public static Location getLeverPos(Sign signBlock) {
        return getLeverPos(signBlock, 2);
    }

    public static Location getLeverPos(Sign signBlock, int distance) {
        Location leverPos = signBlock.getBlock().getLocation().clone();
        if (signBlock.getRawData() == 2) {
            leverPos.setZ(leverPos.getZ() + distance);
        } else if (signBlock.getRawData() == 3) {
            leverPos.setZ(leverPos.getZ() - distance);
        } else if (signBlock.getRawData() == 4) {
            leverPos.setX(leverPos.getX() + distance);
        } else if (signBlock.getRawData() == 5) {
            leverPos.setX(leverPos.getX() - distance);
        }
        return leverPos;
    }

    public static Location getLeverPosLeft(Sign signBlock) {
        return getLeverPosLeft(signBlock, 1);
    }

    public static Location getLeverPosLeft(Sign signBlock, int distance) {
        Location leverPos = signBlock.getBlock().getLocation().clone();
        if (signBlock.getRawData() == 2) {
            leverPos.setZ(leverPos.getZ() + distance);
            leverPos.setX(leverPos.getX() + 1.0D);
        } else if (signBlock.getRawData() == 3) {
            leverPos.setZ(leverPos.getZ() - distance);
            leverPos.setX(leverPos.getX() - 1.0D);
        } else if (signBlock.getRawData() == 4) {
            leverPos.setX(leverPos.getX() + distance);
            leverPos.setZ(leverPos.getZ() - 1.0D);
        } else if (signBlock.getRawData() == 5) {
            leverPos.setX(leverPos.getX() - distance);
            leverPos.setZ(leverPos.getZ() + 1.0D);
        }
        return leverPos;
    }

    public static Location getLeverPosRight(Sign signBlock) {
        return getLeverPosRight(signBlock, 1);
    }

    public static Location getLeverPosRight(Sign signBlock, int distance) {
        Location leverPos = signBlock.getBlock().getLocation().clone();
        if (signBlock.getRawData() == 2) {
            leverPos.setZ(leverPos.getZ() + distance);
            leverPos.setX(leverPos.getX() - 1.0D);
        } else if (signBlock.getRawData() == 3) {
            leverPos.setZ(leverPos.getZ() - distance);
            leverPos.setX(leverPos.getX() + 1.0D);
        } else if (signBlock.getRawData() == 4) {
            leverPos.setX(leverPos.getX() + distance);
            leverPos.setZ(leverPos.getZ() + 1.0D);
        } else if (signBlock.getRawData() == 5) {
            leverPos.setX(leverPos.getX() - distance);
            leverPos.setZ(leverPos.getZ() - 1.0D);
        }
        return leverPos;
    }

    public static boolean isLeverActive(Sign signBlock) {
        return isLeverActive(signBlock, 2);
    }

    public static boolean isLeverActive(Sign signBlock, int distance) {
        Block leverBlock = getLeverPos(signBlock, distance).getBlock();
        if (leverBlock.getTypeId() != Material.LEVER.getId()) {
            return false;
        }
        return leverBlock.getData() >= 8;
    }

    public static boolean isLeftLeverActive(Sign signBlock) {
        return isLeftLeverActive(signBlock, 1);
    }

    public static boolean isLeftLeverActive(Sign signBlock, int distance) {
        Block leverBlock = getLeverPosLeft(signBlock, distance).getBlock();
        if (leverBlock.getTypeId() != Material.LEVER.getId()) {
            return false;
        }
        return leverBlock.getData() >= 8;
    }

    public static boolean isRightLeverActive(Sign signBlock) {
        return isRightLeverActive(signBlock, 1);
    }

    public static boolean isRightLeverActive(Sign signBlock, int distance) {
        Block leverBlock = getLeverPosRight(signBlock, distance).getBlock();
        if (leverBlock.getTypeId() != Material.LEVER.getId()) {
            return false;
        }
        return leverBlock.getData() >= 8;
    }

    public static void dropItemOnNextFreeBlockAbove(Location loc, ItemStack itemstack, int maxDistance) {
        if ((itemstack == null) || (loc == null) || (maxDistance < 1)) {
            return;
        }
        int maxY = Math.min(loc.getWorld().getMaxHeight() - 1, loc.getBlockY() + maxDistance);
        World world = loc.getWorld();
        for (int y = loc.getBlockY() + 1; y <= maxY; y++) {
            if (BlockUtils.canPassThrough(world.getBlockAt(loc.getBlockX(), y, loc.getBlockZ()).getTypeId())) {
                Location sPos = loc.clone();
                sPos.setX(sPos.getX() + 0.5D);
                sPos.setZ(sPos.getZ() + 0.5D);
                if (loc.getBlock().getTypeId() != Material.STEP.getId()) {
                    sPos.setY(y + 0.3D);
                } else {
                    sPos.setY(y - 0.2D);
                }
                Item item = world.dropItem(sPos, itemstack);
                item.setVelocity(new Vector(0, 0, 0));
                world = null;
                return;
            }
        }
        world = null;
    }

    public static void dropItemOnNextFreeBlockBelow(Location loc, ItemStack itemstack, int maxDistance) {
        if ((itemstack == null) || (loc == null) || (maxDistance < 1)) {
            return;
        }
        int minY = Math.max(1, loc.getBlockY() - maxDistance);
        World world = loc.getWorld();
        for (int y = loc.getBlockY() - 1; y >= minY; y--) {
            if (BlockUtils.canPassThrough(world.getBlockAt(loc.getBlockX(), y, loc.getBlockZ()).getTypeId())) {
                Location sPos = loc.clone();
                sPos.setX(sPos.getX() + 0.5D);
                sPos.setZ(sPos.getZ() + 0.5D);
                if (loc.getBlock().getTypeId() != Material.STEP.getId()) {
                    sPos.setY(y + 0.3D);
                } else {
                    sPos.setY(y - 0.2D);
                }
                Item item = world.dropItem(sPos, itemstack);
                item.setVelocity(new Vector(0, 0, 0));
                world = null;
                return;
            }
        }
        world = null;
    }

    private static void sendRedstoneUpdate(Location loc, int oldCurrent, int newCurrent) {
        BlockRedstoneEvent event = new BlockRedstoneEvent(loc.getBlock(), oldCurrent, newCurrent);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    private static void setBlockToLever(Sign signBlock, Location loc, byte data) {
        Block leverAnchor = BlockUtils.getLeverAnchor(loc, data);
        CraftWorld cWorld = (CraftWorld) signBlock.getWorld();
        cWorld.getHandle().setTypeIdAndData(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), Material.LEVER.getId(), data);
        cWorld.getHandle().applyPhysics(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getBlock().getTypeId());
        cWorld.getHandle().applyPhysics(leverAnchor.getX(), leverAnchor.getY(), leverAnchor.getZ(), loc.getBlock().getTypeId());
    }

    public static ArrayList<Location> getBlockPositions(Sign signBlock) {
        Location input1 = signBlock.getBlock().getLocation().clone();
        Location input2 = signBlock.getBlock().getLocation().clone();
        Location input3 = signBlock.getBlock().getLocation().clone();

        ArrayList<Location> result = new ArrayList<Location>();
        if (signBlock.getRawData() == 2) {
            input1.setZ(input1.getZ() - 1.0D);
            input3.setX(input3.getX() - 1.0D);
            input2.setX(input2.getX() + 1.0D);
        } else if (signBlock.getRawData() == 3) {
            input1.setZ(input1.getZ() + 1.0D);
            input3.setX(input3.getX() + 1.0D);
            input2.setX(input2.getX() - 1.0D);
        } else if (signBlock.getRawData() == 4) {
            input1.setX(input1.getX() - 1.0D);
            input3.setZ(input3.getZ() + 1.0D);
            input2.setZ(input2.getZ() - 1.0D);
        } else if (signBlock.getRawData() == 5) {
            input1.setX(input1.getX() + 1.0D);
            input3.setZ(input3.getZ() - 1.0D);
            input2.setZ(input2.getZ() + 1.0D);
        }
        result.add(input1);
        result.add(input2);
        result.add(input3);
        return result;
    }

    private static ArrayList<Location> getBlockPositions(Sign signBlock, boolean hasInput1, boolean hasInput2, boolean hasInput3) {
        if (hasInput1) {
            Location input1 = signBlock.getBlock().getLocation().clone();
            ArrayList<Location> result = new ArrayList<Location>();
            if (signBlock.getRawData() == 2) {
                input1.setZ(input1.getZ() - 1.0D);
            } else if (signBlock.getRawData() == 3) {
                input1.setZ(input1.getZ() + 1.0D);
            } else if (signBlock.getRawData() == 4) {
                input1.setX(input1.getX() - 1.0D);
            } else if (signBlock.getRawData() == 5) {
                input1.setX(input1.getX() + 1.0D);
            }
            result.add(input1);
            return result;
        }
        if (hasInput2) {
            Location input2 = signBlock.getBlock().getLocation().clone();
            ArrayList<Location> result = new ArrayList<Location>();
            if (signBlock.getRawData() == 2) {
                input2.setX(input2.getX() + 1.0D);
            } else if (signBlock.getRawData() == 3) {
                input2.setX(input2.getX() - 1.0D);
            } else if (signBlock.getRawData() == 4) {
                input2.setZ(input2.getZ() - 1.0D);
            } else if (signBlock.getRawData() == 5) {
                input2.setZ(input2.getZ() + 1.0D);
            }
            result.add(input2);
            return result;
        }
        if (hasInput3) {
            Location input3 = signBlock.getBlock().getLocation().clone();
            ArrayList<Location> result = new ArrayList<Location>();
            if (signBlock.getRawData() == 2) {
                input3.setX(input3.getX() - 1.0D);
            } else if (signBlock.getRawData() == 3) {
                input3.setX(input3.getX() + 1.0D);
            } else if (signBlock.getRawData() == 4) {
                input3.setZ(input3.getZ() + 1.0D);
            } else if (signBlock.getRawData() == 5) {
                input3.setZ(input3.getZ() - 1.0D);
            }
            result.add(input3);
            return result;
        }
        return new ArrayList<Location>();
    }

    public static boolean isInputHigh(Sign signBlock, int ID) {
        if ((ID < 1) || (ID > 3)) {
            return false;
        }
        ArrayList<Location> inputs = getBlockPositions(signBlock, ID == 1, ID == 2, ID == 3);
        if (inputs.size() < 1) {
            return false;
        }
        return BlockUtils.isPowered(inputs.get(0));
    }

    public static boolean isInputLow(Sign signBlock, int ID) {
        if ((ID < 1) || (ID > 3)) {
            return false;
        }
        ArrayList<Location> inputs = getBlockPositions(signBlock, ID == 1, ID == 2, ID == 3);
        if (inputs.size() < 1) {
            return false;
        }
        return BlockUtils.isLow(inputs.get(0));
    }
}