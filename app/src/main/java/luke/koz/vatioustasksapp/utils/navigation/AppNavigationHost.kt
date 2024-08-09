package luke.koz.vatioustasksapp.utils.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import luke.koz.vatioustasksapp.ui.theme.VatiousTasksAppTheme

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    Column (modifier = modifier){
        NavHost(navController = navHostController, startDestination = NavRoutesSerializable.DummyTask1) {
            composable<NavRoutesSerializable.DummyTask1> {
                Column {
                    Text(text = "Screen 1")
                    Button(onClick = {
                        navHostController.navigate(NavRoutesSerializable.DummyTask2)
                    }) {
                        Text(text = "LoremIpsum1")
                    }
                    Button(onClick = {
                        navHostController.navigate(NavRoutesSerializable.DummyTask2)
                    }) {
                        Text(text = "LoremIpsum2")
                    }
                    Button(onClick = {
                        navHostController.navigate(NavRoutesSerializable.DummyTask2)
                    }) {
                        Text(text = "LoremIpsum3")
                    }
                }
            }

            composable<NavRoutesSerializable.DummyTask2> {
                Column {
                    Text(text = "Screen 2")
                }
            }

            composable<NavRoutesSerializable.DummyTask3> {
                Column {
                    Text(text = "Screen 3")
                }
            }
        }
    }
}

@Preview
@Composable
private fun AppNavigationHostPreview() {
    VatiousTasksAppTheme {
        AppNavigationHost()
    }
}