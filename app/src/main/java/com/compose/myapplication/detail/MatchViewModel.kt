package com.compose.myapplication.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.myapplication.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MatchViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
        private val repo: MainRepository,
    ) : ViewModel() {
        init {
            println("Match View Model Run init Block")
            println(repo)
            println(repo.stringFlow)
            viewModelScope.launch {
                repo.stringFlow.collect {
                    println("match view model receive update : $it")
                }
            }
        }
    }
