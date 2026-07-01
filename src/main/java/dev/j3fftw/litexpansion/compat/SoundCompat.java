package dev.j3fftw.litexpansion.compat;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XSound;
import java.util.Locale;
import java.util.Optional;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Java-8 universal port helper. The {@code org.bukkit.Sound} enum was flattened/renamed in 1.9 and
 * the {@code SoundCategory} overloads are 1.11+, so referencing modern {@code Sound} constants or the
 * category overload throws on 1.8. Sounds are resolved by name through {@link XSound}, which maps to
 * the correct constant for the running version. On 1.21.3+/26.x, where {@code Sound} became a
 * registry-backed interface and the shaded XSeries 9.10.0 can no longer initialise, resolution falls
 * back to reading the constant straight off {@code org.bukkit.Sound}.
 */
public final class SoundCompat {

    // Flipped off permanently the first time XSeries fails to initialise (1.21.3+ / 26.x).
    private static volatile boolean xSeriesUsable = true;

    private SoundCompat() {}

    public static void play(Location location, String xSoundName, float volume, float pitch) {
        resolve(xSoundName).ifPresent(sound -> location.getWorld().playSound(location, sound, volume, pitch));
    }

    public static void play(Player player, Location location, String xSoundName, float volume, float pitch) {
        resolve(xSoundName).ifPresent(sound -> player.playSound(location, sound, volume, pitch));
    }

    private static Optional<Sound> resolve(String xSoundName) {
        if (xSoundName == null) {
            return Optional.empty();
        }

        if (xSeriesUsable) {
            try {
                Optional<Sound> viaXSeries = XSound.matchXSound(xSoundName).map(XSound::parseSound);
                if (viaXSeries.isPresent()) {
                    return viaXSeries;
                }
            } catch (Throwable x) {
                xSeriesUsable = false;
            }
        }

        return resolveByField(xSoundName);
    }

    private static Optional<Sound> resolveByField(String xSoundName) {
        String name = xSoundName.toUpperCase(Locale.ROOT).replace('.', '_').replace(' ', '_').replace('-', '_');

        try {
            Object value = Sound.class.getField(name).get(null);
            return value instanceof Sound ? Optional.of((Sound) value) : Optional.empty();
        } catch (ReflectiveOperationException | RuntimeException x) {
            return Optional.empty();
        }
    }
}
