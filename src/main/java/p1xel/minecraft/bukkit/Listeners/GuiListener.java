package p1xel.minecraft.bukkit.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import p1xel.minecraft.bukkit.GemInstall;
import p1xel.minecraft.bukkit.Utils.*;

import java.util.Collections;
import java.util.List;

public class GuiListener implements Listener {

    GuiManager GuiManager = new GuiManager();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (InvMaps.getInventories().get(p) == null) {
            return;
        }

        Inventory inv = e.getClickedInventory();

        if (inv != InvMaps.getInventories().get(p)) {
            return;
        }

        int slot = e.getSlot();

        if (slot == 31) {

            // 检查装备是否符合条件
            // 检查装备是否为空
            ItemStack item = inv.getItem(11);
            if (item == null || !Config.getStringList("materials").contains(item.getType().toString())) {
                p.sendMessage(LocaleManager.getMessage("item-wrong"));
                e.setCancelled(true);
                return;
            }

            // 检查宝石是否为空
            ItemStack gem = inv.getItem(15);
            if (gem == null) {
                p.sendMessage(LocaleManager.getMessage("gem-wrong"));
                System.out.println("The gem is null!");
                e.setCancelled(true);
                return;
            }

            // 2023.07.19 01:11
            // 任务:
            // 1. 检查宝石所属类型 (是否存在Lore) [2023.07.19 01:22]
            // 2. 检查武器是否拥有孔位
            // 3. 检查武器孔位是否符合宝石
            // 我就不信今天干不出来
            // [2023.07.19 03:25] 已完成并且修复BUG

            String holeType = null;
            String holeLore = null;
            String gemName = null;

            ItemMeta gemMeta = gem.getItemMeta();
            if (gemMeta != null) {
                List<String> gemLores = gemMeta.getLore();
                for (String gems : GemUtils.get().getKeys(false)) {
                    String hLore = GemUtils.get().getString(gems + ".lore");
                    if (gemLores.stream().anyMatch(hLore::contains)) {
                        gemName = gems;
                        holeLore = hLore;
                        holeType = GemUtils.get().getString(gems + ".hole.type");
                        break;
                    }
                }
            }

            if (holeLore == null) {
                p.sendMessage(LocaleManager.getMessage("gem-wrong"));
                e.setCancelled(true);
                return;
            }

            if (gem.getAmount() > 1) {
                p.sendMessage(LocaleManager.getMessage("one-gem-only"));
                e.setCancelled(true);
                return;
            }

            String holeLoreOnItem = GemUtils.get().getString(gemName + ".hole.lore");

            ItemMeta itemMeta = item.getItemMeta();

            if (itemMeta == null) {
                p.sendMessage(LocaleManager.getMessage("gem-wrong"));
                e.setCancelled(true);
                return;
            }
            List<String> itemLores = itemMeta.getLore();
            if (itemLores.stream().noneMatch(holeLoreOnItem::contains)) {
                p.sendMessage(LocaleManager.getMessage("gem-wrong"));
                e.setCancelled(true);
                return;
            }

            if (GemUtils.get().getBoolean(gemName + ".vault.enable")) {
                double cost = GemUtils.get().getDouble(gemName + ".vault.cost");
                if (GemInstall.getEconomy().getBalance(p) < cost) {
                    p.sendMessage(LocaleManager.getMessage("money-not-enough").replaceAll("%amount%", String.valueOf(cost)));
                    e.setCancelled(true);
                    return;
                }
                GemInstall.getEconomy().withdrawPlayer(p, cost);
            }

            int line = itemLores.indexOf(holeLoreOnItem);
            itemLores.set(line, ChatColor.translateAlternateColorCodes('&', GemUtils.get().getString(gemName + ".attribute")));
            itemMeta.setLore(itemLores);
            item.setItemMeta(itemMeta);
            inv.remove(gem);
            p.sendMessage(LocaleManager.getMessage("success"));
            e.setCancelled(true);
            return;

        }

        // 放装备的栏位
        if (slot == 11) {
            return;
        }

        if (slot == 15) {
            return;
        }

        e.setCancelled(true);


    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();

        if (InvMaps.getInventories().get(p) == null) {
            return;
        }

        Inventory inv = e.getInventory();

        if (inv != InvMaps.getInventories().get(p)) {
            return;
        }

        ItemStack item = inv.getItem(11);
        if (item != null) {
            inv.setItem(11, null);
            p.getInventory().addItem(item);
        }

        ItemStack gem = inv.getItem(15);
        if (gem != null) {
            inv.setItem(15, null);
            p.getInventory().addItem(gem);
        }

        InvMaps.getInventories().replace(p, inv);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = (Player) e.getPlayer();

        p.getOpenInventory().close();


    }

}
