package org.alexcawl.player

import dagger.Module

@Module(includes = [PlayerDataModule::class, PlayerDomainModule::class, PlayerUiModule::class])
interface PlayerModule
