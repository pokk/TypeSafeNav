package com.compose.myapplication.data.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    val stringFlow: Flow<String>

    fun updateStringFlow(value: String)
}
