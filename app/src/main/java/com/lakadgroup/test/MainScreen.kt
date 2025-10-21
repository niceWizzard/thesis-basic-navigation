package com.lakadgroup.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Random Number Generator") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,

        ) {
            val mainScreenViewModel : MainScreenViewModel = viewModel()

            val rngOutput by mainScreenViewModel.rngOutput.collectAsState()
            val minOutput by mainScreenViewModel.minOutput.collectAsState()
            val maxOutput by mainScreenViewModel.maxOutput.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        mainScreenViewModel.rngOutput.value = Random.nextInt(minOutput, maxOutput)
                    },
                    modifier = Modifier.size(128.dp),
                ) {
                    Text(
                        text = "Generate",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    )
                }

                if(rngOutput != null)
                    Text("RNG Value: $rngOutput")

            } // End Column

            MinMaxTextFields(
                minOutput = minOutput,
                maxOutput = maxOutput,
                setMinOutput = { it ->
                    mainScreenViewModel.minOutput.value = it
                },
                setMaxOutput = { it ->
                    mainScreenViewModel.maxOutput.value = it
                },
            )


        } // End Column

    } // End Scaffold
}


@Composable
fun MinMaxTextFields(
    minOutput : Int,
    maxOutput : Int,
    setMinOutput: (Int) -> Unit,
    setMaxOutput: (Int) -> Unit,
) {
    // Text Fields
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            value = minOutput.toString(),
            onValueChange = { changed ->
                if(changed.isEmpty()) {
                    setMinOutput(0)
                }
                changed.toIntOrNull()?.let { validInteger ->
                    if(validInteger < maxOutput)
                        setMinOutput(validInteger)
                }
            },
            label = {
                Text("Minimum: ")
            },
            modifier = Modifier.weight(.5f),
        )
        OutlinedTextField(
            value = maxOutput.toString(),
            onValueChange = { changedInput ->
                if(changedInput.isEmpty()) {
                   setMaxOutput(1000)
                }
                (changedInput.toIntOrNull())?.let { validInteger ->
                    if(validInteger > minOutput)
                        setMaxOutput(validInteger)
                }
            },
            label = {
                Text("Maximum: ")
            },
            modifier = Modifier.weight(.5f)
        )
    } // End Row
}



@Preview(
    showSystemUi = true
)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}

