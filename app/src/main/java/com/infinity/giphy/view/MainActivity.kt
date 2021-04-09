package com.infinity.giphy.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.infinity.giphy.model.search.GifItem
import com.infinity.giphy.adapter.GifAdapter
import com.infinity.giphy.R
import com.infinity.giphy.api.NetworkService
import com.infinity.giphy.model.search.SearchGif
import com.infinity.giphy.presenter.SearchGifPresenter
import com.infinity.giphy.presenter.ViewingGifPresenter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), GifView {
    private var presenter: SearchGifPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = SearchGifPresenter(this@MainActivity)
    }

    override fun opUpdateList() {
        val items: MutableList<GifItem> = ArrayList()

        NetworkService.instance
            ?.getAPI()
            ?.searchGif(etSearchGif.text.toString())
            ?.enqueue(object : Callback<SearchGif?> {
                override fun onResponse(
                    call: Call<SearchGif?>,
                    response: Response<SearchGif?>
                ) {
                    val post: SearchGif = response.body()!!
                    items.clear()
                    post.data.forEach {
                        val item = GifItem(
                            it.images.downsized_medium
                        )
                        items.add(item)
                    }

                    val staggeredGridLayoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    rvGif.layoutManager =
                        staggeredGridLayoutManager

                    val gifAdapter = GifAdapter(items, this@MainActivity)
                    rvGif.adapter = gifAdapter
                }

                override fun onFailure(call: Call<SearchGif?>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    override fun initView() {
        val etSearchGif = findViewById<EditText>(R.id.etSearchGif)

        etSearchGif.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    presenter?.updateList()
                    return true
                }
                return false
            }
        })
    }
}