package com.compose.myapplication.first

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class FirstViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel()
