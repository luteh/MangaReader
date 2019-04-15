package com.luteh.mangareader.ui.activity.chapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luteh.mangareader.R
import com.luteh.mangareader.ui.activity.viewcomic.ViewComicActivity
import com.luteh.mangareader.common.utils.IRecyclerClick
import com.luteh.mangareader.common.Common
import com.luteh.mangareader.data.model.Chapter

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class MyChapterAdapter(
        internal var context: Context,
        internal var chapterList: List<Chapter>
) : RecyclerView.Adapter<MyChapterAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.chapter_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_chapter_number_item.text = chapterList[position].Name

        holder.setClick(object : IRecyclerClick {
            override fun onClick(view: View, position: Int) {
                Common.selectedChapter = chapterList[position]
                Common.chapterIndex = position
                context.startActivity(Intent(context, ViewComicActivity::class.java))
            }
        })
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            iRecyclerClick.onClick(v!!, adapterPosition)
        }

        internal var tv_chapter_number_item: TextView
        internal lateinit var iRecyclerClick: IRecyclerClick

        fun setClick(iRecyclerClick: IRecyclerClick) {
            this.iRecyclerClick = iRecyclerClick
        }

        init {
            tv_chapter_number_item = itemView.findViewById(R.id.tv_chapter_number_item) as TextView

            itemView.setOnClickListener(this)
        }
    }
}