package com.titaniel.clockoutofclocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.titaniel.clockoutofclocks.ui.screens.ClockScreen
import com.titaniel.clockoutofclocks.ui.screens.ClockScreenWrapper
import com.titaniel.clockoutofclocks.ui.theme.ClockOutOfClocksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClockOutOfClocksTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    ClockScreenWrapper()

                }
            }
        }
    }
}