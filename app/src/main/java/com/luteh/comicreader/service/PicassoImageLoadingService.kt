package com.luteh.comicreader.service

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ss.com.bannerslider.ImageLoadingService

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class PicassoImageLoadingService : ImageLoadingService {
    override fun loadImage(url: String?, imageView: ImageView?) {
        Picasso.get().load(url).into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Picasso.get().load(resource).into(imageView)
    }

    override fun loadImage(url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?) {
        Picasso.get().load(url).placeholder(placeHolder).error(errorDrawable).into(imageView)
    }
}