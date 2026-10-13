package dev.j3fftw.litexpansion.utils;

import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.j3fftw.litexpansion.LiteXpansion;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

/**
 * Resolves this addon's chat text for one viewer, from {@code languages/<lang>/messages.yml}.
 *
 * @implNote The {@link ChatColor} deliberately stays at the call site rather than moving into the
 *           translated value: a translator never edits a colour code they cannot see the effect of.
 */
public final class Messages {

    private Messages() {}

    @Nonnull
    public static String get(@Nullable Player player, @Nonnull String key, @Nonnull String english) {
        return Slimefun.getAddonMessageService().getMessage(player, LiteXpansion.getInstance(), key, english);
    }

    @Nonnull
    public static String get(@Nullable Player player, @Nonnull String key, @Nonnull String english,
                             @Nonnull UnaryOperator<String> placeholders) {
        return placeholders.apply(get(player, key, english));
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull String key, @Nonnull String english) {
        Slimefun.getAddonMessageService().sendMessage(recipient, LiteXpansion.getInstance(), key, english);
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key, @Nonnull String english) {
        send(recipient, colour, key, english, UnaryOperator.identity());
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key,
                            @Nonnull String english, @Nonnull UnaryOperator<String> placeholders) {
        Slimefun.getAddonMessageService().sendMessage(recipient, LiteXpansion.getInstance(), key, english,
                line -> colour + placeholders.apply(line));
    }

    /**
     * One of this addon's items named in the viewer's own language, falling back to the id's own
     * built-in name when the item is not registered.
     */
    @Nonnull
    public static String itemName(@Nonnull Player player, @Nonnull SlimefunItemStack stack) {
        SlimefunItem item = SlimefunItem.getByItem(stack);
        return item != null ? Slimefun.getItemTranslationService().getName(player, item) : stack.getItemId();
    }
}
