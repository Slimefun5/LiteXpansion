package dev.j3fftw.litexpansion.weapons;

import dev.j3fftw.litexpansion.Items;
import dev.j3fftw.litexpansion.compat.AttributeCompat;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import dev.j3fftw.litexpansion.items.PassiveElectricRemoval;
import dev.j3fftw.litexpansion.machine.multiblock.MetalForge;
import dev.j3fftw.litexpansion.utils.Constants;
import dev.j3fftw.litexpansion.utils.Utils;
import io.github.thebusybiscuit.slimefun5.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun5.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.implementation.items.SimpleSlimefunItem;
import dev.j3fftw.litexpansion.compat.Pdc;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * Represents the {@link NanoBlade} class.
 *
 * @author j3fftw
 */
public class NanoBlade extends SimpleSlimefunItem<ItemUseHandler> implements Rechargeable, PassiveElectricRemoval {

    public static final float CAPACITY = 4_000;
    public static final float PER_TICK_REMOVAL = 64;

    public NanoBlade() {
        super(Items.LITEXPANSION, Items.NANO_BLADE, MetalForge.RECIPE_TYPE, new ItemStack[] {
                new ItemStack(MaterialCompat.safe(XMaterial.GLOWSTONE_DUST)), Items.ADVANCED_ALLOY.item(), null,
                new ItemStack(MaterialCompat.safe(XMaterial.GLOWSTONE_DUST)), Items.ADVANCED_ALLOY.item(), null,
                Items.CARBON_PLATE.item(), SlimefunItems.POWER_CRYSTAL.item(), Items.CARBON_PLATE.item()
            }
        );
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return CAPACITY;
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return event -> {
            final ItemMeta nanoBladeMeta = event.getItem().getItemMeta();
            boolean wasEnabled = Boolean.TRUE.equals(Pdc.getBoolean(nanoBladeMeta, Constants.NANO_BLADE_ENABLED));
            boolean enabled = !wasEnabled;

            int damage;

            if (enabled && getItemCharge(event.getItem()) > getRemovedChargePerTick()) {
                nanoBladeMeta.setDisplayName(ChatColor.DARK_GREEN + "Nano Blade" + ChatColor.GREEN + " (On)");
                damage = 13; // Base is 7 so 7 + 13 = 20
            } else {
                nanoBladeMeta.setDisplayName(ChatColor.DARK_GREEN + "Nano Blade" + ChatColor.RED + " (Off)");
                damage = -3; // Base is 7 so 7 - 3 = 4
            }

            Pdc.setBoolean(nanoBladeMeta, Constants.NANO_BLADE_ENABLED, enabled);

            AttributeCompat.setAttackDamage(nanoBladeMeta, damage);

            event.getItem().setItemMeta(nanoBladeMeta);
        };
    }

    @Override
    public float getRemovedChargePerTick() {
        return PER_TICK_REMOVAL;
    }

    @Override
    public float getCapacity() {
        return CAPACITY;
    }

    @Override
    public boolean isEnabled(@Nonnull ItemMeta meta) {
        final Optional<Boolean> opt = Utils.getOptionalBoolean(meta, Constants.NANO_BLADE_ENABLED);

        return opt.isPresent() && opt.get();
    }
}

