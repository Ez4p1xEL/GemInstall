package p1xel.minecraft.bukkit.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import p1xel.minecraft.bukkit.GemInstall;

import java.io.File;
import java.io.IOException;

public class GemUtils {

    public static void createFile() {
        File file = new File(GemInstall.getInstance().getDataFolder(), "gem.yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            GemInstall.getInstance().saveResource("gem.yml", false);
        }
    }

    public static FileConfiguration get() {
        File file = new File(GemInstall.getInstance().getDataFolder(), "gem.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, Object value) {
        File file = new File(GemInstall.getInstance().getDataFolder(), "gem.yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        yaml.set(path, value);
        try {
            yaml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
