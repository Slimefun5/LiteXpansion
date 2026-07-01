package dev.j3fftw.litexpansion.machine;

import dev.j3fftw.litexpansion.Items;
import dev.j3fftw.litexpansion.machine.api.PoweredMachine;
import io.github.thebusybiscuit.slimefun5.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Represents the {@link Converter} class.
 *
 * @author j3fftw
 */
public class Converter extends AContainer implements PoweredMachine {

    public static final int TIME = 5;

    public Converter() {
        super(Items.LITEXPANSION, Items.CONVERTER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE)), new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE)), new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE)),
                new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), Items.ADVANCED_MACHINE_BLOCK.item(), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
                null, Items.ADVANCED_CIRCUIT.item(), null
            });
    }

    @Override
    protected void registerDefaultRecipes() {
        Material copperIngot = Material.getMaterial("COPPER_INGOT");
        if (copperIngot != null) {
            addRecipe(new ItemStack(copperIngot), SlimefunItems.COPPER_INGOT.item().clone());
            addRecipe(SlimefunItems.COPPER_INGOT.item().clone(), new ItemStack(copperIngot));
        }

        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)), SlimefunItems.GOLD_4K.item().clone());
        addRecipe(SlimefunItems.GOLD_4K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_6K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_8K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_10K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_12K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_14K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_16K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_20K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_22K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_24K.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_INGOT)));
        addRecipe(SlimefunItems.GOLD_24K_BLOCK.item().clone(), new ItemStack(MaterialCompat.safe(XMaterial.GOLD_BLOCK)));
    }

    private void addRecipe(ItemStack input, ItemStack output) {
        registerRecipe(Converter.TIME, new ItemStack[] {input}, new ItemStack[] {output});
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(MaterialCompat.safe(XMaterial.FIRE_CHARGE));
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return "\u00a76Converter";
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "CONVERTER";
    }

    @Override
    public int getCapacity() {
        return getDefaultEnergyConsumption() * 5;
    }

    @Override
    public int getDefaultEnergyConsumption() {
        return 20_000 / 26;
    }

    @Override
    public int getEnergyConsumption() {
        return this.getFinalEnergyConsumption();
    }

    @Override
    public int getSpeed() {
        return 1;
    }

}

