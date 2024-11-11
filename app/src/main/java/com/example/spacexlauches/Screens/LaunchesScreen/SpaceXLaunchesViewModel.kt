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
        Log.d("startTag", "started") // Эта строка выводится, значит, метод вызывается
        viewModelScope.launch {
            try {
                Log.d("TAG", "Attempting to get launches") // Добавьте лог, чтобы увидеть, что корутина запускается
                val response = SpaceXApiClient.apiService.getLaunches()

                Log.d("TAG1", response.message()) // Проверка, что мы получаем ответ от сервера

                // Если код дойдет сюда, значит, запрос выполнен, выводим результат
                Log.d("TAG2", "Response: $response")

                // Проверим успешность запроса
                if (response.isSuccessful) {
                    Log.d("TAG3", "Successful response") // Печатаем успех, если ответ успешный
                    _launches.value = response.body()?.takeLast(5)?.reversed() ?: emptyList()
                } else {
                    Log.e("TAG", "Error: ${response.message()}") // Логируем ошибку, если запрос не успешен
                }
            } catch (e: Exception) {
                Log.e("TAG", "Exception: ${e.message}") // Логируем исключения
                e.printStackTrace()
            }
        }
    }

}