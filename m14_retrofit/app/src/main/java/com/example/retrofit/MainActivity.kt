package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.userData.Info
import com.example.retrofit.userData.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val error = MutableStateFlow("Запроса не было")
        val userData: MutableStateFlow<UserData> = MutableStateFlow(
            UserData(
                results = listOf(),
                info = Info(1,1,"","")
            )
        )


        RetrofitInstance.searchUserAPI.getUserData().enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                userData.value = response.body()!!
                error.value = "Запрос успешен"
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                error.value = t.toString()
            }

        }
        )
        setContent {
            RetrofitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrintUserData(results =userData, error = error)
                }
            }
        }
    }
}

@Composable
fun PrintUserData(
    modifier: Modifier = Modifier,
    results: MutableStateFlow<UserData>,
    error: MutableStateFlow<String>,
) {
    val userData by results.collectAsState()
    val someError by error.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = someError)
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = userData.toString(),
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }
}