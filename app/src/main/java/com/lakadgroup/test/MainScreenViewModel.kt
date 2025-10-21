package com.lakadgroup.test

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {


    var rngOutput : MutableState<Int?> = mutableStateOf(null)

    var minOutput : MutableState<Int> = mutableIntStateOf(0)
    var maxOutput : MutableState<Int> = mutableIntStateOf(1000)

}