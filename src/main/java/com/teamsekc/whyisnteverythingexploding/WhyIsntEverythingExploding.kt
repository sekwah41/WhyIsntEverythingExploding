package com.teamsekc.whyisnteverythingexploding

import com.teamsekc.whyisnteverythingexploding.enums.ExplosionValues
import org.dimdev.riftloader.listener.InitializationListener
import org.spongepowered.asm.launch.MixinBootstrap
import org.spongepowered.asm.mixin.Mixins

class WhyIsntEverythingExploding : InitializationListener {
    override fun onInitialization() {
        MixinBootstrap.init()
        Mixins.addConfiguration("mixins.whyisnteverythingexploding.json")
    }
}
