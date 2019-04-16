package com.luteh.mangareader.ui.activity.chapter

import com.luteh.mangareader.common.base.BaseContract
import com.luteh.mangareader.data.model.api.Chapter

/**
 * Created by Luthfan Maftuh on 14/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class ChapterContract {

    interface View : BaseContract.View {
        fun showChapterList(chapterList: List<Chapter>)

    }

    interface Presenter : BaseContract.Presenter<ChapterContract.View> {
        fun fetchChapterData()
    }
}