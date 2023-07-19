package p1xel.minecraft.bukkit.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import p1xel.minecraft.bukkit.GemInstall;

import java.util.List;

public class Config {

    public static String getString(String path) {
        return GemInstall.getInstance().getConfig().getString(path);
    }

    public static List<String> getStringList(String path) { return GemInstall.getInstance().getConfig().getStringList(path); }

    public static int getInt(String path) {
        return GemInstall.getInstance().getConfig().getInt(path);
    }

    public static double getDouble(String path) {
        return GemInstall.getInstance().getConfig().getDouble(path);
    }

    public static boolean getBool(String path) {
        return GemInstall.getInstance().getConfig().getBoolean(path);
    }

    public static String getVersion() {
        return getString("version");
    }

    public static String getLanguage() {
        return getString("language");
    }

    public static FileConfiguration get() {
        return GemInstall.getInstance().getConfig();
    }
    
}
