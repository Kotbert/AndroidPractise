package com.example.quizresources.screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizresources.R
import com.example.quizresources.navigation.QuizStorageNavRoute
import com.example.quizresources.ui.theme.opensans
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@Preview(showBackground = true)
@Composable
fun StartScreen(navController: NavController = rememberNavController()) {
    val scope = rememberCoroutineScope()
    var mDate by remember { mutableStateOf("") }
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    mCalendar.time = Date()
    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)
    val scaffoldState = rememberScaffoldState()
    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate = "$mDayOfMonth/${mMonth + 1}/$mYear"
            scope.launch { scaffoldState.snackbarHostState.showSnackbar(mDate) }
        }, mYear, mMonth, mDay

    )
    Scaffold(scaffoldState = scaffoldState, topBar = {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.chooseDate) + mDate,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(10.dp))
            Button(modifier = Modifier.padding(16.dp), onClick = {
                mDatePickerDialog.show()

            }) {
                Text(text = stringResource(R.string.date))
            }
        }
    }, content = { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.welcome_picture),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.startPant),
                    fontFamily = opensans,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = { navController.navigate(route = QuizStorageNavRoute.Question1.route) }) {
                    Text(text = stringResource(R.string.startQUIZ))
                }
            }
        }
    })
}

