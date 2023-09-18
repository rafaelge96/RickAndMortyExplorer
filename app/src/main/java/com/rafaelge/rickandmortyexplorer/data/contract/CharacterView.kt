package com.rafaelge.rickandmortyexplorer.data.contract

import com.rafaelge.rickandmortyexplorer.data.model.CharacterItem

interface CharacterView {
    fun onLoadCharacters(characterItems: List<CharacterItem>)
    fun onLoadFailed(errorMessage: String)
}