package luke.koz.vatioustasksapp.next_prime_number.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PrimeNumberUiState (
    var currentPrimeNumber : Int = 0,
    var currentNumberForCalculation : Int = 0,
)

class PrimeNumberViewModel : ViewModel() {

    private var _localUiState = MutableStateFlow(PrimeNumberUiState())
    var localUiState : StateFlow<PrimeNumberUiState> = _localUiState.asStateFlow()

    /**
     * Function responsible for calculation of nearest prime number
     */
    fun calculateNextPrimeNumber (givenNumber : Int) {
        var currentGivenNumber : Int = if (givenNumber < 0) {
            1
        } else {
            givenNumber
        }
        var assignedNumber : Boolean = false
        while (!assignedNumber) {
            val isPrimeNumber : Boolean = checkCurrentNumber(currentGivenNumber)
            if (isPrimeNumber) {
                _localUiState.value.currentPrimeNumber = currentGivenNumber
                assignedNumber = true
            } else {
                currentGivenNumber++
            }
        }
    }

    /**
     * Inner function responsible for checking if given number is prime
     */
    private fun checkCurrentNumber (givenNumber: Int) : Boolean {
        var i = 2
        while (i <= givenNumber / 2) {
            // exit if number proven to not be prime
            if (givenNumber % i == 0) {
                return false
            }
            ++i
        }
        return true
    }
}