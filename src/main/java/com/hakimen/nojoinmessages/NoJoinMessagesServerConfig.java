package com.hakimen.nojoinmessages;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class NoJoinMessagesServerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue shouldBlockMessages = BUILDER
            .comment("Should block messages")
            .define("shouldBlockMessages", true);

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> serverMessagesByTranslationKey = BUILDER
            .comment("What translation keys to suppress")
            .defineList("translationKeysToSuppress", List.of(
                    "multiplayer.player.left",
                    "multiplayer.player.join"
            ), o -> o instanceof String);

    public static final ForgeConfigSpec CONFIG = BUILDER.build();
}
