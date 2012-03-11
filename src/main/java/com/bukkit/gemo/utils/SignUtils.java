package com.bukkit.gemo.utils;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

public class SignUtils
{
  public static void addAdjacentWallSigns(ArrayList<Sign> list, Block baseBlock)
  {
    if ((baseBlock.getRelative(1, 0, 0).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(1, 0, 0).getData() == 5)) {
      list.add((Sign)baseBlock.getRelative(1, 0, 0).getState());
    }
    if ((baseBlock.getRelative(-1, 0, 0).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(-1, 0, 0).getData() == 4)) {
      list.add((Sign)baseBlock.getRelative(-1, 0, 0).getState());
    }
    if ((baseBlock.getRelative(0, 0, 1).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(0, 0, 1).getData() == 3)) {
      list.add((Sign)baseBlock.getRelative(0, 0, 1).getState());
    }
    if ((baseBlock.getRelative(0, 0, -1).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(0, 0, -1).getData() == 2))
      list.add((Sign)baseBlock.getRelative(0, 0, -1).getState());
  }

  public static ArrayList<Sign> getAdjacentWallSigns(Block baseBlock)
  {
    ArrayList<Sign> list = new ArrayList<Sign>();
    if ((baseBlock.getRelative(1, 0, 0).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(1, 0, 0).getData() == 5)) {
      list.add((Sign)baseBlock.getRelative(1, 0, 0).getState());
    }
    if ((baseBlock.getRelative(-1, 0, 0).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(-1, 0, 0).getData() == 4)) {
      list.add((Sign)baseBlock.getRelative(-1, 0, 0).getState());
    }
    if ((baseBlock.getRelative(0, 0, 1).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(0, 0, 1).getData() == 3)) {
      list.add((Sign)baseBlock.getRelative(0, 0, 1).getState());
    }
    if ((baseBlock.getRelative(0, 0, -1).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(0, 0, -1).getData() == 2)) {
      list.add((Sign)baseBlock.getRelative(0, 0, -1).getState());
    }
    return list;
  }

  public static boolean isSignAnchor(Block baseBlock)
  {
    if ((baseBlock.getRelative(1, 0, 0).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(1, 0, 0).getData() == 5))
      return true;
    if ((baseBlock.getRelative(-1, 0, 0).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(-1, 0, 0).getData() == 4)) {
      return true;
    }
    if ((baseBlock.getRelative(0, 0, 1).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(0, 0, 1).getData() == 3)) {
      return true;
    }

    return (baseBlock.getRelative(0, 0, -1).getTypeId() == Material.WALL_SIGN.getId()) && (baseBlock.getRelative(0, 0, -1).getData() == 2);
  }

  public static Location getSignAnchor(Sign signBlock)
  {
    Location leverPos = signBlock.getBlock().getLocation().clone();

    if (signBlock.getTypeId() == Material.WALL_SIGN.getId())
      switch (signBlock.getRawData())
      {
      case 2:
        leverPos.setZ(leverPos.getZ() + 1.0D);
        break;
      case 3:
        leverPos.setZ(leverPos.getZ() - 1.0D);
        break;
      case 4:
        leverPos.setX(leverPos.getX() + 1.0D);
        break;
      case 5:
        leverPos.setX(leverPos.getX() - 1.0D);
      default:
        break;
      }
    else if (signBlock.getTypeId() == Material.SIGN_POST.getId()) {
      switch (signBlock.getRawData())
      {
      case 8:
        leverPos.setZ(leverPos.getZ() + 1.0D);
        break;
      case 0:
        leverPos.setZ(leverPos.getZ() - 1.0D);
        break;
      case 4:
        leverPos.setX(leverPos.getX() + 1.0D);
        break;
      case 12:
        leverPos.setX(leverPos.getX() - 1.0D);
      }

    }

    return leverPos;
  }

  public static int getDirection(Sign signBlock)
  {
    if (signBlock.getTypeId() == Material.WALL_SIGN.getId()) {
      if (signBlock.getRawData() == 2)
      {
        return 3;
      }if (signBlock.getRawData() == 3)
      {
        return 1;
      }if (signBlock.getRawData() == 4)
      {
        return 4;
      }if (signBlock.getRawData() == 5)
      {
        return 2;
      }
      return -1;
    }if (signBlock.getTypeId() == Material.SIGN_POST.getId()) {
      if (signBlock.getRawData() == 8)
      {
        return 3;
      }if (signBlock.getRawData() == 0)
      {
        return 1;
      }if (signBlock.getRawData() == 4)
      {
        return 4;
      }if (signBlock.getRawData() == 12)
      {
        return 2;
      }
      return -1;
    }
    return -1;
  }

  public static void cancelSignCreation(SignChangeEvent event, String reason)
  {
    event.setCancelled(true);
    event.getBlock().setType(Material.AIR);
    ItemStack item = new ItemStack(Material.SIGN, 1);
    event.getPlayer().getInventory().addItem(new ItemStack[] { item });
    ChatUtils.printError(event.getPlayer(), "", reason);
  }

  public static void cancelSignCreation(SignChangeEvent event)
  {
    event.setCancelled(true);
    event.getBlock().setType(Material.AIR);
    ItemStack item = new ItemStack(Material.SIGN, 1);
    event.getPlayer().getInventory().addItem(new ItemStack[] { item });
  }

  public static ArrayList<FBBlockType> parseItems(String[] lines, String delimiter, boolean allowAir)
  {
    ArrayList<FBBlockType> list = new ArrayList<FBBlockType>();

    for (int l = 0; l < lines.length; l++) {
      lines[l] = lines[l].trim();
      if (lines[l].equalsIgnoreCase("all")) {
        for (int i = allowAir ? 0 : 1; i < Material.values().length; i++) {
          if ((Material.values()[i].getId() == Material.LOG.getId()) || (Material.values()[i].getId() == Material.LEAVES.getId())) {
            for (int j = 0; j < 3; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if ((Material.values()[i].getId() == Material.WOOL.getId()) || (Material.values()[i].getId() == Material.INK_SACK.getId())) {
            for (int j = 0; j < 16; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if (Material.values()[i].getId() == Material.SAPLING.getId()) {
            for (int j = 0; j < 4; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if ((Material.values()[i].getId() == Material.STEP.getId()) || (Material.values()[i].getId() == Material.DOUBLE_STEP.getId())) {
            for (int j = 0; j < 6; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if (Material.values()[i].getId() == Material.COAL.getId()) {
            for (int j = 0; j < 2; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if ((Material.values()[i].getId() == Material.WOOD_HOE.getId()) || (Material.values()[i].getId() == Material.WOOD_SPADE.getId()) || (Material.values()[i].getId() == Material.WOOD_PICKAXE.getId()) || (Material.values()[i].getId() == Material.WOOD_AXE.getId()) || (Material.values()[i].getId() == Material.WOOD_SWORD.getId()))
          {
            for (int j = 0; j < 60; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if ((Material.values()[i].getId() == Material.STONE_HOE.getId()) || (Material.values()[i].getId() == Material.STONE_SPADE.getId()) || (Material.values()[i].getId() == Material.STONE_PICKAXE.getId()) || (Material.values()[i].getId() == Material.STONE_AXE.getId()) || (Material.values()[i].getId() == Material.STONE_SWORD.getId()))
          {
            for (int j = 0; j < 132; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if ((Material.values()[i].getId() == Material.IRON_HOE.getId()) || (Material.values()[i].getId() == Material.IRON_SPADE.getId()) || (Material.values()[i].getId() == Material.IRON_PICKAXE.getId()) || (Material.values()[i].getId() == Material.IRON_AXE.getId()) || (Material.values()[i].getId() == Material.IRON_SWORD.getId()))
          {
            for (int j = 0; j < 251; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if ((Material.values()[i].getId() == Material.GOLD_HOE.getId()) || (Material.values()[i].getId() == Material.GOLD_SPADE.getId()) || (Material.values()[i].getId() == Material.GOLD_PICKAXE.getId()) || (Material.values()[i].getId() == Material.GOLD_AXE.getId()) || (Material.values()[i].getId() == Material.GOLD_SWORD.getId()))
          {
            for (int j = 0; j < 33; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else if ((Material.values()[i].getId() == Material.DIAMOND_HOE.getId()) || (Material.values()[i].getId() == Material.DIAMOND_SPADE.getId()) || (Material.values()[i].getId() == Material.DIAMOND_PICKAXE.getId()) || (Material.values()[i].getId() == Material.DIAMOND_AXE.getId()) || (Material.values()[i].getId() == Material.DIAMOND_SWORD.getId()))
          {
            for (int j = 0; j < 1562; j++)
              list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
          else {
            list.add(new FBBlockType(Material.values()[i].getId(), (short) 0));
          }
        }
        return list;
      }
      String[] split = lines[l].split(delimiter);
      for (int i = 0; i < split.length; i++) {
        String[] itemSplit = split[i].split(":");
        if (itemSplit.length == 1)
        {
          try
          {
            list.add(new FBBlockType(Integer.valueOf(itemSplit[0]).intValue(), (short) 0));
          }
          catch (Exception e) {
            for (int j = allowAir ? 0 : 1; j < Material.values().length; j++)
              if (Material.values()[j].name().equalsIgnoreCase(itemSplit[0]))
                list.add(new FBBlockType(Material.values()[j].getId(), (short) 0));
          }
        }
        else {
          if (itemSplit.length != 2)
            continue;
          try {
            list.add(new FBBlockType(Integer.valueOf(itemSplit[0]).intValue(), Byte.valueOf(itemSplit[1]).byteValue()));
          }
          catch (Exception e) {
            for (int j = allowAir ? 0 : 1; j < Material.values().length; j++) {
              if (!Material.values()[j].name().equalsIgnoreCase(itemSplit[0])) continue;
              try {
                list.add(new FBBlockType(Material.values()[j].getId(), Byte.valueOf(itemSplit[1]).byteValue()));
              } catch (Exception e2) {
                return list;
              }
            }
          }
        }
      }
    }

    return list;
  }

  public static ArrayList<FBItemType> parseLineToItemListWithSize(String line, String delimiter, boolean allowAir, int minSize, int maxSize)
  {
    ArrayList<FBItemType> list = parseLineToItemList(line, delimiter, allowAir);
    if ((list != null) && (list.size() >= minSize) && (list.size() <= maxSize)) {
      return list;
    }
    return null;
  }

  public static ArrayList<FBItemType> parseLineToItemList(String line, String delimiter, boolean allowAir)
  {
    ArrayList<FBItemType> list = new ArrayList<FBItemType>();
    line = line.trim().toLowerCase();

    if (line.equalsIgnoreCase("empty")) {
      return null;
    }

    if (line.equalsIgnoreCase("all")) {
      for (int i = allowAir ? 0 : 1; i < Material.values().length; i++)
        list.add(new FBItemType(Material.values()[i].getId()));
    }
    else
    {
      String[] split = line.split(delimiter);
      String[] itemSplit = (String[])null;
      for (String item : split)
      {
        String[] splitAmount = item.split("\\*");
        int amount = -1;
        if (splitAmount.length > 1) {
          if (!Parser.isInteger(splitAmount[0])) {
            String testString = splitAmount[0].substring(0, splitAmount[0].length() - 1);
            if (!splitAmount[0].endsWith("s")) {
              continue;
            }
            if (!Parser.isInteger(testString)) {
              continue;
            }
            splitAmount[0] = ((Integer)(64 * Parser.getInteger(testString, 1))).toString();
          }
          amount = Parser.getInteger(splitAmount[0], -1);
          item = splitAmount[1];
        }

        if (amount < -1) {
          amount = -1;
        }
        String[] rangeSplit = item.split("\\?");
        if (rangeSplit.length == 1) {
          itemSplit = rangeSplit[0].split(":");

          if (itemSplit.length == 1) {
            if (Parser.isInteger(itemSplit[0])) {
              FBItemType thisItem = new FBItemType(Parser.getInteger(itemSplit[0], 0));
              thisItem.setAmount(amount);
              list.add(thisItem);
            } else {
              FBItemType thisItem = new FBItemType(itemSplit[0]);
              thisItem.setAmount(amount);
              if (thisItem.getItemID() != 0)
                list.add(thisItem);
            }
          }
          else if (itemSplit.length == 2) {
            if ((Parser.isInteger(itemSplit[0])) && (Parser.isInteger(itemSplit[1]))) {
              FBItemType thisItem = new FBItemType(Parser.getInteger(itemSplit[0], 0), Short.valueOf(itemSplit[1]).shortValue());
              thisItem.setAmount(amount);
              list.add(thisItem);
            }
            else if (Parser.isInteger(itemSplit[1])) {
              FBItemType thisItem = new FBItemType(itemSplit[0], Short.valueOf(itemSplit[1]).shortValue());
              thisItem.setAmount(amount);

              if (thisItem.getItemID() != 0) {
                list.add(thisItem);
              }
            }
          }
        }
        else if (rangeSplit.length == 2) {
          int firstID = -1;
          int secondID = -1;
          itemSplit = rangeSplit[0].split(":");
          if (Parser.isInteger(itemSplit[0])) {
            firstID = Parser.getInteger(itemSplit[0], -1);
          } else {
            Material mat = Material.getMaterial(itemSplit[0]);
            if (mat != null)
              firstID = mat.getId();
          }
          itemSplit = rangeSplit[1].split(":");
          if (Parser.isInteger(itemSplit[0])) {
            secondID = Parser.getInteger(itemSplit[0], -1);
          } else {
            Material mat = Material.getMaterial(itemSplit[0]);
            if (mat != null)
              secondID = mat.getId();
          }
          if ((firstID != -1) && (secondID != -1) && (firstID < secondID)) {
            Material mat = null;
            for (int i = firstID; i <= secondID; i++) {
              mat = Material.getMaterial(i);
              if (mat != null) {
                FBItemType thisItem = new FBItemType(i);
                thisItem.setAmount(amount);
                if (thisItem.getItemID() != 0) {
                  list.add(thisItem);
                }
              }
            }
          }
        }
      }
    }
    return list;
  }

  public static ArrayList<FBItemType> parseLinesToItemList(String[] lines, String delimiter, boolean allowAir)
  {
    ArrayList<FBItemType> list = new ArrayList<FBItemType>();
    ArrayList<FBItemType> thisList = null;
    String[] arrayOfString = lines; int j = lines.length; for (int i = 0; i < j; i++) { String line = arrayOfString[i];
      thisList = parseLineToItemList(line, delimiter, allowAir);
      if (thisList != null)
        list.addAll(thisList);
    }
    return list;
  }

  public static ArrayList<FBBlockType> parseItems(String line, String delimiter, boolean allowAir)
  {
    ArrayList<FBBlockType> list = new ArrayList<FBBlockType>();
    if (line.equalsIgnoreCase("empty")) {
      return null;
    }
    line = line.trim();
    if (line.equalsIgnoreCase("all")) {
      for (int i = allowAir ? 0 : 1; i < Material.values().length; i++) {
        if ((Material.values()[i].getId() != Material.COAL.getId()) && (Material.values()[i].getId() != Material.SAPLING.getId()) && (Material.values()[i].getId() != Material.LEAVES.getId()) && (Material.values()[i].getId() != Material.LOG.getId()) && (Material.values()[i].getId() != Material.WOOL.getId()) && (Material.values()[i].getId() != Material.STEP.getId()))
          list.add(new FBBlockType(Material.values()[i].getId(), (short) 0));
        else if ((Material.values()[i].getId() == Material.LOG.getId()) || (Material.values()[i].getId() == Material.LEAVES.getId())) {
          for (int j = 0; j < 3; j++)
            list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
        }
        else if ((Material.values()[i].getId() == Material.WOOL.getId()) || (Material.values()[i].getId() == Material.INK_SACK.getId())) {
          for (int j = 0; j < 16; j++)
            list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
        }
        else if ((Material.values()[i].getId() == Material.STEP.getId()) || (Material.values()[i].getId() == Material.SAPLING.getId())) {
          for (int j = 0; j < 4; j++)
            list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
        }
        else if (Material.values()[i].getId() == Material.COAL.getId()) {
          for (int j = 0; j < 2; j++) {
            list.add(new FBBlockType(Material.values()[i].getId(), (byte)j));
          }
        }
      }
      return list;
    }
    String[] split = line.split(delimiter);
    for (int i = 0; i < split.length; i++) {
      String[] itemSplit = split[i].split(":");
      if (itemSplit.length == 1)
      {
        try
        {
          list.add(new FBBlockType(Integer.valueOf(itemSplit[0]).intValue(), (short) 0));
        }
        catch (Exception e) {
          for (int j = allowAir ? 0 : 1; j < Material.values().length; j++)
            if (Material.values()[j].name().equalsIgnoreCase(itemSplit[0]))
              list.add(new FBBlockType(Material.values()[j].getId(), (short) 0));
        }
      }
      else {
        if (itemSplit.length != 2)
          continue;
        try {
          list.add(new FBBlockType(Integer.valueOf(itemSplit[0]).intValue(), Byte.valueOf(itemSplit[1]).byteValue()));
        }
        catch (Exception e) {
          for (int j = allowAir ? 0 : 1; j < Material.values().length; j++) {
            if (!Material.values()[j].name().equalsIgnoreCase(itemSplit[0])) continue;
            try {
              list.add(new FBBlockType(Material.values()[j].getId(), Byte.valueOf(itemSplit[1]).byteValue()));
            } catch (Exception e2) {
              return list;
            }
          }
        }
      }
    }

    return list;
  }
}