package p1xel.minecraft.bukkit.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import p1xel.minecraft.bukkit.GemInstall;
import p1xel.minecraft.bukkit.Utils.GuiManager;
import p1xel.minecraft.bukkit.Utils.InvMaps;
import p1xel.minecraft.bukkit.Utils.LocaleManager;

import javax.annotation.ParametersAreNonnullByDefault;

public class Cmd implements CommandExecutor {

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(LocaleManager.getCmdMessage("commands.help"));
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {

                for (String path : LocaleManager.get().getConfigurationSection("commands").getKeys(false)) {
                    if (path.equalsIgnoreCase("reload") && !sender.hasPermission("geminstall.admin")) {
                        continue;
                    }
                    sender.sendMessage(LocaleManager.getCmdMessage("commands." + path));
                }

                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("geminstall.admin")) {
                    sender.sendMessage(LocaleManager.getMessage("no-perm"));
                    return true;
                }

                GemInstall.getInstance().reloadConfig();
                sender.sendMessage(LocaleManager.getMessage("reload-success"));
                return true;
            }

            if (args[0].equalsIgnoreCase("gui")) {
                if (!sender.hasPermission("geminstall.use")) {
                    sender.sendMessage(LocaleManager.getMessage("no-perm"));
                    return true;
                }

                if (!(sender instanceof Player)) {
                    sender.sendMessage(LocaleManager.getMessage("must-be-a-player"));
                    return true;
                }

                Player p = (Player) sender;

                if (InvMaps.getInventories().get(p) != null) {
                    p.openInventory(InvMaps.getInventories().get(p));
                    sender.sendMessage(LocaleManager.getMessage("gui-opened"));
                    return true;
                }

                GuiManager GuiManager = new GuiManager();

                InvMaps.getInventories().put(p, GuiManager.createGUI(p));
                p.openInventory(InvMaps.getInventories().get(p));
                sender.sendMessage(LocaleManager.getMessage("gui-opened"));
                return true;
            }
        }


















        return false;
    }


}
