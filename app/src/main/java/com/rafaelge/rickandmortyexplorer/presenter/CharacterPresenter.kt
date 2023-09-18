package com.rafaelge.rickandmortyexplorer.presenter

import com.rafaelge.rickandmortyexplorer.data.contract.CharacterView

interface CharacterPresenter {
    fun attachView(view: CharacterView)
    fun detachView()
    fun getCharacters()
    fun setCurrentPage(currentPage: Int)
    fun getCurrentPage(): Int
}