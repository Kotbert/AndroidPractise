package com.example.quizresources

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopBar(navController: NavController, titleName: String) {
    Row(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Button(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            onClick = { navController.popBackStack() }) {
            Image(painter = painterResource(id = R.drawable.ic_backspace), contentDescription = "")
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = titleName)
            Spacer(modifier = Modifier.size(50.dp))
        }
    }
}