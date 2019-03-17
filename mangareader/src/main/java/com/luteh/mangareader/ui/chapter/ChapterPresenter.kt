package com.luteh.mangareader.ui.chapter

import com.luteh.mangareader.common.Common
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Luthfan Maftuh on 14/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class ChapterPresenter : ChapterContract.Presenter {

    private lateinit var view: ChapterContract.View
    private val subscriptions = CompositeDisposable()

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ChapterContract.View) {
        this.view = view
    }

    override fun fetchChapterData() {
        Common.chapterList = Common.selectedComic!!.Chapters!!
        view.showChapterList(Common.chapterList)
    }
}