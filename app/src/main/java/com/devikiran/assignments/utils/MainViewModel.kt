package com.devikiran.assignments.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devikiran.assignments.data.AppData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val  repository: MainRepository) : ViewModel(){

    private lateinit var networkMonitor: NetworkMonitor

    private val _appDataState = MutableStateFlow<List<AppData>>(emptyList())
    val appDataState: StateFlow<List<AppData>> = _appDataState

    private val _navigateTo = MutableStateFlow<String>("loading")
    val navigateTo: StateFlow<String> = _navigateTo



    init {
        viewModelScope.launch {
            repository.getAppData().collectLatest { data ->
                if(data.isNotEmpty()) {
                    _appDataState.update {
                        data
                    }
                    _navigateTo.update { "success" }
                } else {
                    _navigateTo.update { "failure" }
                }
            }
        }
    }

    fun register(context: Context) {
        viewModelScope.launch {
            networkMonitor = NetworkMonitor(context)
            networkMonitor.register()
            handleNetworkConnectedState()
        }
    }

    fun unregister(){
        networkMonitor.unregister()
    }

    fun getDataFromServer() {
        viewModelScope.launch {
            repository.getAppDataFromServer { state ->
                when(state) {
                    is AppDataEvent.OnFailure -> {
                        _navigateTo.update { "failure" }
                    }
                    AppDataEvent.OnLoading -> {
                        _navigateTo.update { "loading" }
                    }
                    is AppDataEvent.OnComplete -> {
                        _navigateTo.update { "success" }
                    }
                    is AppDataEvent.OnSuccess -> { }
                    AppDataEvent.OnInitialization ->{ }
                }
            }
        }

    }

    private fun handleNetworkConnectedState() {
        viewModelScope.launch {
            networkMonitor.isConnected.collectLatest { isConnected ->
                if(isConnected) {
                    getDataFromServer()
                }else{
                    if(_appDataState.value.isEmpty()) {
                        _navigateTo.update { "failure" }
                    }
                }
            }
        }
    }
}