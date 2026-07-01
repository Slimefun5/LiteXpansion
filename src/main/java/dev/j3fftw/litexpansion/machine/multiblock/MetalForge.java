package dev.j3fftw.litexpansion.machine.multiblock;

import dev.j3fftw.litexpansion.Items;
import dev.j3fftw.litexpansion.LiteXpansion;
import dev.j3fftw.litexpansion.machine.extensions.CraftingMultiBlock;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

/**
 * Represents the {@link MetalForge} class.
 *
 * @author j3fftw
 */
public class MetalForge extends CraftingMultiBlock {

    public static final RecipeType RECIPE_TYPE = new RecipeType(
        new NamespacedKey("litexpansion", "metal_forge"),
        Items.METAL_FORGE,
        "",
        "&7Used to Forge Metals"
    );

    private static final ItemStack anvil = new ItemStack(MaterialCompat.safe(XMaterial.ANVIL));
    private static final ItemStack ironBlock = new ItemStack(MaterialCompat.safe(XMaterial.IRON_BLOCK));

    public MetalForge() {
        super(Items.LITEXPANSION, Items.METAL_FORGE, new ItemStack[] {
            anvil, new ItemStack(MaterialCompat.safe(XMaterial.STONE_BRICK_WALL)), anvil,
            ironBlock, new ItemStack(MaterialCompat.safe(XMaterial.DISPENSER)), ironBlock,
            null, new ItemStack(MaterialCompat.safe(XMaterial.DIAMOND_BLOCK)), null
        }, new ItemStack[0], BlockFace.DOWN);
    }

    @Override
    public Block getSpecialBlock(Block dispenser) {
        return dispenser.getRelative(BlockFace.DOWN);
    }

    @Override
    public boolean removeSpecialBlock() {
        return true;
    }
}

