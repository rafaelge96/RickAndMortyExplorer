package com.rafaelge.rickandmortyexplorer.ui.characterlist

import AppBarSearchBar
import PaginationRow
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.rafaelge.rickandmortyexplorer.DetailActivity
import com.rafaelge.rickandmortyexplorer.R
import com.rafaelge.rickandmortyexplorer.data.model.CharacterItem
import com.rafaelge.rickandmortyexplorer.presenter.impl.CharacterPresenterImpl
import com.rafaelge.rickandmortyexplorer.ui.theme.ListBackground
import com.rafaelge.rickandmortyexplorer.ui.theme.SearchBackground
import com.rafaelge.rickandmortyexplorer.utils.Constants

@Composable
fun CharacterListScreen(context: Context, presenter: CharacterPresenterImpl, characterItems: List<CharacterItem>) {
    var searchText by remember { mutableStateOf(Constants.EMPTY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppBarSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            backgroundColor = SearchBackground,
            contentColor = Color.White,
            onValueChange = { searchText = it }
        )

        PaginationRow(presenter.getCurrentPage(), Constants.ALL_PAGES) { page ->
            presenter.setCurrentPage(page)
            presenter.getCharacters()
        }

        LazyColumn {
            val filteredCharacters = characterItems.filter { character ->
                character.name.contains(searchText, ignoreCase = true)
            }

            items(filteredCharacters) { character ->
                CharacterListItem(
                    context = context,
                    characterItem = character,
                    onItemClick = {
                        navigateToCharacterDetail(context, character)
                    }
                )
            }
        }
    }
}

@Composable
fun CharacterListItem(
    context: Context,
    characterItem: CharacterItem,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ListBackground)
            .clickable { onItemClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val imagePainter: Painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = characterItem.image).apply(block = fun ImageRequest.Builder.() {
                    transformations(CircleCropTransformation())
                }).build()
            )

        Image(
            painter = imagePainter,
            contentDescription = characterItem.name,
            modifier = Modifier
                .size(80.dp)
                .padding(4.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(text = characterItem.name, fontSize = 18.sp)
            Text(text = context.getString(R.string.status_detail, characterItem.status))
            Text(text = context.getString(R.string.species_detail, characterItem.species))
        }
    }
}

private fun navigateToCharacterDetail(context: Context, characterItem: CharacterItem) {
    context.startActivity(
        Intent(context, DetailActivity::class.java).apply {
            putExtra(Constants.CHARACTER_EXTRA, characterItem)
        }
    )
}