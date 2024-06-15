package com.compose.myapplication.di

import com.compose.myapplication.data.repository.MainRepository
import com.compose.myapplication.data.repository.MainRepositoryImpl
import com.compose.myapplication.data.store.MemoryStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {
    @Provides
    @ViewModelScoped
    fun provideMainRepository(memoryStore: MemoryStore): MainRepository = MainRepositoryImpl(memoryStore)
}
