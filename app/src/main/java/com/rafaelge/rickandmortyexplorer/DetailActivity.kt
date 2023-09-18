package com.rafaelge.rickandmortyexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafaelge.rickandmortyexplorer.data.model.CharacterItem
import com.rafaelge.rickandmortyexplorer.ui.characterlist.BackgroundImage
import com.rafaelge.rickandmortyexplorer.ui.characterlist.CharacterDetailScreen
import com.rafaelge.rickandmortyexplorer.ui.theme.RickAndMortyExplorerTheme
import com.rafaelge.rickandmortyexplorer.utils.Extensions

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAndSetContent()
    }

    private fun checkAndSetContent() {
        val characterItem: CharacterItem? = intent.getSerializableExtra("characterItem") as? CharacterItem
        if (characterItem != null) {
            setContent {
                RickAndMortyExplorerTheme {
                    BackgroundImage(
                        backgroundImage = R.drawable.detail_background, // Reemplaza con el ID de tu imagen
                    ) {
                        CharacterDetailScreen(this, characterItem) {
                            finish()
                        }
                    }
                }
            }
        } else {
            Extensions.showDialog(this, getString(R.string.detail_error_message_dialog))
            finish()
        }
    }
}