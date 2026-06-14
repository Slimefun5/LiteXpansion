package dev.j3fftw.litexpansion.utils;

import io.github.thebusybiscuit.slimefun5.libraries.dough.common.ChatColors;
import dev.j3fftw.litexpansion.compat.Pdc;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * Represents the {@link Utils} class.
 *
 * @author j3fftw
 */
public final class Utils {

    private Utils() {}

    public static void send(Player player, String message) {
        player.sendMessage(ChatColor.GRAY + "[LiteXpansion] " + ChatColors.color(message));
    }

    public static Optional<Boolean> getOptionalBoolean(@Nonnull ItemMeta meta, @Nonnull String key) {
        // Java-8 universal port: read via the version-safe PersistentDataAPI Object/String overload
        // instead of the 1.14+ PersistentDataContainer API. The overload returns false when unset,
        // which matches every call site (all only treat a true value as "enabled").
        return Optional.of(Pdc.getBoolean(meta, key));
    }
}
