package dev.j3fftw.litexpansion.machine.extensions;

import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.items.backpacks.SlimefunBackpack;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun5.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun5.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import dev.j3fftw.litexpansion.compat.ParticleCompat;
import dev.j3fftw.litexpansion.compat.SoundCompat;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class CraftingMultiBlock extends MultiBlockMachine {

    protected CraftingMultiBlock(ItemGroup itemGroup, SlimefunItemStack item, ItemStack[] recipe,
                                 ItemStack[] machineRecipes, BlockFace trigger) {
        super(itemGroup, item, recipe, machineRecipes, trigger);
    }

    protected Inventory createVirtualInventory(Inventory inv) {
        Inventory fakeInv = Bukkit.createInventory(null, 9, "Fake Inventory");

        for (int j = 0; j < inv.getContents().length; j++) {
            ItemStack stack = inv.getContents()[j] != null && inv.getContents()[j].getAmount() > 1 ?
                CustomItemStack.create(inv.getContents()[j], inv.getContents()[j].getAmount() - 1) : null;
            fakeInv.setItem(j, stack);
        }

        return fakeInv;
    }

    @Override
    public void onInteract(Player p, Block b) {
        Block dispenser = b.getRelative(BlockFace.DOWN);
        BlockState state = PaperLib.getBlockState(dispenser, false).getState();

        final Block specialBlock = getSpecialBlock(dispenser);
        if (specialBlock != null && specialBlock.getType() != MaterialCompat.safe(XMaterial.AIR)
            && BlockStorage.hasBlockInfo(specialBlock)) {
            p.sendMessage(ChatColor.RED + "You can't use Slimefun blocks as part of the multi-block >:(");
            return;
        }

        if (state instanceof Dispenser) {
            Dispenser disp = (Dispenser) state;
            Inventory inv = disp.getInventory();
            List<ItemStack[]> inputs = RecipeType.getRecipeInputList(this);

            for (ItemStack[] input : inputs) {
                if (isCraftable(inv, input)) {
                    ItemStack output = RecipeType.getRecipeOutputList(this, input).clone();

                    if (SlimefunUtils.canPlayerUseItem(p, output, true)) {
                        craft(inv, dispenser, p, b, output);
                    }

                    return;
                }
            }

            Slimefun.getLocalization().sendMessage(p, "machines.pattern-not-found", true);
        }
    }

    private void craft(Inventory inv, Block dispenser, Player p, Block b, ItemStack output) {
        Inventory fakeInv = createVirtualInventory(inv);
        Inventory outputInv = findOutputInventory(output, dispenser, inv, fakeInv);

        if (outputInv != null) {
            for (int j = 0; j < 9; j++) {
                ItemStack item = inv.getContents()[j];

                if (item != null && item.getType() != MaterialCompat.safe(XMaterial.AIR)) {
                    ItemUtils.consumeItem(item, true);
                }
            }

            SoundCompat.play(b.getLocation(), "BLOCK_WOODEN_BUTTON_CLICK_ON", 1, 1);

            outputInv.addItem(output);

            if (removeSpecialBlock()) {
                final Block specialBlock = getSpecialBlock(dispenser);
                if (specialBlock != null) {
                    specialBlock.setType(MaterialCompat.safe(XMaterial.AIR));
                    ParticleCompat.spawn(specialBlock.getWorld(), "PORTAL", specialBlock.getLocation(), 4,
                        0.5, 0.5, 0.5);
                }
            }
        } else {
            Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
        }
    }

    private boolean isCraftable(Inventory inv, ItemStack[] recipe) {
        for (int j = 0; j < inv.getContents().length; j++) {
            if (!SlimefunUtils.isItemSimilar(inv.getContents()[j], recipe[j], true)) {
                if (SlimefunItem.getByItem(recipe[j]) instanceof SlimefunBackpack) {
                    if (!SlimefunUtils.isItemSimilar(inv.getContents()[j], recipe[j], false)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Gets the special block which we wish to remove or handle.
     *
     * @param dispenser The dispenser used to get the special block
     * @return The special block
     */
    public abstract Block getSpecialBlock(Block dispenser);

    public abstract boolean removeSpecialBlock();
}

