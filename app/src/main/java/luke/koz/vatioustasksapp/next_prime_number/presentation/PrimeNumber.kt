package luke.koz.vatioustasksapp.next_prime_number.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import luke.koz.vatioustasksapp.next_prime_number.viewmodel.PrimeNumberViewModel
import luke.koz.vatioustasksapp.ui.theme.VatiousTasksAppTheme

@Composable
fun PrimeNumberScreen(modifier: Modifier = Modifier, viewModel: PrimeNumberViewModel = viewModel()) {
    val primeNumberUiState by viewModel.localUiState.collectAsState()
    var displayedPrimeNumber by remember { mutableStateOf("") }
    var uiStoredInput by remember { mutableStateOf("") }

    /*
    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
    101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
    197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293.
     */

    Column (modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = displayedPrimeNumber,
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            modifier = Modifier.padding(16.dp),
            value = uiStoredInput,
            onValueChange = {
                uiStoredInput = it.ifBlank {
                    "0"
                }
                primeNumberUiState.currentNumberForCalculation = uiStoredInput.toInt()
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = {
                viewModel.calculateNextPrimeNumber(primeNumberUiState.currentNumberForCalculation)
                displayedPrimeNumber = primeNumberUiState.currentPrimeNumber.toString()
            })
        )
        Button(onClick = {
            viewModel.calculateNextPrimeNumber(primeNumberUiState.currentNumberForCalculation)
            displayedPrimeNumber = primeNumberUiState.currentPrimeNumber.toString()
        }) {
            Text(text = "Calculate next prime number")
        }
    }
}

@Preview
@Composable
private fun PrimeNumberScreenPreview() {
    VatiousTasksAppTheme {
        PrimeNumberScreen()
    }
}