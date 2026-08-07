package dev.j3fftw.litexpansion.items;

import dev.j3fftw.litexpansion.Items;
import dev.j3fftw.litexpansion.LiteXpansion;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.implementation.items.SimpleSlimefunItem;
import dev.j3fftw.litexpansion.compat.Pdc;
import io.github.thebusybiscuit.slimefun5.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the {@link CargoConfigurator} class.
 *
 * @author j3fftw
 */
public class CargoConfigurator extends SimpleSlimefunItem<ItemUseHandler> implements Listener {

    // Java-8 universal port: plain PDC key strings (org.bukkit.NamespacedKey is 1.12+), used with the
    // PersistentDataAPI Object/String overloads.
    private static final String CARGO_BLOCK = "cargo_block";
    private static final String CARGO_CONFIG = "cargo_config";

    public CargoConfigurator() {
        super(Items.LITEXPANSION, Items.CARGO_CONFIGURATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            Items.REFINED_IRON.item(), SlimefunItems.REINFORCED_PLATE.item(), Items.REFINED_IRON.item(),
            SlimefunItems.REINFORCED_PLATE.item(), SlimefunItems.CARGO_MANAGER.item(), SlimefunItems.REINFORCED_PLATE.item(),
            Items.REFINED_IRON.item(), SlimefunItems.REINFORCED_PLATE.item(), Items.REFINED_IRON.item()
        });

        Bukkit.getPluginManager().registerEvents(this, LiteXpansion.getInstance());
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> e.setUseBlock(Event.Result.DENY);
    }

    private boolean canUseCargoConfigurator(@Nonnull Player p, @Nonnull Block clicked) {
        return Slimefun.getProtectionManager().hasPermission(p, clicked, Interaction.INTERACT_BLOCK);
    }

    @EventHandler
    public void onCargoConfiguratorItemClick(PlayerInteractEvent e) {
        if (e.getItem() == null || e.getMaterial() != MaterialCompat.safe(XMaterial.COMPASS)) {
            return;
        }

        final ItemStack clickedItem = e.getItem();

        if (!this.isItem(clickedItem) || SlimefunItem.getByItem(Items.CARGO_CONFIGURATOR).isDisabled()) {
            return;
        }

        final ItemMeta meta = clickedItem.getItemMeta();

        final List<String> defaultLore = Items.CARGO_CONFIGURATOR.getItemMetaSnapshot().getLore()
            .orElse(new ArrayList<>());
        final List<String> lore = meta.hasLore() ? meta.getLore() : defaultLore;

        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
            && e.getPlayer().isSneaking()
        ) {
            clearConfig(e.getPlayer(), clickedItem, meta, defaultLore, lore);
            e.setCancelled(true);
            return;
        }

        if ((e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK)
            || e.getClickedBlock() == null) {
            return;
        }

        final SlimefunItem block = BlockStorage.check(e.getClickedBlock());
        if (block == null) {
            return;
        }

        final ItemStack clickedItemStack = block.getItem();

        final String blockId = block.getId();
        if (!blockId.equals(SlimefunItems.CARGO_INPUT_NODE.getItemId())
            && !blockId.equals(SlimefunItems.CARGO_OUTPUT_NODE.getItemId())
            && !blockId.equals(SlimefunItems.CARGO_OUTPUT_NODE_2.getItemId())
        ) {
            return;
        }

        final Player p = e.getPlayer();

        if (!canUseCargoConfigurator(p, e.getClickedBlock()) && !p.hasPermission("slimefun.cargo.bypass")) {
            Slimefun.getLocalization().sendMessage(p, "inventory.no-access", true);
            return;
        }

        e.setCancelled(true);

        runActions(e, clickedItemStack, meta, blockId, lore, defaultLore);

        meta.setLore(lore);
        clickedItem.setItemMeta(meta);
    }

    private void clearConfig(@Nonnull Player player, @Nonnull ItemStack itemStack, @Nonnull ItemMeta meta,
                             @Nonnull List<String> defaultLore, @Nonnull List<String> lore
    ) {
        Pdc.remove(meta, CARGO_BLOCK);
        Pdc.remove(meta, CARGO_CONFIG);
        player.sendMessage(ChatColor.RED + "Cleared node configuration!");

        if (lore.size() != defaultLore.size()) {
            lore.clear();
            lore.addAll(defaultLore);
        }

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    private void runActions(@Nonnull PlayerInteractEvent e, @Nonnull ItemStack clickedItemStack, @Nonnull ItemMeta meta,
                            @Nonnull String blockId, @Nonnull List<String> lore, @Nonnull List<String> defaultLore
    ) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            final String copiedBlock = Pdc.getString(meta, CARGO_BLOCK);
            final String config = Pdc.getString(meta, CARGO_CONFIG);
            if (copiedBlock == null || config == null) {
                e.getPlayer().sendMessage(ChatColor.RED + "You do not have a config copied!");
                return;
            }

            if (!copiedBlock.equals(blockId)) {
                e.getPlayer().sendMessage(ChatColor.RED + "You can't apply the config to this node!");
                return;
            }

            BlockStorage.setBlockInfo(e.getClickedBlock(), config, true);
            BlockStorage.getStorage(e.getClickedBlock().getWorld()).reloadInventory(e.getClickedBlock().getLocation());
            e.getPlayer().sendMessage(ChatColor.GREEN + "Applied configuration!");
        } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Pdc.setString(meta, CARGO_BLOCK, blockId);
            Pdc.setString(meta, CARGO_CONFIG, BlockStorage.getBlockInfoAsJson(e.getClickedBlock()));

            // Has the copied part
            if (lore.size() == defaultLore.size() + 2) {
                lore.clear();
                lore.addAll(defaultLore);
            }
            lore.addAll(Arrays.asList("", ChatColor.GRAY + "> Copied "
                + ChatColor.RESET + clickedItemStack.getItemMeta().getDisplayName()
                + ChatColor.GRAY + " config!"
            ));
            e.getPlayer().sendMessage(ChatColor.GREEN + "Copied node configuration!");
        }
    }
}

