package luke.koz.vatioustasksapp.utils.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import luke.koz.vatioustasksapp.R
import luke.koz.vatioustasksapp.approximations_of_pi.presentation.ApproximationsOfPiScreen
import luke.koz.vatioustasksapp.hubscreen.presentation.HubDestinationElement
import luke.koz.vatioustasksapp.hubscreen.presentation.HubScreen
import luke.koz.vatioustasksapp.next_prime_number.presentation.PrimeNumberScreen
import luke.koz.vatioustasksapp.ui.theme.VatiousTasksAppTheme

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val destinationList : List<HubDestinationElement> = listOf(
        HubDestinationElement(
            destinationRoute = {navHostController.navigate(NavRoutesSerializable.ApproximationsOfPi)},
            destinationLabel = "Approximate π to specified digit",
            destinationImage = R.drawable.symbol_of_pi_icon
        ),
        HubDestinationElement(
            destinationRoute = {navHostController.navigate(NavRoutesSerializable.CalculateNextPrimeNumber)},
            destinationLabel = "Calculate Next PrimeNumber",
            destinationImage = R.drawable.icon_prime_number
        ),
    )

    Column (modifier = modifier){
        NavHost(navController = navHostController, startDestination = NavRoutesSerializable.HubScreen) {
            composable<NavRoutesSerializable.HubScreen> {
                HubScreen(list = destinationList)
            }

            composable<NavRoutesSerializable.ApproximationsOfPi> {
                Column {
                    ApproximationsOfPiScreen()
                }
            }

            composable<NavRoutesSerializable.CalculateNextPrimeNumber> {
                Column {
                   PrimeNumberScreen()
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