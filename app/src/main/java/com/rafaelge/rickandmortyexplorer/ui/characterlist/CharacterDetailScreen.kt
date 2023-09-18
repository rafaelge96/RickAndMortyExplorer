package com.rafaelge.rickandmortyexplorer.ui.characterlist

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.rafaelge.rickandmortyexplorer.R
import com.rafaelge.rickandmortyexplorer.data.model.CharacterItem
import com.rafaelge.rickandmortyexplorer.ui.theme.ButtonBackground
import com.rafaelge.rickandmortyexplorer.ui.theme.DetailBackground
import com.rafaelge.rickandmortyexplorer.utils.Constants

@Composable
fun CharacterDetailScreen(
    context: Context,
    character: CharacterItem,
    onBackClick: () -> Unit
) {
    val textStyleBody = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    )

    val textStyleName = TextStyle(
        fontSize = 25.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DetailBackground, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val imagePainter: Painter =
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = character.image).apply(block = fun ImageRequest.Builder.() {
                        transformations(CircleCropTransformation())
                    }).build()
                )
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                    .border(4.dp, Color.White, shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = character.name,
                style = textStyleName,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = context.getString(R.string.species_detail, character.species) + Constants.SEPARATOR + context.getString(R.string.gender_detail, character.gender),
                style = textStyleBody
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = context.getString(R.string.location_detail, character.location.name),
                style = textStyleBody
            )

            Text(
                text = context.getString(R.string.origin_detail, character.origin.name),
                style = textStyleBody
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = context.getString(R.string.status_detail, character.status),
                style = textStyleBody
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = { onBackClick() },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBackground)
            ) {
                Text(text = context.getString(R.string.detail_button))
            }
        }
    }
}