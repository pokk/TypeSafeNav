package com.compose.myapplication.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.compose.myapplication.MyApplication
import com.compose.myapplication.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InGameViewModel
    @Inject
    constructor(
        private val savedStateHandle: SavedStateHandle,
        private val repo: MainRepository,
        application: Application,
    ) : AndroidViewModel(application = application) {
        init {
            println("======================InGameViewModel===========================")
            println(repo.stringFlow)
            println("======================InGameViewModel===========================")
        }

        fun changeConfiguration() {
            getApplication<MyApplication>().apply {
                val newConfig = resources.configuration.apply {
                    setLocale(Locale.JAPAN)
                }
                createConfigurationContext(newConfig)
            }
        }
    }
