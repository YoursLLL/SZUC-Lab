package org.poition.fawa.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.poition.fawa.api.OverlayType;
import org.poition.fawa.client.BarInfo;

public class BarRenderer implements HudRenderCallback {

    Identifier BAR = Identifier.of("fawa", "textures/bars.png");

    MinecraftClient client = MinecraftClient.getInstance();

    BarInfo info;

    public BarRenderer(BarInfo barInfo) {
        this.info = barInfo;
    }

    public void drawBar(DrawContext context) {
        MatrixStack matrices = context.getMatrices();
        OverlayType id = info.getId();
        int color = info.getColor();
        Identifier icon = info.getIcon();

        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = (color >> 4) & 0xFF;
        int alpha = color & 0xFF;

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        int x = width / 20;
        int y = height / 20;

        matrices.push();

        context.drawTexture(BAR, x, y, 0, 0, 81, 9);
        context.drawTexture(icon, x - 9, y, 0, 0, 8, 8, 8, 8);

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(red / 255.0F, green / 255.0F, blue / 255.0F, alpha / 255.0F);
        RenderSystem.setShaderTexture(0, BAR);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        context.drawTexture(BAR, x, y, 0, 9, (int) (81 * id.getPercent()), 9);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();

        matrices.pop();
    }

    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        drawBar(drawContext);
    }
}