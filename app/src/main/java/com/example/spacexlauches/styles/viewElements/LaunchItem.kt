package com.example.spacexlauches.styles.viewElements

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spacexlauches.models.Launch

@Composable
fun LaunchItem(launch: Launch, onClick: (launch: Launch) -> Unit) {

    val name = launch.name?: "UNDEFINED"
    val date = launch.date_utc?: "UNDEFINED"

    fun userTappedLaunch() {
        Log.d("VIEW", "User tapped launch: $launch")
        onClick(launch)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = true, onClick = ::userTappedLaunch)
            .padding(16.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Date: $date", style = MaterialTheme.typography.bodyMedium)
    }
}