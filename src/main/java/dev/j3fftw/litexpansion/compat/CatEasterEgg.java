package dev.j3fftw.litexpansion.compat;

import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;

/**
 * Java-8 universal port helper. {@code org.bukkit.entity.Cat} is 1.14+. This class hard-references
 * {@code Cat}, so it must only be loaded once {@link #isCatApiAvailable()} has confirmed the class
 * exists. The caller guards on that before referencing this helper.
 */
public final class CatEasterEgg {

    private static final boolean CAT_API_AVAILABLE = classExists("org.bukkit.entity.Cat");

    private CatEasterEgg() {}

    public static boolean isCatApiAvailable() {
        return CAT_API_AVAILABLE;
    }

    public static void tryApply(Entity entity) {
        if (!(entity instanceof Cat)) {
            return;
        }

        Cat cat = (Cat) entity;
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 100_000);
        if (cat.getCatType() == Cat.Type.RED && randomNumber == 91622) {
            OfflinePlayer player = Bukkit.getOfflinePlayer("22815ad5-2a54-44c0-8f83-f65cfe5310f8"); // _lagpc_
            entity.setCustomName("Kleintje");
            cat.setOwner(player);
        }
    }

    private static boolean classExists(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
