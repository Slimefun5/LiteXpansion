package dev.j3fftw.litexpansion.ticker;

import dev.j3fftw.litexpansion.LiteXpansion;
import dev.j3fftw.litexpansion.items.PassiveElectricRemoval;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Represents the {@link PassiveElectricRemovalTicker} class.
 *
 * @author j3fftw
 * @implNote Collects every PassiveElectricRemoval item across all online players' inventories and ticks
 * them together on the main thread. Hacky, kept only until a better mechanism exists.
 */
public final class PassiveElectricRemovalTicker implements Runnable {

    @Override
    public void run() {
        final Set<TickerDataHolder> set = new HashSet<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            // This creates a copy and translates all the NMS items into CB items. This is pretty expensive
            final ItemStack[] contents = player.getInventory().getContents();

            for (int i = 0; i < contents.length; i++) {
                final ItemStack is = contents[i];
                if (is != null && is.hasItemMeta()) {
                    final SlimefunItem item = SlimefunItem.getByItem(is);

                    if (item instanceof PassiveElectricRemoval) {
                        PassiveElectricRemoval per = (PassiveElectricRemoval) item;
                        set.add(new TickerDataHolder(player.getUniqueId(), i, is, per));
                    }
                }
            }
        }

        Bukkit.getScheduler().runTask(LiteXpansion.getInstance(), () -> {
            for (TickerDataHolder holder : set) {
                PassiveElectricRemoval.tick(holder.item, holder.per);
            }
        });
    }

    private static class TickerDataHolder {

        private final UUID playerUuid;
        private final int slot;
        private final ItemStack item;
        private final PassiveElectricRemoval per;

        public TickerDataHolder(UUID playerUuid, int slot, ItemStack itemStack, PassiveElectricRemoval per) {
            this.playerUuid = playerUuid;
            this.slot = slot;
            this.item = itemStack;
            this.per = per;
        }

        @Override
        public int hashCode() {
            return Objects.hash(playerUuid, slot, item);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TickerDataHolder) {
                TickerDataHolder tdh = (TickerDataHolder) obj;
                return this.playerUuid.equals(tdh.playerUuid)
                    && this.slot == tdh.slot
                    && this.item.equals(tdh.item)
                    && this.per.equals(tdh.per);
            } else {
                return false;
            }
        }
    }
}

