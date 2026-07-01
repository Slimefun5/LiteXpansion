package dev.j3fftw.litexpansion.machine.generators;

import dev.j3fftw.litexpansion.Items;
import io.github.thebusybiscuit.slimefun5.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.items.electric.generators.CoalGenerator;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import dev.j3fftw.litexpansion.compat.TagCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Represents the {@link Generator} class.
 *
 * @author j3fftw
 */
public class Generator extends CoalGenerator {

    public Generator() {
        super(Items.LITEXPANSION, Items.GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            null, Items.RE_BATTERY.item(), null,
            null, Items.MACHINE_BLOCK.item(), null,
            null, new ItemStack(MaterialCompat.safe(XMaterial.FURNACE)), null
        });
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return "\u00a77Generator";
    }

    @Nonnull
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(MaterialCompat.safe(XMaterial.FIRE_CHARGE));
    }

    @Override
    public int getEnergyProduction() {
        return 10;
    }

    @Override
    protected void registerDefaultFuelTypes() {
        super.registerDefaultFuelTypes();

        super.registerFuel(new MachineFuel(100, new ItemStack(MaterialCompat.safe(XMaterial.LAVA_BUCKET))));
        super.registerFuel(new MachineFuel(8, new ItemStack(MaterialCompat.safe(XMaterial.CHARCOAL))));
        super.registerFuel(new MachineFuel(6, new ItemStack(MaterialCompat.safe(XMaterial.SCAFFOLDING))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.WOODEN_AXE))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.WOODEN_HOE))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.WOODEN_PICKAXE))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.WOODEN_SHOVEL))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.WOODEN_SWORD))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.LADDER))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.CRAFTING_TABLE))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.CARTOGRAPHY_TABLE))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.FLETCHING_TABLE))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.SMITHING_TABLE))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.LOOM))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.BOOKSHELF))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.LECTERN))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.COMPOSTER))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.CHEST))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.TRAPPED_CHEST))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.BARREL))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.DAYLIGHT_DETECTOR))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.JUKEBOX))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.NOTE_BLOCK))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.BROWN_MUSHROOM_BLOCK))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.RED_MUSHROOM_BLOCK))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.MUSHROOM_STEM))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.CROSSBOW))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.BOW))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.FISHING_ROD))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.BOWL))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.STICK))));
        super.registerFuel(new MachineFuel(1, new ItemStack(MaterialCompat.safe(XMaterial.BAMBOO))));

        // Banners
        for (Material mat : TagCompat.values("ITEMS_BANNERS")) {
            super.registerFuel(new MachineFuel(1, new ItemStack(mat)));
        }

        for (Material mat : TagCompat.values("CARPETS")) {
            super.registerFuel(new MachineFuel(1, new ItemStack(mat)));
        }

        // Saplings
        for (Material mat : TagCompat.values("SAPLINGS")) {
            super.registerFuel(new MachineFuel(1, new ItemStack(mat)));
        }

        // Wool
        for (Material mat : TagCompat.values("WOOL")) {
            super.registerFuel(new MachineFuel(1, new ItemStack(mat)));
        }

    }

    @Override
    public int getCapacity() {
        return 4000;
    }
}

