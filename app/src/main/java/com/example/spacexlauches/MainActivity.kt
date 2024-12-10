package com.example.spacexlauches

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacexlauches.di.appModule
import com.example.spacexlauches.models.Launch
import com.example.spacexlauches.screens.launchDetails.LaunchDetailsScreen
import com.example.spacexlauches.screens.launches.LaunchListScreen
import com.example.spacexlauches.ui.theme.SpaceXLauchesTheme
import kotlinx.serialization.json.Json
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinApplication(application = {
                modules(appModule)
            }) {
                SpaceXLauchesTheme {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        val navController: NavHostController = rememberNavController()

                        NavHost(navController = navController, startDestination = "launch_list") {
                            composable("launch_list") {
                                LaunchListScreen(navController = navController)
                            }
                            composable("launch_details/{launch}") { backStackEntry ->
                                Log.d("NAVIGATION", "Args: ${backStackEntry.arguments}")
                                val launchString: String? = backStackEntry.arguments?.getString("launch")
                                if (launchString == null) throw IllegalArgumentException("Launch argument is required")
                                val launch: Launch = Json.decodeFromString(launchString)
                                LaunchDetailsScreen(launch)
                            }
                        }
                    }
                }
            }
        }
    }
}
