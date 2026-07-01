package dev.j3fftw.litexpansion.compat;

import java.lang.reflect.Constructor;
import java.util.UUID;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Java-8 universal port helper. {@code org.bukkit.attribute.Attribute} / {@code AttributeModifier}
 * and the {@link ItemMeta} attribute-modifier methods only exist on MC 1.9+. Referencing them
 * directly throws on 1.8, so the attack-damage modifier is applied reflectively here and silently
 * skipped on versions that lack the API.
 */
public final class AttributeCompat {

    private static final String ATTACK_DAMAGE = "GENERIC_ATTACK_DAMAGE";

    private AttributeCompat() {}

    /**
     * Replaces the attack-damage {@code AttributeModifier} on the given meta with one of the given
     * amount. No-op on versions without the attribute API.
     */
    public static void setAttackDamage(ItemMeta meta, double amount) {
        try {
            Class<?> attributeClass = Class.forName("org.bukkit.attribute.Attribute");
            Class<?> modifierClass = Class.forName("org.bukkit.attribute.AttributeModifier");
            Class<?> operationClass = Class.forName("org.bukkit.attribute.AttributeModifier$Operation");

            @SuppressWarnings({"unchecked", "rawtypes"})
            Object attribute = Enum.valueOf((Class<? extends Enum>) attributeClass, ATTACK_DAMAGE);
            @SuppressWarnings({"unchecked", "rawtypes"})
            Object addNumber = Enum.valueOf((Class<? extends Enum>) operationClass, "ADD_NUMBER");

            meta.getClass().getMethod("removeAttributeModifier", attributeClass).invoke(meta, attribute);

            Constructor<?> modifierConstructor = modifierClass.getConstructor(
                UUID.class, String.class, double.class, operationClass, EquipmentSlot.class);
            Object modifier = modifierConstructor.newInstance(
                UUID.randomUUID(), "generic.attackDamage", amount, addNumber, EquipmentSlot.HAND);

            meta.getClass().getMethod("addAttributeModifier", attributeClass, modifierClass)
                .invoke(meta, attribute, modifier);
        } catch (ReflectiveOperationException | RuntimeException e) {
            // Attribute API unavailable on this version; the item still works without the damage tweak.
        }
    }
}
