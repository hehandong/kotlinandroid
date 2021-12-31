package com.example.kotlinandroid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MainViewModel(
    private val dataSource: DataSource
) : ViewModel() {


    companion object {
        // Real apps would use a wrapper on the result type to handle this.
        const val LOADING_STRING = "Loading..."
    }

    // Exposed liveData that emits and single value and subsequent values from another source.
    val currentWeather: LiveData<String> = liveData {
        emit(LOADING_STRING)
        emitSource(dataSource.fetchWeather())
    }



    /**
     * Factory for [LiveDataViewModel].
     */
    object LiveDataVMFactory : ViewModelProvider.Factory {

        private val dataSource = LiveDataSource(Dispatchers.IO)

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dataSource) as T
        }
    }
}