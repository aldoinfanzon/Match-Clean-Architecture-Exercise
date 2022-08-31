package com.neuronride.match.cleanarch.application.ui.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neuronride.match.cleanarch.application.ui.theme.MatchCleanArchitectureExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatchCleanArchitectureExampleTheme {
                MainScreen(viewModel())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MatchCleanArchitectureExampleTheme {
        MainScreen()
    }
}