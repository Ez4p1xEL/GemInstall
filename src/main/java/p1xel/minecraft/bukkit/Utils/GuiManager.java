package p1xel.minecraft.bukkit.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import p1xel.minecraft.bukkit.Utils.LocaleManager;

import java.util.List;

public class GuiManager {

    public Inventory createGUI(Player p) {
        Inventory inv = Bukkit.createInventory(p, 36, LocaleManager.getMessage("gui.title"));

        inv.setItem(0, getSlotItem("item-slot"));
        inv.setItem(1, getSlotItem("item-slot"));
        inv.setItem(2, getSlotItem("item-slot"));
        inv.setItem(3, getSlotItem("item-slot"));
        inv.setItem(9, getSlotItem("item-slot"));
        inv.setItem(10, getSlotItem("item-slot"));
        inv.setItem(12, getSlotItem("item-slot"));
        inv.setItem(18, getSlotItem("item-slot"));
        inv.setItem(19, getSlotItem("item-slot"));
        inv.setItem(20, getSlotItem("item-slot"));
        inv.setItem(21, getSlotItem("item-slot"));

        inv.setItem(5, getSlotItem("gem-slot"));
        inv.setItem(6, getSlotItem("gem-slot"));
        inv.setItem(7, getSlotItem("gem-slot"));
        inv.setItem(8, getSlotItem("gem-slot"));
        inv.setItem(14, getSlotItem("gem-slot"));
        inv.setItem(16, getSlotItem("gem-slot"));
        inv.setItem(17, getSlotItem("gem-slot"));
        inv.setItem(23, getSlotItem("gem-slot"));
        inv.setItem(24, getSlotItem("gem-slot"));
        inv.setItem(25, getSlotItem("gem-slot"));
        inv.setItem(26, getSlotItem("gem-slot"));
        inv.setItem(31, getSlotItem("start-slot"));

        inv.setItem(4, getSlotItem("neutral-slot"));
        inv.setItem(13, getSlotItem("neutral-slot"));
        inv.setItem(22, getSlotItem("neutral-slot"));
        inv.setItem(27, getSlotItem("neutral-slot"));
        inv.setItem(28, getSlotItem("neutral-slot"));
        inv.setItem(29, getSlotItem("neutral-slot"));
        inv.setItem(30, getSlotItem("neutral-slot"));
        inv.setItem(32, getSlotItem("neutral-slot"));
        inv.setItem(33, getSlotItem("neutral-slot"));
        inv.setItem(34, getSlotItem("neutral-slot"));
        inv.setItem(35, getSlotItem("neutral-slot"));



        return inv;
    }

    public ItemStack getSlotItem(String name) {
        if (name.equalsIgnoreCase("item-slot")) {
            ItemStack item = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1) ;
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(LocaleManager.getMessage("gui.item-slot.display"));
                meta.setLore(LocaleManager.get().getStringList("gui.item-slot.lore"));
            }
            item.setItemMeta(meta);
            return item;
        }

        if (name.equalsIgnoreCase("gem-slot")) {
            ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1) ;
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(LocaleManager.getMessage("gui.gem-slot.display"));
                meta.setLore(LocaleManager.get().getStringList("gui.gem-slot.lore"));
            }
            item.setItemMeta(meta);
            return item;
        }

        if (name.equalsIgnoreCase("start-slot")) {
            ItemStack item = new ItemStack(Material.ANVIL, 1) ;
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(LocaleManager.getMessage("gui.start-slot.display"));
                meta.setLore(LocaleManager.get().getStringList("gui.start-slot.lore"));
            }
            item.setItemMeta(meta);
            return item;
        }

        if (name.equalsIgnoreCase("neutral-slot")) {
            ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1) ;
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(LocaleManager.getMessage("gui.neutral-slot.display"));
                meta.setLore(LocaleManager.get().getStringList("gui.neutral-slot.lore"));
            }
            item.setItemMeta(meta);
            return item;
        }

        return new ItemStack(Material.BARRIER, 1);
    }

}
