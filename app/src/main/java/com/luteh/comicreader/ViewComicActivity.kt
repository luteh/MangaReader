package com.luteh.comicreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.luteh.comicreader.adapter.MyViewPagerAdapter
import com.luteh.comicreader.common.Common
import com.luteh.comicreader.model.Chapter
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer
import kotlinx.android.synthetic.main.activity_view_comic.*

class ViewComicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comic)

        v_back.setOnClickListener {
            if (Common.chapterIndex == 0) {
//                If user in first chapter but press back
                Toast.makeText(this, "You are reading first chapter", Toast.LENGTH_SHORT).show()
            } else {
                Common.chapterIndex--
                fetchLinks(Common.chapterList[Common.chapterIndex])
            }
        }
        v_next.setOnClickListener {
            if (Common.chapterIndex == Common.chapterList.size - 1) {
//                If user in first chapter but press back
                Toast.makeText(this, "You are reading last chapter", Toast.LENGTH_SHORT).show()
            } else {
                Common.chapterIndex++
                fetchLinks(Common.chapterList[Common.chapterIndex])
            }
        }

        fetchLinks(Common.selectedChapter)
    }

    private fun fetchLinks(chapter: Chapter) {
        if (chapter.Links != null) {
            if (chapter.Links!!.size > 0) {
                val adapter = MyViewPagerAdapter(baseContext, chapter.Links!!)
                view_pager.adapter = adapter
                tv_view_comic_chapter_name.text = Common.formatString(Common.selectedChapter.Name!!)

//                Create book flip anim
                val bookFlipPageTransformer = BookFlipPageTransformer()
                bookFlipPageTransformer.scaleAmountPercent = 10f
                view_pager.setPageTransformer(true, bookFlipPageTransformer)
            } else {
                Toast.makeText(this, "No image here", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "This is latest chapter from author", Toast.LENGTH_SHORT).show()
        }
    }
}
