package com.rafaelge.rickandmortyexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafaelge.rickandmortyexplorer.data.model.CharacterItem
import com.rafaelge.rickandmortyexplorer.presenter.impl.CharacterPresenterImpl
import com.rafaelge.rickandmortyexplorer.data.contract.CharacterView
import com.rafaelge.rickandmortyexplorer.data.repository.RetrofitInstance
import com.rafaelge.rickandmortyexplorer.ui.characterlist.BackgroundImage
import com.rafaelge.rickandmortyexplorer.ui.characterlist.CharacterListScreen
import com.rafaelge.rickandmortyexplorer.ui.theme.RickAndMortyExplorerTheme
import com.rafaelge.rickandmortyexplorer.utils.Extensions

class MainActivity : ComponentActivity(), CharacterView {

    private lateinit var presenter: CharacterPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = CharacterPresenterImpl(this, RetrofitInstance.apiService)
        presenter.attachView(this)
        presenter.getCharacters()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onLoadCharacters(newCharacterItems: List<CharacterItem>) {
        setContent {
            RickAndMortyExplorerTheme {
                BackgroundImage(
                    backgroundImage = R.drawable.main_background,
                ) {
                    CharacterListScreen(this, presenter, newCharacterItems)
                }
            }
        }
    }

    override fun onLoadFailed(errorMessage: String) {
        Extensions.showDialog(this, errorMessage)
    }
}