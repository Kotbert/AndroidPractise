package com.example.m17_recyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.m17_recyclerview.navigation.NavGraph
import com.example.m17_recyclerview.ui.MainViewModel
import com.example.m17_recyclerview.ui.PhotoComponent
import com.example.m17_recyclerview.ui.theme.M17_recyclerviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            NavGraph(navController = rememberNavController(), mainViewModel = viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val recView by viewModel.uiState.collectAsState()
    M17_recyclerviewTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (recView.loading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }
            if (!recView.loading) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(
                        viewModel.uiState.value.marsRoverPhoto?.photos ?: listOf()
                    ) { index, photo ->
                        Button(onClick = { navController.navigate("Detail/$index") }) {
                            PhotoComponent(photo)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}