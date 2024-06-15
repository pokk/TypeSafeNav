package com.compose.myapplication.second

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.compose.myapplication.data.repository.MainRepository
import com.compose.myapplication.second.navigation.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SecondViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
        private val repo: MainRepository,
    ) : ViewModel() {
        init {
            println("this is second viewmodel init block")
            println(repo)
            println(repo.stringFlow)
            viewModelScope.launch {
                repo.stringFlow.collect {
                    println("second view model receive update : $it")
                }
        }
    }

        fun testArgs() {
            val args = savedStateHandle.toRoute<Book>()
            println("=================================================")
            println(args.name)
            println("=================================================")
        }
}
