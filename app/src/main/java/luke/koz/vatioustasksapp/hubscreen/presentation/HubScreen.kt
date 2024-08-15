package luke.koz.vatioustasksapp.hubscreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import luke.koz.vatioustasksapp.R
import luke.koz.vatioustasksapp.ui.theme.VatiousTasksAppTheme

data class HubDestinationElement (
    val destinationRoute : () -> Unit,
    val destinationLabel : String,
    val destinationImage : Int
)

@Composable
fun HubScreen(modifier: Modifier = Modifier, list: List<HubDestinationElement>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(list.size) {
            HubGridElement(element = list[it])
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HubGridElement(modifier: Modifier = Modifier, element: HubDestinationElement) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .sizeIn(minWidth = 240.dp, minHeight = 100.dp)
            .padding(8.dp),
        onClick = element.destinationRoute::invoke
    ) {
        Column (modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id = element.destinationImage),
                contentDescription = null,
                modifier = Modifier
                    .sizeIn(minWidth = 120.dp, minHeight = 50.dp)
            )
            Text(text = element.destinationLabel)
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun HubScreenPreview() {
    VatiousTasksAppTheme {
        HubScreen(
            list = listOf(
                HubDestinationElement(
                    destinationRoute = {},
                    destinationLabel = "Approximate π to specified digit",
                    destinationImage = R.drawable.symbol_of_pi_icon
                ),
                HubDestinationElement(
                    destinationRoute = {},
                    destinationLabel = "Find e to the Nth Digit",
                    destinationImage = R.drawable.ic_launcher_foreground
                ),
                HubDestinationElement(
                    destinationRoute = {},
                    destinationLabel = "Fibonacci Sequence",
                    destinationImage = R.drawable.ic_launcher_foreground
                )
            )
        )
    }
}