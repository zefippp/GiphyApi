package com.infinity.giphy.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.infinity.giphy.R
import com.infinity.giphy.model.search.Data
import com.infinity.giphy.view.GifActivity

class GifAdapter(private val values: List<Data>?, private var context: Context) :
    RecyclerView.Adapter<GifAdapter.MyViewHolder>() {

    override fun getItemCount() = values!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentImage = values!![position].images.downsized_medium
        val url = currentImage.url
        holder.progressBar!!.visibility = View.VISIBLE
        holder.image?.let {
            Glide.with(context)
                .asGif()
                .load(url)
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.progressBar!!.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: GifDrawable?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.progressBar!!.visibility = View.GONE
                        return false
                    }
                })
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
        var progressBar: ProgressBar? = null
        var backgroundPic: LinearLayout? = null

        init {
            image = itemView.findViewById(R.id.placePic)
            progressBar = itemView.findViewById(R.id.progressBar)
            backgroundPic = itemView.findViewById(R.id.backgroundPic)
        }
    }
}