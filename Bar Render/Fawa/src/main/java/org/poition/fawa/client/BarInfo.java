package org.poition.fawa.client;

import net.minecraft.util.Identifier;
import org.poition.fawa.api.OverlayType;

public class BarInfo {
    private final OverlayType id;
    private final int color;
    private final Identifier icon;

    public BarInfo(OverlayType id, int color, Identifier icon) {
        this.id = id;
        this.color = color;
        this.icon = icon;
    }

    public OverlayType getId() {
        return id;
    }
    public int getColor() {
        return color;
    }
    public Identifier getIcon() {
        return icon;
    }

}
