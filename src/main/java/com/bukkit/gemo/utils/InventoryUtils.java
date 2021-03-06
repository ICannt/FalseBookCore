package com.bukkit.gemo.utils;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    public static boolean isInventoryEmpty(Inventory inventory) {
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item != null) && (item.getTypeId() != Material.AIR.getId())) {
                return false;
            }
        }
        return true;
    }

    public static int countItemInInventory(Inventory inventory, ItemStack itemStack) {
        int count = 0;
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item == null) || (item.getTypeId() == Material.AIR.getId())) {
                continue;
            }
            if ((item.getTypeId() == itemStack.getTypeId()) && (item.getDurability() == itemStack.getDurability()) && (item.getEnchantments().equals(itemStack.getEnchantments()))) {
                count += item.getAmount();
            }
        }
        return count;
    }

    public static int countItemInInventory(Inventory inventory, FBItemType itemStack) {
        return countItemInInventory(inventory, itemStack.getItemID(), itemStack.getItemData(), itemStack.usesWildcart());
    }

    public static int countItemInInventory(Inventory inventory, int ID, short SubID, boolean useWildcart) {
        int count = 0;
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item == null) || (item.getTypeId() == Material.AIR.getId())) {
                continue;
            }
            if ((item.getTypeId() == ID) && ((useWildcart) || (item.getDurability() == SubID))) {
                count += item.getAmount();
            }
        }
        return count;
    }

    public static int countItemInSlot(Inventory inventory, ItemStack itemStack, int slot) {
        return countItemInSlot(inventory, itemStack.getTypeId(), itemStack.getDurability(), slot);
    }

    public static int countItemInSlot(Inventory inventory, FBItemType itemStack, int slot) {
        return countItemInSlot(inventory, itemStack.getItemID(), itemStack.getItemData(), slot);
    }

    public static int countItemInSlot(Inventory inventory, int ID, short SubID, int slot) {
        ItemStack item = null;
        item = inventory.getItem(slot);
        if (item == null) {
            return 0;
        }
        if ((item.getTypeId() == ID) && (item.getDurability() == SubID)) {
            return item.getAmount();
        }
        return 0;
    }

    public static int removeItemInInventory(Inventory inventory, ItemStack itemStack) {
        return removeItemInInventory(inventory, itemStack.getTypeId(), itemStack.getDurability());
    }

    public static int removeItemInInventory(Inventory inventory, FBBlockType itemStack) {
        return removeItemInInventory(inventory, itemStack.getItemID(), itemStack.getItemData());
    }

    public static int removeItemInInventory(Inventory inventory, int ID, short SubID) {
        int removed = 0;
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item == null) || (item.getTypeId() == Material.AIR.getId())) {
                continue;
            }
            if ((item.getTypeId() == ID) && (item.getDurability() == SubID)) {
                removed += item.getAmount();
                inventory.setItem(i, null);
            }
        }
        return removed;
    }

    public static int removeItemAmountInInventory(Inventory inventory, FBBlockType itemStack, int amount) {
        return removeItemAmountInInventory(inventory, itemStack.getItemID(), itemStack.getItemData(), amount);
    }

    public static int removeItemAmountInInventory(Inventory inventory, int ID, short SubID, int amount) {
        if (amount < 1) {
            return 0;
        }
        int removed = 0;
        int restAmount = amount;
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item == null) || (item.getTypeId() == Material.AIR.getId())) {
                continue;
            }
            if ((item.getTypeId() != ID) || (item.getDurability() != SubID)) {
                continue;
            }
            if (restAmount >= item.getAmount()) {
                removed += item.getAmount();
                restAmount -= item.getAmount();
                inventory.setItem(i, null);
            } else {
                int itemAmount = item.getAmount();
                itemAmount -= restAmount;
                removed += restAmount;
                item.setAmount(itemAmount);
                restAmount = 0;
            }
            if (restAmount < 1) {
                break;
            }
        }
        return removed;
    }

    public static ItemStack addItemIntoSlot(Inventory inventory, int slot, ItemStack item) {
        ItemStack itemInSlot = inventory.getItem(slot);
        int maxStackSize = BlockUtils.getMaxStackSize(item.getTypeId());
        if ((itemInSlot != null) && (itemInSlot.getTypeId() == item.getTypeId()) && (itemInSlot.getDurability() == item.getDurability())) {
            maxStackSize -= itemInSlot.getAmount();
            int amountToTransfer = Math.min(maxStackSize, item.getAmount());
            int restAmount = item.getAmount() - amountToTransfer;
            itemInSlot.setAmount(itemInSlot.getAmount() + amountToTransfer);
            if (restAmount < 1) {
                return null;
            }
            item = item.clone();
            item.setAmount(restAmount);
            return item;
        }
        if ((itemInSlot == null) || (itemInSlot.getTypeId() == Material.AIR.getId())) {
            int amountToTransfer = Math.min(maxStackSize, item.getAmount());
            int restAmount = item.getAmount() - amountToTransfer;
            inventory.setItem(slot, new ItemStack(item.getTypeId(), amountToTransfer, item.getDurability()));
            if (restAmount < 1) {
                return null;
            }
            item = item.clone();
            item.setAmount(restAmount);
            return item;
        }

        return item.clone();
    }

    public static ItemStack addItemIntoSlotWithMaxAmount(Inventory inventory, int slot, ItemStack item, int maxAmount) {
        ItemStack itemInSlot = inventory.getItem(slot);
        int maxStackSize = Math.min(maxAmount, BlockUtils.getMaxStackSize(item.getTypeId()));
        if ((itemInSlot != null) && (itemInSlot.getTypeId() == item.getTypeId()) && (itemInSlot.getDurability() == item.getDurability())) {
            maxStackSize -= itemInSlot.getAmount();
            int amountToTransfer = Math.min(maxStackSize, item.getAmount());
            int restAmount = item.getAmount() - amountToTransfer;
            if (amountToTransfer < 1) {
                return item.clone();
            }
            itemInSlot.setAmount(itemInSlot.getAmount() + amountToTransfer);
            if (restAmount < 1) {
                return null;
            }
            item = item.clone();
            item.setAmount(restAmount);
            return item;
        }
        if ((itemInSlot == null) || (itemInSlot.getTypeId() == Material.AIR.getId())) {
            int amountToTransfer = Math.min(maxStackSize, item.getAmount());
            int restAmount = item.getAmount() - amountToTransfer;
            if (amountToTransfer < 1) {
                return item.clone();
            }
            inventory.setItem(slot, new ItemStack(item.getTypeId(), amountToTransfer, item.getDurability()));
            if (restAmount < 1) {
                return null;
            }
            item = item.clone();
            item.setAmount(restAmount);
            return item;
        }

        return item.clone();
    }

    public static ItemStack addItemNative(Inventory inventory, ItemStack item) {
        CraftInventory cInv = (CraftInventory) inventory;
        while (true) {
            int firstPartial = cInv.firstPartial(item);

            if (firstPartial == -1) {
                int firstFree = cInv.firstEmpty();

                if (firstFree == -1) {
                    return item;
                }

                int maxStackSize = BlockUtils.getMaxStackSize(item.getTypeId());
                if (item.getAmount() > maxStackSize) {
                    CraftItemStack stack = new CraftItemStack(item.getTypeId(), maxStackSize, item.getDurability());
                    stack.addUnsafeEnchantments(item.getEnchantments());
                    cInv.setItem(firstFree, stack);
                    item.setAmount(item.getAmount() - maxStackSize);
                    continue;
                }

                cInv.setItem(firstFree, item);
                break;
            }

            ItemStack partialItem = cInv.getItem(firstPartial);

            int amount = item.getAmount();
            int partialAmount = partialItem.getAmount();
            int maxAmount = partialItem.getMaxStackSize();

            if (amount + partialAmount <= maxAmount) {
                partialItem.setAmount(amount + partialAmount);
                break;
            }

            partialItem.setAmount(maxAmount);
            item.setAmount(amount + partialAmount - maxAmount);
        }

        return null;
    }

    public static int addItem(Inventory inventory, ItemStack itemStack) {
        CraftInventory cInv = (CraftInventory) inventory;
        int itemAmount = itemStack.getAmount();
        int firstPartial;
        while (true) {
            firstPartial = cInv.firstPartial(itemStack);

            if (firstPartial != -1) {
                break;
            }
            int firstFree = inventory.firstEmpty();

            if (firstFree == -1) {
                return 0;
            }

            int maxStackSize = Material.getMaterial(itemStack.getTypeId()).getMaxStackSize();
            if (itemStack.getAmount() > maxStackSize) {
                CraftItemStack stack = new CraftItemStack(itemStack.getTypeId(), maxStackSize, itemStack.getDurability());
                stack.addUnsafeEnchantments(itemStack.getEnchantments());
                cInv.setItem(firstFree, stack);
                itemStack.setAmount(itemStack.getAmount() - maxStackSize);
                addItem(inventory, itemStack);
                continue;
            }

            cInv.setItem(firstFree, itemStack);
            return itemAmount;
        }

        ItemStack partialItem = cInv.getItem(firstPartial);

        int amount = itemStack.getAmount();
        int partialAmount = partialItem.getAmount();
        int maxAmount = Material.getMaterial(itemStack.getTypeId()).getMaxStackSize();

        if (amount + partialAmount <= maxAmount) {
            partialItem.setAmount(amount + partialAmount);
            return itemAmount;
        }

        partialItem.setAmount(maxAmount);
        itemStack.setAmount(amount + partialAmount - maxAmount);

        return itemAmount - itemStack.getAmount();
    }

    public static int countFreeSpace(Inventory inventory, ItemStack itemStack) {
        int amount = 0;
        int maxStackSize = BlockUtils.getMaxStackSize(itemStack.getType());
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item == null) || (item.getTypeId() == Material.AIR.getId())) {
                amount += maxStackSize;
            } else {
                if ((inventory.getItem(i).getTypeId() != itemStack.getAmount()) || (inventory.getItem(i).getDurability() != itemStack.getDurability()) || (!inventory.getItem(i).getEnchantments().equals(itemStack.getEnchantments()))
                        || (inventory.getItem(i).getAmount() > maxStackSize)) {
                    continue;
                }
                amount += maxStackSize - inventory.getItem(i).getAmount();
            }
        }
        return amount;
    }

    public static int countGeneralItemAmount(Inventory inventory) {
        int amount = 0;
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item == null) || (item.getTypeId() == Material.AIR.getId())) {
                continue;
            }
            amount += inventory.getItem(i).getAmount();
        }
        return amount;
    }

    public static int countGeneralStackedFreeSpace(Inventory inventory) {
        int amount = 0;
        ItemStack item = null;
        for (int i = 0; i < inventory.getSize(); i++) {
            item = inventory.getItem(i);
            if ((item == null) || (item.getTypeId() == Material.AIR.getId())) {
                amount += 64;
            } else {
                amount += BlockUtils.getMaxStackSize(item.getTypeId()) - item.getAmount();
            }
        }
        return amount;
    }
}