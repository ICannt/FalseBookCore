package com.bukkit.gemo.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FBBlockType {

    private int ItemID;
    private short ItemData = 0;
    private byte ItemDamage;

    private int getMaterialIDByName(String name) {
        for (Material m : Material.values()) {
            if (m.name().equalsIgnoreCase(name)) {
                return m.getId();
            }
        }
        return 0;
    }

    public FBBlockType(String name) {
        this.ItemID = getMaterialIDByName(name);
        this.ItemData = 0;
        this.ItemDamage = 0;
    }

    public FBBlockType(String name, short Data) {
        this.ItemID = getMaterialIDByName(name);
        this.ItemData = Data;
        this.ItemDamage = 0;
    }

    public FBBlockType(int ID) {
        this.ItemID = ID;
        this.ItemData = 0;
        this.ItemDamage = 0;
    }

    public FBBlockType(int ID, short Data) {
        this.ItemID = ID;
        this.ItemData = Data;
        this.ItemDamage = 0;
    }

    public int getItemID() {
        return this.ItemID;
    }

    public void setItemID(int itemID) {
        this.ItemID = itemID;
    }

    public void setItemID(String name) {
        this.ItemID = getMaterialIDByName(name);
    }

    public short getItemData() {
        return this.ItemData;
    }

    public byte getItemDataAsByte() {
        return Byte.valueOf(String.valueOf(this.ItemData)).byteValue();
    }

    public void setItemData(short itemData) {
        this.ItemData = itemData;
    }

    public byte getItemDamage() {
        return this.ItemDamage;
    }

    public boolean equals(FBBlockType other) {
        return (getItemID() == other.getItemID()) && (getItemData() == other.getItemData());
    }

    public boolean equals(ItemStack other) {
        if (other == null) {
            return false;
        }
        return (getItemID() == other.getTypeId()) && (getItemData() == other.getDurability());
    }
}