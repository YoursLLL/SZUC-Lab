package org.poition.fawa.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.util.Identifier;
import org.poition.fawa.api.BarAPI;
import org.poition.fawa.api.OverlayType;
import org.poition.fawa.render.BarRenderer;

public class FawaClient implements ClientModInitializer {
    private static final OverlayType HUNGER = new OverlayType("hunger");
    private static final OverlayType ARMOR = new OverlayType("armor");
    private static final OverlayType HEALTH = new OverlayType("health");

    @Override
    public void onInitializeClient() {
        BarInfo barInfo = new BarInfo(HEALTH, 0x80FFFFFF, Identifier.of("fawa", "textures/heart.png"));
        BarAPI barAPI = new BarAPI();
        barAPI.createBar(barInfo);
        barAPI.renderStatus(true);
        barAPI.renderEvent(HudRenderCallback.EVENT);


    }
}
