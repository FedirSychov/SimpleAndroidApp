package com.example.spacexlauches.Screens.LaunchesScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.Launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class SpaceXLaunchesViewModel: ViewModel() {
    private val _launches = MutableStateFlow<List<Launch>>(emptyList())
    val launches: StateFlow<List<Launch>> = _launches

    init {
        getLaunches()
    }

    private fun getLaunches() {
        Log.d("startTag", "started")
        viewModelScope.launch {
            try {
                val response = SpaceXApiClient.apiService.getLaunches()

                if (response.isSuccessful) {
                    _launches.value = response.body()?.takeLast(5)?.reversed() ?: emptyList()
                } else {
                    Log.e("TAG", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("TAG", "Exception: ${e.message}")
                e.printStackTrace()
            }
        }
    }

}