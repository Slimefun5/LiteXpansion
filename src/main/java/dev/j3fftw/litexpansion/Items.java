package dev.j3fftw.litexpansion;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import dev.j3fftw.litexpansion.machine.MassFabricator;
import dev.j3fftw.litexpansion.machine.Recycler;
import dev.j3fftw.litexpansion.machine.RubberSynthesizer;
import dev.j3fftw.litexpansion.machine.generators.AdvancedSolarPanel;
import dev.j3fftw.litexpansion.weapons.NanoBlade;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefun5.utils.LoreBuilder;
import org.bukkit.Color;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Represents the {@link Items} class.
 *
 * @author j3fftw
 */
public final class Items {

    public static final ItemGroup LITEXPANSION = new ItemGroup(
        new NamespacedKey("litexpansion", "litexpansion"),
        CustomItemStack.create(MaterialCompat.stack(XMaterial.REDSTONE), "\u00a77LiteXpansion")
    ).setTheme("energy_tech");

    public static final SlimefunItemStack ELECTRIC_CHESTPLATE = new SlimefunItemStack("ELECTRIC_CHESTPLATE", MaterialCompat.safe(XMaterial.LEATHER_CHESTPLATE), Color.TEAL);

    public static final SlimefunItemStack NANO_BLADE = new SlimefunItemStack("NANO_BLADE", MaterialCompat.safe(XMaterial.DIAMOND_SWORD));

    public static final SlimefunItemStack GLASS_CUTTER = new SlimefunItemStack("GLASS_CUTTER", MaterialCompat.safe(XMaterial.GHAST_TEAR));
    public static final SlimefunItemStack MINING_DRILL = new SlimefunItemStack("MINING_DRILL", MaterialCompat.safe(XMaterial.IRON_SHOVEL));
    public static final SlimefunItemStack DIAMOND_DRILL = new SlimefunItemStack("DIAMOND_DRILL", MaterialCompat.safe(XMaterial.DIAMOND_SHOVEL));
    public static final SlimefunItemStack TREETAP = new SlimefunItemStack("TREETAP", MaterialCompat.safe(XMaterial.WOODEN_HOE));
    public static final SlimefunItemStack CARGO_CONFIGURATOR = new SlimefunItemStack("CARGO_CONFIGURATOR", MaterialCompat.safe(XMaterial.COMPASS));

    public static final SlimefunItemStack TIN_PLATE = new SlimefunItemStack("TIN_PLATE", MaterialCompat.safe(XMaterial.WHITE_CARPET));

    public static final SlimefunItemStack TIN_ITEM_CASING = new SlimefunItemStack("TIN_ITEM_CASING", MaterialCompat.safe(XMaterial.WHITE_CARPET));

    public static final SlimefunItemStack UNINSULATED_TIN_CABLE = new SlimefunItemStack("UNINSULATED_TIN_CABLE", MaterialCompat.safe(XMaterial.STRING));

    public static final SlimefunItemStack TIN_CABLE = new SlimefunItemStack("TIN_CABLE", MaterialCompat.safe(XMaterial.STRING));

    public static final SlimefunItemStack COPPER_PLATE = new SlimefunItemStack("COPPER_PLATE", MaterialCompat.safe(XMaterial.ORANGE_CARPET));

    public static final SlimefunItemStack COPPER_ITEM_CASING = new SlimefunItemStack("COPPER_ITEM_CASING", MaterialCompat.safe(XMaterial.ORANGE_CARPET));

    public static final SlimefunItemStack MAG_THOR = new SlimefunItemStack("MAG_THOR", MaterialCompat.safe(XMaterial.IRON_INGOT));

    public static final SlimefunItemStack THORIUM = new SlimefunItemStack("THORIUM", CustomItemStack.create(MaterialCompat.stack(XMaterial.PLAYER_HEAD), "Thorium"));

    public static final SlimefunItemStack SCRAP = new SlimefunItemStack("SCRAP", MaterialCompat.safe(XMaterial.DEAD_BUSH));
    public static final SlimefunItemStack UU_MATTER = new SlimefunItemStack("UU_MATTER", MaterialCompat.safe(XMaterial.PURPLE_DYE));
    public static final SlimefunItemStack IRIDIUM = new SlimefunItemStack("IRIDIUM", MaterialCompat.safe(XMaterial.WHITE_DYE));
    public static final SlimefunItemStack IRIDIUM_PLATE = new SlimefunItemStack("IRIDIUM_PLATE", MaterialCompat.safe(XMaterial.PAPER));
    public static final SlimefunItemStack THORIUM_DUST = new SlimefunItemStack("THORIUM_DUST", MaterialCompat.safe(XMaterial.BLACK_DYE));
    public static final SlimefunItemStack REFINED_IRON = new SlimefunItemStack("REFINED_IRON", MaterialCompat.safe(XMaterial.IRON_INGOT));
    public static final SlimefunItemStack MACHINE_BLOCK = new SlimefunItemStack("MACHINE_BLOCK", MaterialCompat.safe(XMaterial.IRON_BLOCK));
    public static final SlimefunItemStack UNINSULATED_COPPER_CABLE = new SlimefunItemStack("UNINSULATED_COPPER_CABLE", MaterialCompat.safe(XMaterial.STRING));
    public static final SlimefunItemStack COPPER_CABLE = new SlimefunItemStack("COPPER_CABLE", MaterialCompat.safe(XMaterial.STRING));
    public static final SlimefunItemStack RUBBER = new SlimefunItemStack("RUBBER", MaterialCompat.safe(XMaterial.INK_SAC));
    public static final SlimefunItemStack ELECTRONIC_CIRCUIT = new SlimefunItemStack("ELECTRONIC_CIRCUIT", MaterialCompat.safe(XMaterial.COBWEB));
    public static final SlimefunItemStack ADVANCED_CIRCUIT = new SlimefunItemStack("ADVANCED_CIRCUIT", MaterialCompat.safe(XMaterial.COBWEB));

    public static final SlimefunItemStack COAL_DUST = new SlimefunItemStack("COAL_DUST", MaterialCompat.safe(XMaterial.BLACK_DYE));
    public static final SlimefunItemStack RAW_CARBON_FIBRE = new SlimefunItemStack("RAW_CARBON_FIBRE", MaterialCompat.safe(XMaterial.BLACK_DYE));
    public static final SlimefunItemStack RAW_CARBON_MESH = new SlimefunItemStack("RAW_CARBON_MESH", MaterialCompat.safe(XMaterial.BLACK_DYE));
    public static final SlimefunItemStack CARBON_PLATE = new SlimefunItemStack("CARBON_PLATE", MaterialCompat.safe(XMaterial.BLACK_CARPET));
    public static final SlimefunItemStack ADVANCED_ALLOY = new SlimefunItemStack("ADVANCED_ALLOY", MaterialCompat.safe(XMaterial.PAPER));

    public static final SlimefunItemStack ADVANCED_MACHINE_BLOCK = new SlimefunItemStack("ADVANCED_MACHINE_BLOCK", MaterialCompat.safe(XMaterial.DIAMOND_BLOCK));

    public static final SlimefunItemStack LAPOTRON_CRYSTAL = new SlimefunItemStack("LAPOTRON_CRYSTAL", MaterialCompat.safe(XMaterial.DIAMOND));
    public static final SlimefunItemStack REINFORCED_STONE = new SlimefunItemStack("REINFORCED_STONE", MaterialCompat.safe(XMaterial.STONE));
    public static final SlimefunItemStack REINFORCED_DOOR = new SlimefunItemStack("REINFORCED_DOOR", MaterialCompat.safe(XMaterial.IRON_DOOR));
    public static final SlimefunItemStack REINFORCED_GLASS = new SlimefunItemStack("REINFORCED_GLASS", MaterialCompat.safe(XMaterial.GRAY_STAINED_GLASS));
    public static final SlimefunItemStack MIXED_METAL_INGOT = new SlimefunItemStack("MIXED_METAL_INGOT", MaterialCompat.safe(XMaterial.IRON_INGOT));
    public static final SlimefunItemStack RECYCLER = new SlimefunItemStack("SCRAP_MACHINE", MaterialCompat.safe(XMaterial.BLACK_CONCRETE));
    public static final SlimefunItemStack MASS_FABRICATOR_MACHINE = new SlimefunItemStack("MASS_FABRICATOR_MACHINE", MaterialCompat.safe(XMaterial.PURPLE_CONCRETE));
    public static final SlimefunItemStack RUBBER_SYNTHESIZER_MACHINE = new SlimefunItemStack("RUBBER_SYNTHESIZER", MaterialCompat.safe(XMaterial.ORANGE_CONCRETE));
    public static final SlimefunItemStack MACERATOR = new SlimefunItemStack("MACERATOR", MaterialCompat.safe(XMaterial.IRON_BLOCK));
    public static final SlimefunItemStack ADVANCED_SOLAR_PANEL = new SlimefunItemStack("ADVANCED_SOLAR_PANEL", MaterialCompat.safe(XMaterial.BLACK_GLAZED_TERRACOTTA));
    public static final SlimefunItemStack HYBRID_SOLAR_PANEL = new SlimefunItemStack("HYBRID_SOLAR_PANEL", MaterialCompat.safe(XMaterial.GRAY_GLAZED_TERRACOTTA));
    public static final SlimefunItemStack ULTIMATE_SOLAR_PANEL = new SlimefunItemStack("ULTIMATE_SOLAR_PANEL", MaterialCompat.safe(XMaterial.PURPLE_GLAZED_TERRACOTTA));
    public static final SlimefunItemStack MULTI_FUNCTIONAL_ELECTRIC_STORAGE_UNIT = new SlimefunItemStack("MULTI_FUNCTIONAL_ELECTRIC_STORAGE_UNIT", MaterialCompat.safe(XMaterial.IRON_BLOCK));
    public static final SlimefunItemStack MULTI_FUNCTIONAL_STORAGE_UNIT = new SlimefunItemStack("MULTI_FUNCTIONAL_STORAGE_UNIT", MaterialCompat.safe(XMaterial.DIAMOND_BLOCK));
    public static final SlimefunItemStack GOLD_PLATE = new SlimefunItemStack("GOLD_PLATE", MaterialCompat.safe(XMaterial.YELLOW_CARPET));
    public static final SlimefunItemStack GOLD_ITEM_CASING = new SlimefunItemStack("GOLD_ITEM_CASING", MaterialCompat.safe(XMaterial.YELLOW_CARPET));
    public static final SlimefunItemStack UNINSULATED_GOLD_CABLE = new SlimefunItemStack("UNINSULATED_GOLD_CABLE", MaterialCompat.safe(XMaterial.STRING));
    public static final SlimefunItemStack GOLD_CABLE = new SlimefunItemStack("GOLD_CABLE", MaterialCompat.safe(XMaterial.STRING));
    public static final SlimefunItemStack REFINED_SMELTERY = new SlimefunItemStack("REFINED_SMELTERY", MaterialCompat.safe(XMaterial.BLAST_FURNACE));

    public static final SlimefunItemStack METAL_FORGE = new SlimefunItemStack("METAL_FORGE", MaterialCompat.safe(XMaterial.DISPENSER));

    public static final SlimefunItemStack MANUAL_MILL = new SlimefunItemStack("MANUAL_MILL", MaterialCompat.safe(XMaterial.DISPENSER));

    public static final SlimefunItemStack GENERATOR = new SlimefunItemStack("GENERATOR", MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public static final SlimefunItemStack RE_BATTERY = new SlimefunItemStack("RE_BATTERY", MaterialCompat.safe(XMaterial.GLASS_BOTTLE));

    public static final SlimefunItemStack ADVANCED_SOLAR_HELMET = new SlimefunItemStack("ADVANCED_SOLAR_HELMET", MaterialCompat.safe(XMaterial.IRON_HELMET));

    public static final SlimefunItemStack CARBONADO_SOLAR_HELMET = new SlimefunItemStack("CARBONADO_SOLAR_HELMET", MaterialCompat.safe(XMaterial.GOLDEN_HELMET));

    public static final SlimefunItemStack ENERGIZED_SOLAR_HELMET = new SlimefunItemStack("ENERGIZED_SOLAR_HELMET", MaterialCompat.safe(XMaterial.GOLDEN_HELMET));

    public static final SlimefunItemStack ADVANCEDLX_SOLAR_HELMET = new SlimefunItemStack("ADVANCEDLX_SOLAR_HELMET", MaterialCompat.safe(XMaterial.DIAMOND_HELMET));

    public static final SlimefunItemStack HYBRID_SOLAR_HELMET = new SlimefunItemStack("HYBRID_SOLAR_HELMET", MaterialCompat.safe(XMaterial.DIAMOND_HELMET));

    public static final SlimefunItemStack ULTIMATE_SOLAR_HELMET = new SlimefunItemStack("ULTIMATE_SOLAR_HELMET", MaterialCompat.safe(XMaterial.DIAMOND_HELMET));

    public static final SlimefunItemStack FOOD_SYNTHESIZER = new SlimefunItemStack("FOOD_SYNTHESIZER", CustomItemStack.create(MaterialCompat.stack(XMaterial.PLAYER_HEAD), "Food Synthesizer"));

    public static final SlimefunItemStack IRON_PLATE = new SlimefunItemStack("IRON_PLATE", MaterialCompat.safe(XMaterial.WHITE_CARPET));

    public static final SlimefunItemStack DIAMOND_PLATE = new SlimefunItemStack("DIAMOND_PLATE", MaterialCompat.safe(XMaterial.CYAN_CARPET));
    public static final SlimefunItemStack THORIUM_PLATE = new SlimefunItemStack("THORIUM_PLATE", MaterialCompat.safe(XMaterial.GRAY_CARPET));

    public static final SlimefunItemStack POWER_UNIT = new SlimefunItemStack("POWER_UNIT", MaterialCompat.safe(XMaterial.GOLDEN_HOE));

    public static final SlimefunItemStack IRON_ITEM_CASING = new SlimefunItemStack("IRON_ITEM_CASING", MaterialCompat.safe(XMaterial.GRAY_CARPET));

    public static final SlimefunItemStack LAPIS_DUST = new SlimefunItemStack("LAPIS_DUST", MaterialCompat.safe(XMaterial.PURPLE_DYE));

    public static final SlimefunItemStack REDSTONE_DUST = new SlimefunItemStack("REDSTONE_DUST", MaterialCompat.safe(XMaterial.RED_DYE));

    public static final SlimefunItemStack DIAMOND_DUST = new SlimefunItemStack("DIAMOND_DUST", MaterialCompat.safe(XMaterial.CYAN_DYE));
    public static final SlimefunItemStack EMERALD_DUST = new SlimefunItemStack("EMERALD_DUST", MaterialCompat.safe(XMaterial.LIME_DYE));
    public static final SlimefunItemStack QUARTZ_DUST = new SlimefunItemStack("QUARTZ_DUST", MaterialCompat.safe(XMaterial.WHITE_DYE));
    public static final SlimefunItemStack ANCIENT_DEBRIS_DUST = new SlimefunItemStack("ANCIENT_DEBRIS_DUST", MaterialCompat.safe(XMaterial.BROWN_DYE));

    public static final SlimefunItemStack UU_CRAFTER = new SlimefunItemStack("UU_CRAFTER", MaterialCompat.safe(XMaterial.CRAFTING_TABLE));

    public static final SlimefunItemStack CONVERTER = new SlimefunItemStack("CONVER", MaterialCompat.safe(XMaterial.COAL_BLOCK));

    static {
        ADVANCED_SOLAR_HELMET.item().addEnchantment(Enchantment.DURABILITY, 1);
        CARBONADO_SOLAR_HELMET.item().addEnchantment(Enchantment.DURABILITY, 2);
        ENERGIZED_SOLAR_HELMET.item().addEnchantment(Enchantment.DURABILITY, 3);
        ADVANCEDLX_SOLAR_HELMET.item().addUnsafeEnchantment(Enchantment.DURABILITY, 4);
        HYBRID_SOLAR_HELMET.item().addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        ULTIMATE_SOLAR_HELMET.item().addUnsafeEnchantment(Enchantment.DURABILITY, 6);
        setGlint(ADVANCED_CIRCUIT);
        setGlint(GLASS_CUTTER);
        setGlint(DIAMOND_DRILL);
        setGlint(LAPOTRON_CRYSTAL);
        setGlint(ADVANCEDLX_SOLAR_HELMET);
        setGlint(HYBRID_SOLAR_HELMET);
        setGlint(ULTIMATE_SOLAR_HELMET);
    }

    private static void setGlint(SlimefunItemStack item) {
        ItemMeta meta = item.item().getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        item.item().setItemMeta(meta);
    }

    private Items() {}
}
