/*
 * Based on code from JustDireThings
 * https://github.com/Direwolf20-MC/JustDireThings
 *
 * Licensed under the MIT License.
 *
 * Original copyright (c) 2023 Direwolf20-MC
 */

package net.scratch221171.astralenchant.client.itemRenderer;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.IItemDecorator;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.registries.AEDataComponents;
import net.scratch221171.astralenchant.common.util.AEUtils;
import org.jetbrains.annotations.NotNull;

public class XPBarDecorator implements IItemDecorator {
    @Override
    public boolean render(
            @NotNull GuiGraphics guiGraphics, @NotNull Font font, @NotNull ItemStack stack, int xOffset, int yOffset) {
        int progress = stack.getOrDefault(AEDataComponents.OVER_MENDING, 0);
        if (progress > 0 || AEUtils.getEnchantmentLevel(stack, AEEnchantments.OVER_MENDING) > 0) {
            // Calculate positions based on whether the power bar is visible
            int XPBarY = stack.isBarVisible()
                    ? yOffset + 11
                    : yOffset + 13; // Adjust Y position based on power bar visibility
            int XPBarWidth = (int) Math.ceil(progress * 13F / 100f); // fluidContainingItem.getFluidBarWidth(stack);

            long time = System.currentTimeMillis();
            int XPBarColor = Mth.color(
                    0.4f,
                    (float) (0.8f + 0.2f * Math.sin(time * 2 * Math.PI / 2048f)),
                    1); // fluidContainingItem.getFluidBarColor(stack);

            // Render fluid bar
            renderBar(guiGraphics, xOffset + 2, XPBarY, XPBarWidth, XPBarColor);

            return true;
        }
        return false;
    }

    private void renderBar(GuiGraphics guiGraphics, int x, int y, int width, int color) {
        // Render the background of the bar (black)
        guiGraphics.fill(RenderType.guiOverlay(), x, y, x + 13, y + 2, 0xFF303030);

        // Render the fluid bar with the calculated width and color
        guiGraphics.fill(RenderType.guiOverlay(), x, y, x + width, y + 1, color | 0xFF000000);
    }
}
