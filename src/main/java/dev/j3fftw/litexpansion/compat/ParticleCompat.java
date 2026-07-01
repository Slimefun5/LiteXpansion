package dev.j3fftw.litexpansion.compat;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * Java-8 universal port helper. {@code org.bukkit.Particle} and {@code World#spawnParticle} are 1.9+,
 * so they are invoked reflectively here and skipped on versions that lack the API.
 */
public final class ParticleCompat {

    private ParticleCompat() {}

    public static void spawn(World world, String particleName, Location location, int count,
                             double offsetX, double offsetY, double offsetZ) {
        try {
            Class<?> particleClass = Class.forName("org.bukkit.Particle");
            @SuppressWarnings({"unchecked", "rawtypes"})
            Object particle = Enum.valueOf((Class<? extends Enum>) particleClass, particleName);
            world.getClass()
                .getMethod("spawnParticle", particleClass, Location.class,
                    int.class, double.class, double.class, double.class)
                .invoke(world, particle, location, count, offsetX, offsetY, offsetZ);
        } catch (ReflectiveOperationException | RuntimeException e) {
            // Particle API unavailable on this version; visual effect is simply skipped.
        }
    }
}
