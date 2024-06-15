package com.compose.myapplication.di

import com.compose.myapplication.data.store.MemoryStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideMemoryStore(): MemoryStore = MemoryStore()
}
