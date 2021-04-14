package com.infinity.giphy.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.infinity.giphy.R
import com.infinity.giphy.adapter.GifAdapter
import com.infinity.giphy.api.RetrofitBuilder
import com.infinity.giphy.presenter.SearchGifPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import java.util.*

class MainActivity : AppCompatActivity(), GifView, CoroutineScope by MainScope() {

    private var presenter: SearchGifPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = SearchGifPresenter(this@MainActivity)
    }

    override fun onUpdateList() {
        GlobalScope.launch(Dispatchers.Main) {
            val getItems = async(IO) {
                RetrofitBuilder.instance
                    ?.getAPI()
                    ?.searchGif(etSearchGif.text.toString())?.body()
            }
            try {
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                rvGif.layoutManager =
                    staggeredGridLayoutManager

                val gifAdapter = GifAdapter(getItems.await()?.data, this@MainActivity)
                rvGif.adapter = gifAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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