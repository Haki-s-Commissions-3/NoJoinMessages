package com.hakimen.nojoinmessages.mixin;

import com.hakimen.nojoinmessages.NoJoinMessagesServerConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Mixin(PlayerList.class)
public abstract class PlayerListBroadcastMessagesMixin {

    @Shadow
    @Final
    private MinecraftServer server;

    @Shadow
    @Final
    private List<ServerPlayer> players;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void broadcastSystemMessage(Component p_240526_, Function<ServerPlayer, Component> p_240594_, boolean p_240648_) {
        this.server.sendSystemMessage(p_240526_);
        Iterator var4 = this.players.iterator();

        while (var4.hasNext()) {
            ServerPlayer serverplayer = (ServerPlayer) var4.next();
            Component component = (Component) p_240594_.apply(serverplayer);
            if (component != null) {
                System.out.println( NoJoinMessagesServerConfig.serverMessagesByTranslationKey.get());
                if (NoJoinMessagesServerConfig.shouldBlockMessages.get()
                    && component.getContents() instanceof TranslatableContents translatableContents
                    && NoJoinMessagesServerConfig.serverMessagesByTranslationKey.get().contains(translatableContents.getKey())) {
                    return;
                }
            } else {
                serverplayer.sendSystemMessage(component, p_240648_);
            }
        }
    }
}
