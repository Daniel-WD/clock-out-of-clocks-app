package com.titaniel.clockoutofclocks.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ClockViewModel : ViewModel() {



}

@Composable
fun ClockScreenWrapper(viewModel: ClockViewModel = viewModel()) {

    ClockScreen()

}

@Composable
fun ClockScreen() {

    Text(text = "Hello")

}