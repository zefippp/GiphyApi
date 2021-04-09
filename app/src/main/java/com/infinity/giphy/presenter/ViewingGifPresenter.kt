package com.infinity.giphy.presenter

import com.infinity.giphy.view.GifView

class ViewingGifPresenter(private val view: GifView) {
    init {
        view.initView()
    }
}