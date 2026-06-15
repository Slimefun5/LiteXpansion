package dev.j3fftw.litexpansion.compat;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.Material;

/**
 * Java-8 universal port helper. Resolves {@link XMaterial} constants to the {@link Material} of the
 * running server version. Many modern {@code Material} constants do not exist on MC 1.8 and would
 * throw {@code NoSuchFieldError} if referenced directly, so all such references are routed through
 * {@link XMaterial#parseMaterial()} here.
 */
public final class MaterialCompat {

    private MaterialCompat() {}

    /**
     * Returns the {@link Material} for the given {@link XMaterial} on the running version, falling back
     * to {@link Material#STONE} when the material has no equivalent (so callers never receive null).
     */
    public static Material safe(XMaterial material) {
        Material parsed = material.parseMaterial();
        return parsed != null ? parsed : Material.STONE;
    }

    /**
     * Resolves an {@link XMaterial} to an {@link ItemStack}, preserving the legacy data value
     * (e.g. SKULL_ITEM:3 = player head) that {@link #safe(XMaterial)} drops on 1.8-1.12.
     */
    @javax.annotation.Nonnull
    public static org.bukkit.inventory.ItemStack stack(@javax.annotation.Nonnull XMaterial material) {
        org.bukkit.inventory.ItemStack item = material.parseItem();
        return item != null ? item : new org.bukkit.inventory.ItemStack(safe(material));
    }
}
