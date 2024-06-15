package com.compose.myapplication.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.compose.myapplication.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InGameViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
        private val repo: MainRepository,
    ) : ViewModel() {
        init {
            println("======================InGameViewModel===========================")
            println(repo.stringFlow)
            println("======================InGameViewModel===========================")
    }
}
