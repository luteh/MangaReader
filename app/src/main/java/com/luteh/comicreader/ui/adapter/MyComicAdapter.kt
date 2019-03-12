package com.luteh.comicreader.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luteh.comicreader.ui.chapter.ChapterActivity
import com.luteh.comicreader.R
import com.luteh.comicreader.common.utils.IRecyclerClick
import com.luteh.comicreader.common.Common
import com.luteh.comicreader.model.Comic
import com.squareup.picasso.Picasso

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class MyComicAdapter(
    internal var context: Context,
    internal var comicList: MutableList<Comic>
) : RecyclerView.Adapter<MyComicAdapter.MyViewHolder>() {
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

        holder.setClick(object : IRecyclerClick {
            override fun onClick(view: View, position: Int) {
                context.startActivity(Intent(context, ChapterActivity::class.java))
                Common.selectedComic = comicList[position]
            }
        })
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            iRecyclerClick.onClick(v!!, adapterPosition)
        }

        var iv_comic_item: ImageView
        var tv_comic_name_item: TextView

        lateinit var iRecyclerClick: IRecyclerClick

        fun setClick(iRecyclerClick: IRecyclerClick) {
            this.iRecyclerClick = iRecyclerClick
        }

        init {
            iv_comic_item = itemView.findViewById(R.id.iv_comic_item) as ImageView
            tv_comic_name_item = itemView.findViewById(R.id.tv_comic_name_item) as TextView

            itemView.setOnClickListener(this)
        }
    }
}