package p1xel.minecraft.bukkit;

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

        inv.setItem(11, getSlotItem("item-slot"));
        inv.setItem(14, getSlotItem("gem-slot"));
        inv.setItem(31, getSlotItem("start-slot"));

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
            ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1) ;
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(LocaleManager.getMessage("gui.start-slot.display"));
                meta.setLore(LocaleManager.get().getStringList("gui.start-slot.lore"));
            }
            item.setItemMeta(meta);
            return item;
        }

        return new ItemStack(Material.BARRIER, 1);
    }

}
