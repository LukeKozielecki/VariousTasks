package luke.koz.vatioustasksapp.approximations_of_pi.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import luke.koz.vatioustasksapp.approximations_of_pi.viewmodel.NthDigitOfPiViewModel
import luke.koz.vatioustasksapp.ui.theme.VatiousTasksAppTheme

@Composable
fun ApproximationsOfPiScreen(modifier: Modifier = Modifier) {
    ApproximationsOfPi(modifier = modifier.fillMaxSize())
}

@Composable
private fun ApproximationsOfPi(modifier: Modifier = Modifier, viewModel: NthDigitOfPiViewModel = viewModel()) {
    val piUiState by viewModel.piUiState.collectAsState()

    var text by remember {
        mutableStateOf("")
    }
    var piApproximate by remember {
        mutableDoubleStateOf(piUiState.calculatedPi)
    }
    piApproximate = piUiState.calculatedPi
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = piApproximate.toString(), style = MaterialTheme.typography.displayLarge, modifier = Modifier.padding(vertical = 16.dp))
        TextField(
            value = text,
            onValueChange = {
                text = it
                try {
                    viewModel.assignNewNumber(it.toInt())
                } catch (x: NumberFormatException) {
                    viewModel.assignNewNumber(0)
                }
                viewModel.recalculateNumber()
                            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "0")},
            label = { Text(text = "Expected decimal (displayed +2)")}
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun ApproximationsOfPiPreview() {
    VatiousTasksAppTheme {
        ApproximationsOfPi()
    }
}