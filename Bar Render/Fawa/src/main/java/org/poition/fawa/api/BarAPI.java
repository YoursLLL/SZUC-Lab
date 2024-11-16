package org.poition.fawa.api;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.Event;
import org.poition.fawa.client.BarInfo;
import org.poition.fawa.render.BarRenderer;

public class BarAPI {
    BarInfo barInfo;
    BarRenderer barRenderer;
    boolean status;
    boolean already;

    public void createBar(BarInfo barInfo) {
        this.barInfo = barInfo;
        this.barRenderer = new BarRenderer(barInfo);
    }

    public void renderEvent(Event<HudRenderCallback> event) {
        if (status && !already) {
            event.register(new BarRenderer(barInfo));
            already = true;
        }
    }



    public void renderStatus(boolean status) {
        this.status = status;
    }



}
