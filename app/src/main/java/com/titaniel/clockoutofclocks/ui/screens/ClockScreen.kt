package com.titaniel.clockoutofclocks.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ClockViewModel @Inject constructor() : ViewModel() {

    val number = flow {

        while (true) {

            LocalDateTime.now().let {

                val hours = it.hour.toString().padStart(2, '0')

                val mins = it.minute.toString().padStart(2, '0')

                val secs = it.second.toString().padStart(2, '0')

                emit(
                    listOf(
                        hours[0].digitToInt(),
                        hours[1].digitToInt(),
                        mins[0].digitToInt(),
                        mins[1].digitToInt(),
                        secs[0].digitToInt(),
                        secs[1].digitToInt()
                    )
                )
            }

            delay(1000)
        }

    }

}

enum class ClockState(val smallDegrees: Float, val bigDegrees: Float) {
    BOTTOM_RIGHT(90f, 180f),
    BOTTOM_LEFT(270f, 180f),
    TOP_RIGHT(90f, 0f),
    TOP_LEFT(270f, 0f),
    HORIZONTAL(270f, 90f),
    VERTICAL(180f, 0f),
    LEFT(270f, 270f),
    RIGHT(90f, 90f),
    TOP(0f, 0f),
    BOTTOM(180f, 180f),
    INACTIVE(225f, 225f)
}

@Composable
fun ClockScreenWrapper(viewModel: ClockViewModel = viewModel()) {

    val number by viewModel.number.collectAsState(initial = listOf(0, 0, 0, 0))

    ClockScreen(numbers = number)

}

@Composable
fun ClockScreen(numbers: List<Int>) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Row {

            numbers.forEach { num ->

                Number(number = num)

                Spacer(modifier = Modifier.width(0.dp))
            }

        }
    }


}

@Composable
fun Number(number: Int) {

    var stateTopLeft = remember { ClockState.HORIZONTAL }
    var stateCenterLeft = remember { ClockState.HORIZONTAL }
    var stateBottomLeft = remember { ClockState.HORIZONTAL }
    var stateTopRight = remember { ClockState.HORIZONTAL }
    var stateCenterRight = remember { ClockState.HORIZONTAL }
    var stateBottomRight = remember { ClockState.HORIZONTAL }

    when (number) {
        0 -> {

            stateTopLeft = ClockState.BOTTOM_RIGHT
            stateCenterLeft = ClockState.VERTICAL
            stateBottomLeft = ClockState.TOP_RIGHT

            stateTopRight = ClockState.BOTTOM_LEFT
            stateCenterRight = ClockState.VERTICAL
            stateBottomRight = ClockState.TOP_LEFT
        }
        1 -> {

            stateTopLeft = ClockState.INACTIVE
            stateCenterLeft = ClockState.INACTIVE
            stateBottomLeft = ClockState.INACTIVE

            stateTopRight = ClockState.BOTTOM
            stateCenterRight = ClockState.VERTICAL
            stateBottomRight = ClockState.TOP
        }
        2 -> {

            stateTopLeft = ClockState.RIGHT
            stateCenterLeft = ClockState.BOTTOM_RIGHT
            stateBottomLeft = ClockState.TOP_RIGHT

            stateTopRight = ClockState.BOTTOM_LEFT
            stateCenterRight = ClockState.TOP_LEFT
            stateBottomRight = ClockState.LEFT

        }
        3 -> {

            stateTopLeft = ClockState.RIGHT
            stateCenterLeft = ClockState.RIGHT
            stateBottomLeft = ClockState.RIGHT

            stateTopRight = ClockState.BOTTOM_LEFT
            stateCenterRight = ClockState.TOP_LEFT
            stateBottomRight = ClockState.TOP_LEFT

        }
        4 -> {

            stateTopLeft = ClockState.BOTTOM
            stateCenterLeft = ClockState.TOP_RIGHT
            stateBottomLeft = ClockState.INACTIVE

            stateTopRight = ClockState.BOTTOM
            stateCenterRight = ClockState.TOP_LEFT
            stateBottomRight = ClockState.TOP

        }
        5 -> {

            stateTopLeft = ClockState.BOTTOM_RIGHT
            stateCenterLeft = ClockState.TOP_RIGHT
            stateBottomLeft = ClockState.RIGHT

            stateTopRight = ClockState.LEFT
            stateCenterRight = ClockState.BOTTOM_LEFT
            stateBottomRight = ClockState.TOP_LEFT

        }
        6 -> {

            stateTopLeft = ClockState.BOTTOM_RIGHT
            stateCenterLeft = ClockState.VERTICAL
            stateBottomLeft = ClockState.TOP_RIGHT

            stateTopRight = ClockState.LEFT
            stateCenterRight = ClockState.BOTTOM_LEFT
            stateBottomRight = ClockState.TOP_LEFT

        }
        7 -> {

            stateTopLeft = ClockState.RIGHT
            stateCenterLeft = ClockState.INACTIVE
            stateBottomLeft = ClockState.INACTIVE

            stateTopRight = ClockState.BOTTOM_LEFT
            stateCenterRight = ClockState.VERTICAL
            stateBottomRight = ClockState.TOP

        }
        8 -> {

            stateTopLeft = ClockState.BOTTOM_RIGHT
            stateCenterLeft = ClockState.TOP_RIGHT
            stateBottomLeft = ClockState.TOP_RIGHT

            stateTopRight = ClockState.BOTTOM_LEFT
            stateCenterRight = ClockState.TOP_LEFT
            stateBottomRight = ClockState.TOP_LEFT

        }
        9 -> {

            stateTopLeft = ClockState.BOTTOM_RIGHT
            stateCenterLeft = ClockState.TOP_RIGHT
            stateBottomLeft = ClockState.RIGHT

            stateTopRight = ClockState.BOTTOM_LEFT
            stateCenterRight = ClockState.VERTICAL
            stateBottomRight = ClockState.TOP_LEFT

        }
    }

    val spacing = 8.dp

    Row {
        Column {

            MiniClock(clockState = stateTopLeft)

            Spacer(modifier = Modifier.height(spacing))

            MiniClock(clockState = stateCenterLeft)

            Spacer(modifier = Modifier.height(spacing))

            MiniClock(clockState = stateBottomLeft)
        }

        Spacer(modifier = Modifier.width(spacing))

        Column {

            MiniClock(clockState = stateTopRight)

            Spacer(modifier = Modifier.height(spacing))

            MiniClock(clockState = stateCenterRight)

            Spacer(modifier = Modifier.height(spacing))

            MiniClock(clockState = stateBottomRight)
        }
    }

}

@Composable
fun MiniClock(clockState: ClockState) {
    MiniClock(bigDeg = clockState.bigDegrees, smallDeg = clockState.smallDegrees)
}

@Composable
fun MiniClock(bigDeg: Float, smallDeg: Float) {

    val animSpec = tween<Float>(800)

    val smallDegAnim by animateFloatAsState(targetValue = smallDeg, animationSpec = animSpec)
    val bigDegAnim by animateFloatAsState(targetValue = bigDeg, animationSpec = animSpec)

    val unitSize = 6.dp

    val size = unitSize * 7

    val pointerShorter = size / 2 - unitSize / 2

    Box(
        modifier = Modifier
            .size(size),
        contentAlignment = Alignment.Center
    ) {

        // Small one
        Box(
            modifier = Modifier
                .rotate(smallDegAnim)
                .padding(bottom = pointerShorter, top = 0.dp /*unitSize*/)
                .size(unitSize, size)
                .background(color = MaterialTheme.colors.onBackground, shape = RoundedCornerShape(100))
        )

        // Big one
        Box(
            modifier = Modifier
                .rotate(bigDegAnim)
                .padding(bottom = pointerShorter)
                .size(unitSize, size)
                .background(color = MaterialTheme.colors.onBackground, shape = RoundedCornerShape(100))
        )

    }

}