package dev.j3fftw.litexpansion.compat;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XSound;
import java.util.Optional;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Java-8 universal port helper. The {@code org.bukkit.Sound} enum was flattened/renamed in 1.9 and
 * the {@code SoundCategory} overloads are 1.11+, so referencing modern {@code Sound} constants or the
 * category overload throws on 1.8. Sounds are resolved by name through {@link XSound}, which maps to
 * the correct constant for the running version.
 */
public final class SoundCompat {

    private SoundCompat() {}

    public static void play(Location location, String xSoundName, float volume, float pitch) {
        resolve(xSoundName).ifPresent(sound -> location.getWorld().playSound(location, sound, volume, pitch));
    }

    public static void play(Player player, Location location, String xSoundName, float volume, float pitch) {
        resolve(xSoundName).ifPresent(sound -> player.playSound(location, sound, volume, pitch));
    }

    private static Optional<Sound> resolve(String xSoundName) {
        return XSound.matchXSound(xSoundName).map(XSound::parseSound);
    }
}
