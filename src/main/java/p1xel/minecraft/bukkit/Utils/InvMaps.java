package p1xel.minecraft.bukkit.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class InvMaps {

    static HashMap<Player, Inventory> inv = new HashMap<>();

    public static HashMap<Player, Inventory> getInventories() {
        return inv;
    }

}
