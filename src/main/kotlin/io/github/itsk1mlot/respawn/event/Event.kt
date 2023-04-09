package io.github.itsk1mlot.respawn.event

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

class Event: Listener {

    @EventHandler
    fun onRespawn(e: PlayerRespawnEvent) {
        val player = e.player
        player.respawn(player)
    }

    fun Player.respawn(player: Player): Player {
        val egg = ItemStack(Material.EGG, 1)
        val eggMeta = egg.itemMeta

        eggMeta.displayName(Component.text("§e부활절 달걀"))
        eggMeta.lore(listOf(Component.text("§a부활절에 태어난 부활절 귀신이 부활절에 부활해서 부활절 달걀을 부활 기념으로 선물했습니다!")))
        egg.itemMeta = eggMeta

        player.inventory.addItem(egg)
        player.sendMessage(Component.text("§e헉! 부활절에 부활해서 부활절 달걀을 부활 기념으로 선물받으셨군요!"))

        return player
    }
}