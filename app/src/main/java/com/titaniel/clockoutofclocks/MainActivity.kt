package com.titaniel.clockoutofclocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.titaniel.clockoutofclocks.ui.screens.ClockScreen
import com.titaniel.clockoutofclocks.ui.screens.ClockScreenWrapper
import com.titaniel.clockoutofclocks.ui.theme.ClockOutOfClocksTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClockOutOfClocksTheme {

                SystemUiSettings()

                Surface(color = MaterialTheme.colors.background) {

                    ClockScreenWrapper()

                }
            }
        }
    }
}

@Composable
fun SystemUiSettings() {

    // Get ui controller
    val systemUiController = rememberSystemUiController()

    // Hide system bars
    systemUiController.isSystemBarsVisible = false

}