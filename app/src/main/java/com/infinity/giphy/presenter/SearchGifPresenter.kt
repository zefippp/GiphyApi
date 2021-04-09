package com.infinity.giphy.presenter

import com.infinity.giphy.view.GifView

class SearchGifPresenter(private val view: GifView) {
    fun updateList() {
        view.opUpdateList()
    }

    init {
        view.initView()
    }
}