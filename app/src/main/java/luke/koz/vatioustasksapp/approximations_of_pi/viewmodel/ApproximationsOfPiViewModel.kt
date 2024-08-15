package luke.koz.vatioustasksapp.approximations_of_pi.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Math.pow
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Data class responsible for piApproximation ViewModel calculations
 */
data class NthDigitOfPiUiState (
    var givenNumber : Int = 3,
    var calculatedPi : Double = 0.0
)

/**
 * View Model providing business logic responsible for calculations inside piApproximation Screen
 */
class NthDigitOfPiViewModel : ViewModel(){

    private var _piUiState = MutableStateFlow(NthDigitOfPiUiState())
    val piUiState : StateFlow<NthDigitOfPiUiState> = _piUiState.asStateFlow()

    init {
        recalculateNumber()
    }

    fun recalculateNumber(){
        _piUiState.value.calculatedPi = calculatePi(_piUiState.value.givenNumber)
    }

    /**
     * Function responsible for getting user input into data class _piUiState for calculations inside calculatePi function.
     */
    fun assignNewNumber(n : Int) {
        if (_piUiState.value.givenNumber < 100) {
            _piUiState.value.givenNumber = n
        } else {
            _piUiState.value.givenNumber = 100
        }
    }

    /**
     * Function calculating pi approximation to 'k' decimal point, utilizing Bailey–Borwein–Plouffe formula.
     * The returned value is rounded down and assigned specified decimal point +2, for additional clarity.]
     */
    private fun calculatePi (k : Int) : Double {
        var pi = 0.0

        for (currentInt in 0..k) {
            pi += ((4.0 / (8.0 * currentInt + 1.0) - 2.0 / (8.0 * currentInt + 4.0) - 1.0 / (8.0 * currentInt + 5.0) - 1.0 / (8.0 * currentInt + 6.0)) * pow(
                16.0,
                -1.0 * currentInt
            ))
        }

        return BigDecimal(pi).setScale(k+2,RoundingMode.HALF_EVEN).toDouble()
    }
}