package com.luteh.comicreader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luteh.comicreader.R
import com.luteh.comicreader.model.Comic
import com.squareup.picasso.Picasso

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class MyComicAdapter(internal var context: Context,
                     internal var comicList: List<Comic>) : RecyclerView.Adapter<MyComicAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.comic_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(comicList[position].Image).into(holder.iv_comic_item)
        holder.tv_comic_name_item.text = comicList[position].Name
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_comic_item: ImageView
        var tv_comic_name_item: TextView

        init {
            iv_comic_item = itemView.findViewById(R.id.iv_comic_item) as ImageView
            tv_comic_name_item = itemView.findViewById(R.id.tv_comic_name_item) as TextView
        }
    }
}