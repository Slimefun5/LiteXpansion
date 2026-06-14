package dev.j3fftw.litexpansion.items;

import dev.j3fftw.litexpansion.Items;
import io.github.thebusybiscuit.slimefun5.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun5.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun5.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import dev.j3fftw.litexpansion.compat.MaterialCompat;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

/**
 * Represents the {@link FoodSynthesizer} class.
 *
 * @author j3fftw
 */
public class FoodSynthesizer extends SlimefunItem implements Rechargeable, NotPlaceable {

    public FoodSynthesizer() {
        super(Items.LITEXPANSION, Items.FOOD_SYNTHESIZER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            SlimefunItems.PLASTIC_SHEET.item(), new ItemStack(MaterialCompat.safe(XMaterial.COOKED_BEEF)), SlimefunItems.PLASTIC_SHEET.item(),
            new ItemStack(MaterialCompat.safe(XMaterial.APPLE)), SlimefunItems.COOLER.item(), new ItemStack(MaterialCompat.safe(XMaterial.APPLE)),
            SlimefunItems.PLASTIC_SHEET.item(), new ItemStack(MaterialCompat.safe(XMaterial.COOKED_BEEF)), SlimefunItems.PLASTIC_SHEET.item()
        });
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) PlayerRightClickEvent::cancel);
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return 100;
    }
}

