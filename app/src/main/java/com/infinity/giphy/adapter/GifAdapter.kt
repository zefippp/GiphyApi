package com.infinity.giphy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infinity.giphy.R
import com.infinity.giphy.model.search.GifItem
import com.infinity.giphy.view.GifActivity

class GifAdapter(private val values: List<GifItem>, context: Context) :
    RecyclerView.Adapter<GifAdapter.MyViewHolder>() {

    private var context = context
    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = values[position]
        holder.image?.let {
            Glide.with(context)
                .asGif()
                .load(item.gif.url)
                .into(it)
        }
        holder.image!!.setOnClickListener {
            val intent = Intent(
                context,
                GifActivity::class.java
            )
                .putExtra("GIFS", values.toTypedArray())
                .putExtra("POSITION", position)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView? = null

        init {
            image = itemView.findViewById(R.id.placePic)
        }
    }
}