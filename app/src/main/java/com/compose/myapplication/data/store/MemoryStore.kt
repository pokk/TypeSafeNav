package com.compose.myapplication.data.store

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MemoryStore {
    private val _flow: MutableStateFlow<String> = MutableStateFlow("")
    val flow: StateFlow<String> = _flow

    fun update(value: String) = _flow.update { value }
}
