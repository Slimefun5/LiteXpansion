package dev.j3fftw.litexpansion.compat;

import org.bukkit.inventory.meta.ItemMeta;

/**
 * Java-8 universal port helper. The item-meta {@code org.bukkit.inventory.meta.Damageable} interface
 * is 1.13+. Referencing it directly (incl. {@code instanceof}) throws on 1.8, so the durability is set
 * reflectively and skipped on versions where the interface does not exist.
 */
public final class DamageableCompat {

    private static final Class<?> DAMAGEABLE = resolve();

    private DamageableCompat() {}

    /**
     * Sets the item's durability damage via the {@code Damageable} item meta when available; no-op on
     * versions without the interface.
     */
    public static void setDamage(ItemMeta meta, int damage) {
        if (DAMAGEABLE == null || !DAMAGEABLE.isInstance(meta)) {
            return;
        }

        try {
            DAMAGEABLE.getMethod("setDamage", int.class).invoke(meta, damage);
        } catch (ReflectiveOperationException | RuntimeException e) {
            // Interface present but call failed; leave the meta unchanged.
        }
    }

    private static Class<?> resolve() {
        try {
            return Class.forName("org.bukkit.inventory.meta.Damageable");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
