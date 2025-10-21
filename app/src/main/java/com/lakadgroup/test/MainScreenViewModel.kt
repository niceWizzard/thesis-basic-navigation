package com.lakadgroup.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainScreenViewModel : ViewModel() {

    var isLoading = MutableStateFlow(false)
    var rngOutput = MutableStateFlow<Int?>(null)

    var minOutput = MutableStateFlow(0)
    var maxOutput = MutableStateFlow(1000)

    fun onGenerateButtonClick() {
        viewModelScope.launch {
            isLoading.value = true
            delay(1000)
            rngOutput.value = Random.nextInt(minOutput.value, maxOutput.value)
            isLoading.value = false

        } // launch
    } // function

}