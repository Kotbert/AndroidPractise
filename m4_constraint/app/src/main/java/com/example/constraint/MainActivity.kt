package com.example.constraint

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val someMusic = MediaPlayer.create(LocalContext.current, R.raw.i_will_rule_the_universe)
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround) {
                Box(modifier = Modifier.size(300.dp, 300.dp), contentAlignment = Alignment.Center) {

                    Image(painter = painterResource(id = R.drawable.i_will_rule_the_universe),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize())
                }

                PlayMusic(author = "Civil War",
                    songName = "I will rule the Universe",
                    music = someMusic)
            }

        }
    }
}

@Composable
fun PlayMusic(author: String, songName: String, music: MediaPlayer) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {

        Text(text = songName,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            fontSize = 25.sp)
        Text(text = author,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            fontSize = 20.sp)

        ControlPanel(iconSkipPrevious = R.drawable.ic_skip_previous,
            iconFastRewind = R.drawable.ic_fast_rewind,
            iconPlay = R.drawable.ic_play,
            iconPause = R.drawable.ic_pause,
            iconForward = R.drawable.ic_fast_forward,
            iconSkipNext = R.drawable.ic_skip_next,
            music = music)
    }
}

@Composable
fun ControlPanel(
    iconSkipPrevious: Int,
    iconFastRewind: Int,
    iconPlay: Int,
    iconPause: Int,
    iconForward: Int,
    iconSkipNext: Int,
    music: MediaPlayer,
) {
    var playPause by remember { mutableStateOf(iconPlay) }
    val xxx by remember { derivedStateOf { music.currentPosition } }

    LinearProgressIndicator(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        backgroundColor = Color.Gray,
        color = Color.Blue,
        progress = xxx.toFloat())

    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            if (music.currentPosition / music.duration <= 0.0025) {
                music.seekTo(0)
            } else {/*TODO("Если бы был список музыки, то было переключение")*/
            }
        }) {
            Image(painter = painterResource(id = iconSkipPrevious),
                contentDescription = "",
                modifier = Modifier.size(55.dp))
        }

        IconButton(onClick = { music.seekTo(music.currentPosition - 10000) }) {
            Image(painter = painterResource(id = iconFastRewind),
                contentDescription = "",
                modifier = Modifier.size(55.dp))
        }

        IconButton(onClick = {
            when (playPause) {
                iconPlay -> {
                    playPause = iconPause
                    music.start()
                }
                iconPause -> {
                    playPause = iconPlay
                    music.pause()
                }
            }
        }) {
            Image(painter = painterResource(id = playPause),
                contentDescription = "",
                modifier = Modifier.size(80.dp))

        }

        IconButton(onClick = { music.seekTo(music.currentPosition + 10000) }) {
            Image(painter = painterResource(id = iconForward),
                contentDescription = "",
                modifier = Modifier.size(55.dp))
        }

        IconButton(onClick = { music.seekTo(music.duration) /*TODO("Если бы был список музыки, то было переключение")*/ }) {
            Image(painter = painterResource(id = iconSkipNext),
                contentDescription = "",
                modifier = Modifier.size(55.dp))
        }
    }
}
