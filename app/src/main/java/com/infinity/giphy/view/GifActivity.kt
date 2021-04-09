package com.infinity.giphy.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.infinity.giphy.R
import com.infinity.giphy.model.search.GifItem
import com.infinity.giphy.presenter.ViewingGifPresenter
import com.synnapps.carouselview.CarouselView
import java.util.*

class GifActivity : AppCompatActivity(), GifView {
    private var presenter: ViewingGifPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)
        presenter = ViewingGifPresenter(this@GifActivity)
    }

    override fun initView() {
        val carouselView = findViewById<CarouselView>(R.id.carouselView);
        val items = intent.getSerializableExtra("GIFS") as Array<GifItem>
        val positionClicked = intent.getSerializableExtra("POSITION") as Int
        carouselView.pageCount = items.size
        carouselView.currentItem = positionClicked
        carouselView.setImageListener { position, imageView ->
            imageView.let {
                imageView?.context?.let {
                    Glide.with(it)
                        .asGif()
                        .load(items[position].gif.url)
                        .into(imageView)
                }
            }
        }
    }
}