package com.aliozdemir.cryptoapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aliozdemir.cryptoapp.utils.loadImage

class CoinBinding {
    companion object {
        //https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, coinImage: String) {
            val imageUrl = "https://s2.coinmarketcap.com/static/img/coins/64x64/$coinImage.png"
            imageView.loadImage(imageUrl)
        }
    }
}