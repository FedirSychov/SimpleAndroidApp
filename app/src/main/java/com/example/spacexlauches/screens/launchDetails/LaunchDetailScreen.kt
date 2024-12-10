package com.example.spacexlauches.screens.launchDetails

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spacexlauches.models.Launch

@Composable
fun LaunchDetailsScreen(launch: Launch) {

    val name = launch.name
    val date = launch.date_utc
    val rocket = launch.rocket
    val details = launch.details

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Launch: $name", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Date: $date", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rocket: $rocket", style = MaterialTheme.typography.bodyLarge)
            if (details != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Details: $details", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
