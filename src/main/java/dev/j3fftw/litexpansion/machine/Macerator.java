package dev.j3fftw.litexpansion.machine;

import dev.j3fftw.litexpansion.Items;
import dev.j3fftw.litexpansion.machine.api.PoweredMachine;
import io.github.thebusybiscuit.slimefun5.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import dev.j3fftw.litexpansion.compat.TagCompat;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Represents the {@link Macerator} class.
 *
 * @author j3fftw
 */
public class Macerator extends AContainer implements PoweredMachine {

    public static final int TIME = 5;

    public Macerator() {
        super(Items.LITEXPANSION, Items.MACERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(MaterialCompat.safe(XMaterial.FLINT)), new ItemStack(MaterialCompat.safe(XMaterial.FLINT)), new ItemStack(MaterialCompat.safe(XMaterial.FLINT)),
                new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), Items.MACHINE_BLOCK.item(), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)),
                null, Items.ELECTRONIC_CIRCUIT.item(), null
            });
    }

    @Override
    protected void registerDefaultRecipes() {
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.BLAZE_ROD)), new ItemStack(MaterialCompat.safe(XMaterial.BLAZE_POWDER), 5));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.COAL_BLOCK)), Items.COAL_DUST.asQuantity(9));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE_BLOCK)), new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE), 9));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.QUARTZ_BLOCK)), new ItemStack(MaterialCompat.safe(XMaterial.QUARTZ), 4));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.BONE_BLOCK)), new ItemStack(MaterialCompat.safe(XMaterial.BONE_MEAL), 9));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.BONE)), new ItemStack(MaterialCompat.safe(XMaterial.BONE_MEAL), 4));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.CLAY)), new ItemStack(MaterialCompat.safe(XMaterial.CLAY_BALL), 4));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.COAL)), Items.COAL_DUST.asQuantity(1));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)), new ItemStack(MaterialCompat.safe(XMaterial.SAND)));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.GLOWSTONE)), new ItemStack(MaterialCompat.safe(XMaterial.GLOWSTONE_DUST), 4));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL)), new ItemStack(MaterialCompat.safe(XMaterial.FLINT), 2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.ICE)), new ItemStack(MaterialCompat.safe(XMaterial.SNOWBALL), 4));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.QUARTZ_STAIRS)), new ItemStack(MaterialCompat.safe(XMaterial.QUARTZ), 4));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.SANDSTONE)), new ItemStack(MaterialCompat.safe(XMaterial.SAND), 4));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.STONE)), new ItemStack(MaterialCompat.safe(XMaterial.COBBLESTONE)));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.GRANITE)), new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL)));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.ANDESITE)), new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL)));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.DIORITE)), new ItemStack(MaterialCompat.safe(XMaterial.GRAVEL)));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.IRON_ORE)), SlimefunItems.IRON_DUST.asQuantity(2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.COAL_ORE)), CustomItemStack.create(Items.COAL_DUST.item(), 2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.LAPIS_ORE)), Items.LAPIS_DUST.asQuantity(2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.GOLD_ORE)), SlimefunItems.GOLD_DUST.asQuantity(2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.REDSTONE_ORE)), Items.REDSTONE_DUST.asQuantity(2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.DIAMOND_ORE)), Items.DIAMOND_DUST.asQuantity(2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.EMERALD_ORE)), Items.EMERALD_DUST.asQuantity(2));
        addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.NETHER_QUARTZ_ORE)), Items.QUARTZ_DUST.asQuantity(2));

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16)) {
            addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.NETHER_GOLD_ORE)), SlimefunItems.GOLD_DUST.asQuantity(2));
            addRecipe(new ItemStack(MaterialCompat.safe(XMaterial.ANCIENT_DEBRIS)), Items.ANCIENT_DEBRIS_DUST.asQuantity(2));
        }

        for (Material mat : TagCompat.values("WOOL")) {
            addRecipe(new ItemStack(mat), new ItemStack(MaterialCompat.safe(XMaterial.STRING), 4));
        }
    }

    private void addRecipe(ItemStack input, ItemStack output) {
        registerRecipe(Macerator.TIME, new ItemStack[] {input}, new ItemStack[] {output});
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(MaterialCompat.safe(XMaterial.FIRE_CHARGE));
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return "\u00a76Macerator";
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "MACERATOR";
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

