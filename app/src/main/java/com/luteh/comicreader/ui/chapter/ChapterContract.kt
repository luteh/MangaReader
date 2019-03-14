package com.luteh.comicreader.ui.chapter

import com.luteh.comicreader.common.base.BaseContract
import com.luteh.comicreader.model.Chapter

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