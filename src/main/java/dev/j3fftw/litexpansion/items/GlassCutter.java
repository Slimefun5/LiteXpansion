package dev.j3fftw.litexpansion.items;

import dev.j3fftw.litexpansion.Items;
import dev.j3fftw.litexpansion.LiteXpansion;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun5.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun5.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun5.utils.tags.SlimefunTag;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * The {@link GlassCutter} is a {@link SimpleSlimefunItem} that breaks
 * glass and glass panes quickly.
 *
 * @author FluffyBear
 */
public class GlassCutter extends SimpleSlimefunItem<ItemUseHandler> implements Listener, Rechargeable {

    public GlassCutter() {
        super(Items.LITEXPANSION, Items.GLASS_CUTTER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            Items.REFINED_IRON.item(), Items.REFINED_IRON.item(), Items.REFINED_IRON.item(),
            new ItemStack(MaterialCompat.safe(XMaterial.SHEARS)), Items.ADVANCED_CIRCUIT.item(), new ItemStack(MaterialCompat.safe(XMaterial.SHEARS)),
            null, Items.CARBON_PLATE.item(), null
        });

        Bukkit.getPluginManager().registerEvents(this, LiteXpansion.getInstance());
    }

    @Nonnull
    public ItemUseHandler getItemHandler() {
        return e -> e.setUseBlock(Event.Result.DENY);
    }

    @EventHandler
    @SuppressWarnings("ConstantConditions")
    public void onGlassCut(PlayerInteractEvent e) {
        final Block block = e.getClickedBlock();
        if (block == null) {
            return;
        }

        final Material blockType = block.getType();
        final Location blockLocation = block.getLocation();

        if (e.getAction() == Action.LEFT_CLICK_BLOCK
            && SlimefunTag.GLASS.isTagged(blockType) && isItem(e.getItem())
            && Slimefun.getProtectionManager().hasPermission(e.getPlayer(), blockLocation,
            Interaction.BREAK_BLOCK)
        ) {
            e.setCancelled(true);

            final SlimefunItem slimefunItem = BlockStorage.check(block);

            if (slimefunItem == null && removeItemCharge(e.getItem(), 0.5F)) {
                blockLocation.getWorld().dropItemNaturally(blockLocation,
                    new ItemStack(blockType));
                block.setType(MaterialCompat.safe(XMaterial.AIR));
            }
        }
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return 300;
    }
}

