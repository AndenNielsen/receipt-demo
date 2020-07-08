package com.demo.receipt

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("imageUri")
fun bindImageUri(imageView: ImageView, uri: String) {
    imageView.load(Uri.parse(uri))
}