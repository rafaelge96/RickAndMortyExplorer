package com.rafaelge.rickandmortyexplorer.ui.characterlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.rafaelge.rickandmortyexplorer.utils.Constants

@Composable
fun BackgroundImage(
    backgroundImage: Int,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = Constants.IMG_DESCRIPTION_DEFAULT,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        content()
    }
}