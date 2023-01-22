package com.example.rawgbootcampidn.binding_adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.rawgbootcampidn.R
import com.example.rawgbootcampidn.model.Developer
import com.example.rawgbootcampidn.model.Genre
import com.example.rawgbootcampidn.model.ParentPlatform
import com.example.rawgbootcampidn.model.Publisher

object GameBindingAdapter {
    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
        val placeHolderDrawable = R.drawable.img_placeholder
        Glide.with(imageView.context).load(imageUrl).placeholder(placeHolderDrawable)
            .error(placeHolderDrawable)
            .into(imageView)
    }

    @BindingAdapter("setParentPlatform")
    @JvmStatic
    fun setParentPlatform(textView: TextView, parentPlatforms: List<ParentPlatform?>?) {
        val textParentPlatform =
            parentPlatforms?.map { parent -> parent?.platform?.name }?.joinToString(
                separator = " ",
                limit = 3,
                truncated = "..."
            )
        textView.text = textParentPlatform

    }

    @BindingAdapter("setGenre")
    @JvmStatic
    fun setGenre(textView: TextView, genres: List<Genre?>?) {
        val textGenres =
            genres?.map { genre -> genre?.name }?.joinToString(
                separator = " ",
                limit = 3,
                truncated = "..."
            )
        textView.text = textGenres
    }

    @BindingAdapter("setDevelopers")
    @JvmStatic
    fun setDevelopers(textView: TextView, developers: List<Developer?>?) {
        val textDeveloper =
            developers?.map { developer -> developer?.name }?.joinToString(
                separator = " ",
                limit = 3,
                truncated = "..."
            )
        textView.text = textDeveloper
    }

    @BindingAdapter("setPublishers")
    @JvmStatic
    fun setPublishers(textView: TextView, publishers: List<Publisher?>?) {
        val textPublishers =
            publishers?.map { publisher -> publisher?.name }?.joinToString(
                separator = " ",
                limit = 3,
                truncated = "..."
            )
        textView.text = textPublishers
    }
}
