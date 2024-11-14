package com.example.gson

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import timber.log.Timber

class Adapter(private val photos: List<Photo>, private val context: Context) : RecyclerView.Adapter<Adapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val intent = Intent(context, PicViewer::class.java)
                    val link = buildImageUrl(photos[position])
                    intent.putExtra("PhotoURL", link)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        val imageUrl = buildImageUrl(photo)
        loadImage(holder.imageView, imageUrl)
    }

    override fun getItemCount(): Int = photos.size

    private fun buildImageUrl(photo: Photo): String {
        return "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg"
    }

    private fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .into(imageView)
    }
}
