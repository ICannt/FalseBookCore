package com.bukkit.gemo.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FBItemType
{
  private boolean usesWildcart = false;
  private int ItemID;
  private short ItemData = 0;
  private int amount = -1;

  public String getString() {
    return this.ItemID + ":" + (this.usesWildcart ? "true" : Short.valueOf(this.ItemData));
  }

  private int getMaterialIDByName(String name) {
    for (Material m : Material.values())
      if (m.name().equalsIgnoreCase(name))
        return m.getId();
    return 0;
  }

  public FBItemType(String name) {
    this.ItemID = getMaterialIDByName(name);
    this.ItemData = 0;
    this.usesWildcart = true;
  }

  public FBItemType(String name, short Data) {
    this.ItemID = getMaterialIDByName(name);
    this.ItemData = Data;
    this.usesWildcart = false;
  }

  public FBItemType(int ID) {
    this.ItemID = ID;
    this.ItemData = 0;
    this.usesWildcart = true;
  }

  public FBItemType(int ID, short Data) {
    this.ItemID = ID;
    this.ItemData = Data;
    this.usesWildcart = false;
  }

  public int getItemID() {
    return this.ItemID;
  }

  public boolean usesWildcart() {
    return this.usesWildcart;
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

  public int getAmount() {
    return this.amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public boolean equals(FBItemType other) {
    return (getItemID() == other.getItemID()) && ((this.usesWildcart) || (getItemData() == other.getItemData()));
  }

  public boolean equals(ItemStack other) {
    if (other == null)
      return false;
    return (getItemID() == other.getTypeId()) && ((this.usesWildcart) || (getItemData() == other.getDurability()));
  }

  public ItemStack getItemStack() {
    if (this.amount > 0) {
      ItemStack itemStack = new ItemStack(getItemID());
      itemStack.setAmount(this.amount);
      if (!this.usesWildcart)
        itemStack.setDurability(this.ItemData);
      return itemStack;
    }
    return null;
  }
}