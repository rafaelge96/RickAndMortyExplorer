package com.rafaelge.rickandmortyexplorer.presenter.impl

import android.content.Context
import com.rafaelge.rickandmortyexplorer.R
import com.rafaelge.rickandmortyexplorer.data.model.CharacterResponse
import com.rafaelge.rickandmortyexplorer.data.repository.ApiService
import com.rafaelge.rickandmortyexplorer.data.contract.CharacterView
import com.rafaelge.rickandmortyexplorer.presenter.CharacterPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterPresenterImpl(private val context: Context, private val apiService: ApiService) : CharacterPresenter {
    private var view: CharacterView? = null
    private var currentPage = 1

    override fun attachView(view: CharacterView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getCharacters() {
        apiService.getCharacters(currentPage).enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    val characters = response.body()?.results
                    view?.onLoadCharacters(characters ?: emptyList()) // Utilizar una lista vacía si characters es nulo
                } else {
                    view?.onLoadFailed(context.getString(R.string.request_error_message_dialog))
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                view?.onLoadFailed(context.getString(R.string.network_error_message_dialog))
            }
        })
    }

    override fun setCurrentPage(currentPage: Int) {
        this.currentPage = currentPage
    }

    override fun getCurrentPage(): Int = currentPage
}