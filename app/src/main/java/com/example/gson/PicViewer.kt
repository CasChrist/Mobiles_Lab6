package com.example.gson

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

class PicViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_viewer)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val imageView = findViewById<ImageView>(R.id.fullscreenImageView)
        val photoUrl = intent.getStringExtra("PhotoURL")

        photoUrl?.let {
            Glide.with(this).load(photoUrl).into(imageView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                val imageUrl = intent.getStringExtra("PhotoURL")
                val resultIntent = Intent().apply {
                    putExtra("PhotoURL", imageUrl)
                    putExtra("isFavorite", true)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
