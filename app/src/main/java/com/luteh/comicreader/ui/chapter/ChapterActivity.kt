package com.luteh.comicreader.ui.chapter

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.luteh.comicreader.R
import com.luteh.comicreader.common.Common
import com.luteh.comicreader.common.base.BaseActivity
import com.luteh.comicreader.common.base.BaseContract
import com.luteh.comicreader.di.component.DaggerActivityComponent
import com.luteh.comicreader.di.module.ActivityModule
import com.luteh.comicreader.model.Chapter
import com.luteh.comicreader.model.Comic
import kotlinx.android.synthetic.main.activity_chapter.*
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class ChapterActivity : BaseActivity(), ChapterContract.View {
    override fun onInit() {

    }

    @Inject
    lateinit var presenter: ChapterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        presenter.attach(this)
        initView()
    }

    override fun injectDependencyIfNeed() {
        val chapterComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        chapterComponent.inject(this)
    }

    private fun initView() {
        initToolbar()
        initRecycler()

        presenter.fetchChapterData()
    }

    private fun initToolbar() {
        toolbar.title = Common.selectedComic!!.Name
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initRecycler() {
        rv_chapter.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rv_chapter.layoutManager = layoutManager
        rv_chapter.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }


    override fun showChapterList(chapterList: List<Chapter>) {
        tv_chapter.text = String.format("CHAPTER (%d)", chapterList.size)

        rv_chapter.adapter = MyChapterAdapter(this, chapterList)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}
