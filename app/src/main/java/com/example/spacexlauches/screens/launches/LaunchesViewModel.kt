package com.example.spacexlauches.screens.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlauches.models.Launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.example.spacexlauches.repos.SpaceXApiRepo
import kotlinx.coroutines.flow.asStateFlow

class LaunchesViewModel(private val spaceXApiRepo: SpaceXApiRepo): ViewModel() {

    private val _launches: MutableStateFlow<List<Launch>> = MutableStateFlow(emptyList())
    val launches: StateFlow<List<Launch>> = _launches.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var overflow: List<Launch> = listOf()

    fun onCreate() {
        _isLoading.value = true
        getLaunches()
    }

    fun onRefresh() {
        getLaunches()
    }

    private fun getLaunches() {
        Log.d("SERVICE", "Will get launches")
        viewModelScope.launch {
            val launches = spaceXApiRepo.getLaunches().sortedByDescending { it.date_utc }
            val currentLaunches = if (launches.size > 20) launches.subList(0, 20) else launches
            val overflowLaunches = if (launches.size > 20) launches.subList(20, launches.size - 1) else emptyList()
            overflow = overflowLaunches
            _launches.value = currentLaunches
            _isLoading.value = false
        }
    }

}