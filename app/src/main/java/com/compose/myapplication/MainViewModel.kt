package com.compose.myapplication

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        fun getUUID() {
            println("=====uuid============================================")
            println(savedStateHandle.hashCode())
            println("=====uuid============================================")
    }
}
