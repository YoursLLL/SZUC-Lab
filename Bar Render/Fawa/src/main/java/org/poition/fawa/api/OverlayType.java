package org.poition.fawa.api;


import net.minecraft.client.MinecraftClient;

public class OverlayType {
    private final String id;
    static MinecraftClient client = MinecraftClient.getInstance();

    public OverlayType(String id) {
        this.id = id;
    }

    public float getPercent() {
        switch (this.id) {
            case "health" -> {
                assert client.player != null;
                return client.player.getHealth() / client.player.getMaxHealth();
            }
            case "armor" -> {
                assert client.player != null;
                return (float) client.player.getArmor() / 10;
            }
            case "air" -> {
                assert client.player != null;
                return (float) client.player.getAir() / client.player.getMaxAir();
            }
            case "hunger" -> {
                assert client.player != null;
                return client.player.getHungerManager().getSaturationLevel() / 10;
            }
        };
        return 0;
    }
}
