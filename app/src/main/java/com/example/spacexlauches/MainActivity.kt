package com.example.spacexlauches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacexlauches.ui.theme.SpaceXLauchesTheme
import com.example.spacexlauches.Screens.LaunchesScreen.LaunchListScreen
import com.example.spacexlauches.Screens.LaunchesScreen.LaunchDetailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXLauchesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController: NavHostController = rememberNavController()

                    NavHost(navController = navController, startDestination = "launch_list") {
                        composable("launch_list") {
                            LaunchListScreen(navController = navController)
                        }
                        composable("launch_details/{name}/{date}/{rocket}/{details}") { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
                            val date = backStackEntry.arguments?.getString("date") ?: "Unknown"
                            val rocket = backStackEntry.arguments?.getString("rocket") ?: "Unknown"
                            val details = backStackEntry.arguments?.getString("details") ?: "No details available"

                            LaunchDetailsScreen(name, date, rocket, details)
                        }
                    }
                }
            }
        }
    }
}
