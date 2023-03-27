package com.example.m17_recyclerview.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.m17_recyclerview.dto.Photo
import com.example.m17_recyclerview.ui.theme.M17_recyclerviewTheme

@Composable
fun PhotoComponent(photo: Photo) {
    Column(
        modifier = Modifier
            .background(Color.Gray)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = photo.imgSrc,
            contentDescription = null,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Rover: ${photo.rover?.name}")
            Text(text = "Sol: ${photo.sol}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Camera: ${
                    photo.camera?.name
                }"
            )
            Text(text = "Date: ${photo.earthDate}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoComponent() {
    M17_recyclerviewTheme {
        PhotoComponent(Photo(null, null, null, "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG", null, null))
    }
}