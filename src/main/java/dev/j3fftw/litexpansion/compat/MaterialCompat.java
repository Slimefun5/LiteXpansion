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
        if (parsed == null) {
            parsed = substitute(material);
        }
        return parsed != null ? parsed : Material.STONE;
    }

    // Sensible legacy substitutes for materials that don't exist on older servers (e.g. 1.8).
    private static final java.util.Map<XMaterial, XMaterial> LEGACY_SUBSTITUTES = buildLegacySubstitutes();

    private static java.util.Map<XMaterial, XMaterial> buildLegacySubstitutes() {
        java.util.Map<XMaterial, XMaterial> m = new java.util.EnumMap<>(XMaterial.class);
        m.put(XMaterial.NETHERITE_BLOCK, XMaterial.DIAMOND_BLOCK);
        m.put(XMaterial.NETHERITE_INGOT, XMaterial.DIAMOND);
        m.put(XMaterial.NETHERITE_SCRAP, XMaterial.IRON_NUGGET);
        m.put(XMaterial.ANCIENT_DEBRIS, XMaterial.NETHERRACK);
        m.put(XMaterial.BEEHIVE, XMaterial.DISPENSER);
        m.put(XMaterial.BEE_NEST, XMaterial.DISPENSER);
        m.put(XMaterial.HONEY_BLOCK, XMaterial.SLIME_BLOCK);
        m.put(XMaterial.BARREL, XMaterial.CHEST);
        m.put(XMaterial.BLAST_FURNACE, XMaterial.FURNACE);
        m.put(XMaterial.SMOKER, XMaterial.FURNACE);
        m.put(XMaterial.CAMPFIRE, XMaterial.NETHERRACK);
        m.put(XMaterial.SMITHING_TABLE, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.CARTOGRAPHY_TABLE, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.FLETCHING_TABLE, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.LOOM, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.STONECUTTER, XMaterial.CRAFTING_TABLE);
        m.put(XMaterial.GRINDSTONE, XMaterial.ANVIL);
        m.put(XMaterial.LANTERN, XMaterial.GLOWSTONE);
        m.put(XMaterial.COMPOSTER, XMaterial.CHEST);
        m.put(XMaterial.MAGMA_BLOCK, XMaterial.NETHERRACK);
        m.put(XMaterial.LODESTONE, XMaterial.IRON_BLOCK);
        m.put(XMaterial.BLACKSTONE, XMaterial.COBBLESTONE);
        m.put(XMaterial.OBSERVER, XMaterial.PISTON);
        return m;
    }

    private static Material substitute(XMaterial xMaterial) {
        XMaterial sub = LEGACY_SUBSTITUTES.get(xMaterial);
        return sub != null ? sub.parseMaterial() : null;
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
