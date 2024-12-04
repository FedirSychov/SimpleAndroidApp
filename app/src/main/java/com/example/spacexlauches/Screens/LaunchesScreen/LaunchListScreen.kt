package com.example.spacexlauches.Screens.LaunchesScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.spacexlaunches.Launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchListScreen(
    viewModel: SpaceXLaunchesViewModel = SpaceXLaunchesViewModel(),
    navController: NavController
) {
    val launches = viewModel.launches.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SpaceX Launches") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(launches.value) { launch ->
                LaunchItem(launch = launch) {
                    navController.navigate(
                        "launch_details/${launch.name}/${launch.date_utc}/${launch.rocket}/${launch.details ?: "No details"}"
                    )
                }
            }
        }
    }
}

@Composable
fun LaunchItem(launch: Launch, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = launch.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Date: ${launch.date_utc}", style = MaterialTheme.typography.bodyMedium)
    }
}
