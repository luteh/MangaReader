package com.luteh.comicreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Images.ImageColumns.ORIENTATION
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.luteh.comicreader.adapter.MyChapterAdapter
import com.luteh.comicreader.common.Common
import com.luteh.comicreader.model.Comic
import kotlinx.android.synthetic.main.activity_chapter.*

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class ChapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        toolbar.title = Common.selectedComic!!.Name
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        rv_chapter.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rv_chapter.layoutManager = layoutManager
        rv_chapter.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        fetchChapter(Common.selectedComic!!)
    }

    private fun fetchChapter(comic: Comic) {
        Common.chapterList = comic.Chapters!!
        tv_chapter.text = String.format("CHAPTER (%d)", comic.Chapters!!.size)

        rv_chapter.adapter = MyChapterAdapter(this, Common.chapterList)
    }
}
