package com.compose.myapplication.data.repository

import com.compose.myapplication.data.store.MemoryStore
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val memoryStore: MemoryStore,
) : MainRepository {
    override val stringFlow: Flow<String>
        get() = memoryStore.flow

    override fun updateStringFlow(value: String) {
        memoryStore.update(value)
    }
}
