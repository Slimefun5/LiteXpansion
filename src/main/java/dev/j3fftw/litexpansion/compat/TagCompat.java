package dev.j3fftw.litexpansion.compat;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Set;
import org.bukkit.Material;

/**
 * Java-8 universal port helper. {@code org.bukkit.Tag} and its constants (e.g. {@code WOOL},
 * {@code CARPETS}) only exist on MC 1.13+. Referencing them directly throws on 1.8, so this helper
 * reads them reflectively and returns an empty set when the tag is unavailable, letting callers
 * degrade gracefully instead of failing to enable.
 */
public final class TagCompat {

    private TagCompat() {}

    /**
     * Returns the materials of the {@code org.bukkit.Tag} constant with the given field name, or an
     * empty set on versions where {@code Tag} (or that constant) does not exist.
     */
    @SuppressWarnings("unchecked")
    public static Set<Material> values(String tagFieldName) {
        try {
            Class<?> tagClass = Class.forName("org.bukkit.Tag");
            Field field = tagClass.getField(tagFieldName);
            Object tag = field.get(null);
            return (Set<Material>) tag.getClass().getMethod("getValues").invoke(tag);
        } catch (ReflectiveOperationException | RuntimeException e) {
            return Collections.emptySet();
        }
    }
}
