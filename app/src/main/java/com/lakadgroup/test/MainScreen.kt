package com.lakadgroup.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.abs


fun intToBinary(input : Int) : String {
    val positiveInput = abs(input)

    // divide method
    var quotient = positiveInput
    var binaryString = ""

    // Safety tip for while loop (add limiter)
    var iteration = 0
    while(iteration < 10_000 && quotient > 0) {
        binaryString += (quotient % 2).toString()
        quotient /= 2
        iteration++
    }

    return  binaryString.reversed()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Binary Converter") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            var decimalInput by remember { mutableStateOf("") }
            var binaryOutput by remember { mutableStateOf("") }
            OutlinedTextField(
                value = decimalInput,
                onValueChange = { value ->
                    if(value.isEmpty() || value.toFloatOrNull() != null)
                        decimalInput = value.trim()
                },
                label = { Text("Enter a decimal number") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                    binaryOutput = intToBinary(decimalInput.toInt())
                }
            ) {
                Text("Convert")
            }
            if(binaryOutput.isNotEmpty()) {
                Text("Binary is: $binaryOutput")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
