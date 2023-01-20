package com.example.rawgbootcampidn.binding_adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.rawgbootcampidn.R
import com.example.rawgbootcampidn.model.ParentPlatform

object GameRowBindingAdapter {
    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
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
}