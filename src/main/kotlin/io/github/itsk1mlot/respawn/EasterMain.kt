package io.github.itsk1mlot.respawn

import io.github.itsk1mlot.respawn.event.Event
import org.bukkit.plugin.java.JavaPlugin

class EasterMain: JavaPlugin() {

    companion object {
        lateinit var instance: EasterMain
    }
    init {
        instance = this
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(Event(), this)
    }
}