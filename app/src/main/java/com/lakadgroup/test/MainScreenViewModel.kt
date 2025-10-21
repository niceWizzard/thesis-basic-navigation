package com.lakadgroup.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random


enum class MainScreenState {
    Initial,
    Generated,
    WarmingUp,
    Processing,
    Generating,
}

class MainScreenViewModel : ViewModel() {

    var mainScreenState = MutableStateFlow(MainScreenState.Initial)
    var rngOutput = MutableStateFlow<Int?>(null)

    var minOutput = MutableStateFlow(0)
    var maxOutput = MutableStateFlow(1000)

    fun onGenerateButtonClick() {
        viewModelScope.launch {
            mainScreenState.value = MainScreenState.WarmingUp
            delay(1000)
            mainScreenState.value = MainScreenState.Processing
            delay(1000)
            mainScreenState.value = MainScreenState.Generating
            delay(1000)

            rngOutput.value = Random.nextInt(minOutput.value, maxOutput.value)
            mainScreenState.value = MainScreenState.Generated
        } // launch
    } // function

}