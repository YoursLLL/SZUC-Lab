package org.poition.fawa.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class HealthBarMixin {


    @Unique
    private static final Identifier OVERBAR = new Identifier("fawa", "textures/bars.png");
    @Unique
    private static final Identifier ICONS = new Identifier("minecraft", "textures/gui/icons.png");

    @Inject(method = "renderHealthBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;ceil(D)I", shift = At.Shift.AFTER), cancellable = true)
    private void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        float i = player.getHealth();
        context.drawTexture(OVERBAR, x, y, 0, 0, 81, 9);
        context.drawTexture(ICONS, x - 9, y, 52, 0, 9, 9);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(0.8F, 0.1F, 0.4F, 1.0F);
        RenderSystem.setShaderTexture(0, OVERBAR);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        context.drawTexture(OVERBAR, x, y, 0, 9, 2 * (81 - 2 * (40 - (int)i)), 9);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        ci.cancel();
    }

}
