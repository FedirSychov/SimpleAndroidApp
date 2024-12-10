package com.example.spacexlauches.screens.launches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.spacexlauches.styles.viewElements.LaunchItem
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchListScreen(
    viewModel: LaunchesViewModel = koinViewModel(),
    navController: NavController
) {
    val json = Json { ignoreUnknownKeys = true }
    val launches by viewModel.launches.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }

    fun  refresh() {
        isRefreshing = true
        viewModel.onRefresh()
    }

    LaunchedEffect(Unit) {
        viewModel.onCreate()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SpaceX Launches") }
            )
        }
    ) { padding ->
        PullToRefreshBox(isRefreshing = isRefreshing, onRefresh = ::refresh) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                itemsIndexed(launches) { index, launch ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        LaunchItem(launch = launch) {
                            val launchJson = json.encodeToJsonElement(launch)
                            navController.navigate(
                                "launch_details/$launchJson"
                            )
                        }
                        if (index < launches.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}