package com.example.components

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.ui.theme.ComponentsTheme
import kotlin.math.roundToInt

var timer: CountDownTimer? = null
var isActive by mutableStateOf(false)
var sliderPosition by mutableStateOf(0f)
var timerValue by mutableStateOf(0f)
var maxTimerValue by mutableStateOf(1)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentsTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        modifier = Modifier.padding(top = 30.dp, bottom = 30.dp),
                        text = "Таймер",
                        fontSize = 40.sp
                    )

                    Box(
                        modifier = Modifier.size(200.dp, 200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(200.dp, 200.dp),
                            progress = (timerValue / maxTimerValue) / 1000,
                            strokeWidth = 5.dp
                        )
                        Text(text = "${(timerValue / 1000).roundToInt()}", fontSize = 36.sp)
                    }
                    Slider(value = sliderPosition,
                        enabled = !isActive,
                        valueRange = 0f..60f,
                        steps = 5,
                        onValueChange = { sliderPosition = it })

                    Button(onClick = {
                        if (!isActive) startCountTimer(sliderPosition.toLong() * 1000)
                        else {
                            timer?.onFinish()
                            timer?.cancel()
                        }
                    }) {
                        Text(text = if (!isActive) "Старт" else "Рестарт")
                    }
                }
            }
        }
    }
}

fun startCountTimer(timeMillis: Long) {
    maxTimerValue = (timeMillis / 1000).toInt()
    timer?.cancel()
    isActive = true
    timer = object : CountDownTimer(timeMillis, 1) {
        override fun onTick(timeM: Long) {
            timerValue = timeM.toFloat()
        }

        override fun onFinish() {
            timerValue = 0f
            isActive = false
        }
    }.start()
}