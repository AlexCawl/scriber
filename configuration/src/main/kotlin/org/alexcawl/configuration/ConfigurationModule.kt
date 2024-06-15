package org.alexcawl.configuration

import dagger.Module

@Module(includes = [ConfigurationDataModule::class, ConfigurationDomainModule::class, ConfigurationUiModule::class])
interface ConfigurationModule