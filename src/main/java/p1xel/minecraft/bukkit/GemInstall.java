package p1xel.minecraft.bukkit;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import p1xel.minecraft.bukkit.Utils.Config;
import p1xel.minecraft.bukkit.Utils.LocaleManager;

public class GemInstall extends JavaPlugin {

    private static GemInstall instance;
    public static GemInstall getInstance() { return instance; }
    private static Economy econ = null;
    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        LocaleManager.createFile();

        if (!setupEconomy() ) {
            this.getLogger().info("没有找到Vault!");
            this.getLogger().info("Vault is not found!");
        }

        this.getLogger().info("插件加载成功");
        this.getLogger().info("版本: " + this.getDescription().getVersion());
        this.getLogger().info("Plugin loaded.");
        this.getLogger().info("Version: " + this.getDescription().getVersion());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


}
