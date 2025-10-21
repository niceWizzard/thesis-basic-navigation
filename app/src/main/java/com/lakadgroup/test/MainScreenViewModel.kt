package com.lakadgroup.test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainScreenViewModel : ViewModel() {


    var rngOutput = MutableStateFlow<Int?>(null)

    var minOutput = MutableStateFlow(0)
    var maxOutput = MutableStateFlow(1000)

}