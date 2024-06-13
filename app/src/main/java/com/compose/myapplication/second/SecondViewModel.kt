package com.compose.myapplication.second

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.compose.myapplication.second.navigation.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        fun testArgs() {
            val args = savedStateHandle.toRoute<Book>()
            println("=================================================")
            println(args.name)
            println("=================================================")
    }
}
