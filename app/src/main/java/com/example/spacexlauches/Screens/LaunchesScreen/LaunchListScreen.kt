import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spacexlauches.Screens.LaunchesScreen.SpaceXLaunchesViewModel
import com.example.spacexlaunches.Launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchListScreen(viewModel: SpaceXLaunchesViewModel = SpaceXLaunchesViewModel()) {
    val launches = viewModel.launches.collectAsState()
    val launchesTest2 = listOf(
        Launch(
            name = "Starlink-15",
            date_utc = "2022-08-08",
            rocket = "Falcon 9",
            details = "no det"
        ),
        Launch(
            name = "Starlink-15",
            date_utc = "2022-08-08",
            rocket = "Falcon 9",
            details = "no det"
        )
    )

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
                LaunchItem(launch = launch)
            }
        }
    }
}

@Composable
fun LaunchItem(launch: Launch) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = launch.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Date: ${launch.date_utc}", style = MaterialTheme.typography.bodyMedium)
        launch.details?.let { Text(text = it, style = MaterialTheme.typography.bodySmall) }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LaunchItemPreview() {
//    LaunchItem(
//        launch = Launch(
//            missionName = "Starlink-15",
//            launchDate = "2022-08-08",
//            rocketName = "Falcon 9"
//        )
//    )
//}
