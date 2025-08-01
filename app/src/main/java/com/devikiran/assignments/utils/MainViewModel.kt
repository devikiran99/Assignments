package com.devikiran.assignments.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devikiran.assignments.data.GHRepoData
import com.devikiran.assignments.utils.internet.InternetMonitor
import com.devikiran.assignments.utils.internet.InternetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private lateinit var internetMonitor: InternetMonitor

    private val _ghRepoDataState = MutableStateFlow<List<GHRepoData>>(emptyList())
    val ghRepoDataState: StateFlow<List<GHRepoData>> = _ghRepoDataState

    private val currentGhRepoDataList = arrayListOf<GHRepoData>()

    private val _navigateTo = MutableStateFlow<String>("loading")
    val navigateTo: StateFlow<String> = _navigateTo

    var ghRepoData: GHRepoData ?= null


    init {
        viewModelScope.launch {
            repository.getGhRepoData().collectLatest { data ->
                if (data.isNotEmpty()) {
                    currentGhRepoDataList.addAll(ArrayList(data))
                    _ghRepoDataState.update {
                        data
                    }
                    _navigateTo.update { "success" }
                }
                else {
                    _navigateTo.update { "failure" }
                }
            }
        }
    }

    fun register(context: Context) {
        viewModelScope.launch {
            internetMonitor = InternetMonitor(context)
            internetMonitor.register()
            handleNetworkConnectedState()
        }
    }

    fun unregister() {
        internetMonitor.unregister()
    }

    fun getDataFromServer() {
        viewModelScope.launch {
            repository.getGhRepoDataFromServer { state ->
                when (state) {
                    is AppDataEvent.OnFailure -> {
                        _navigateTo.update { "failure" }
                    }

                    AppDataEvent.OnLoading -> {
                        _navigateTo.update { "loading" }
                    }

                    is AppDataEvent.OnComplete -> {
                        _navigateTo.update { "success" }
                    }

                    is AppDataEvent.OnSuccess -> {}
                    AppDataEvent.OnInitialization -> {}
                }
            }
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {

            val filteredList = currentGhRepoDataList.filter {
                it.name.startsWith(name, ignoreCase = true)
            }

            _ghRepoDataState.update {
                filteredList
            }
        }
    }

    fun onItemClicked(ghRepoData: GHRepoData){
        viewModelScope.launch {
            this@MainViewModel.ghRepoData = ghRepoData
            _ghRepoDataState.update { currentGhRepoDataList }
            _navigateTo.update { "loadGitRepo" }
        }
    }

    fun onBackPressed(destination: String){
        viewModelScope.launch {
            _navigateTo.update { destination }
        }
    }

    private fun handleNetworkConnectedState() {
        viewModelScope.launch {
            internetMonitor.isConnected.collectLatest { isConnected ->
                when(isConnected){
                    InternetState.IDLE -> {
                        _navigateTo.update { "loading" }
                    }
                    InternetState.CONNECTED -> {
                        getDataFromServer()
                    }
                    InternetState.DISCONNECTED -> {
                        if (_ghRepoDataState.value.isEmpty()) {
                            _navigateTo.update { "failure" }
                        }
                    }
                }
            }
        }
    }
}