package com.luteh.mangareader.ui.main

import com.luteh.mangareader.common.base.BaseContract
import com.luteh.mangareader.data.model.Manga

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class DiscoverContract {
    interface View : BaseContract.View {
        fun showErrorMessage(message: String)
        fun onSuccessLoadMangaListData(mangaList: MutableList<Manga>)
        fun onStartLoading()
        fun onFinishLoading()
    }

    interface Presenter : BaseContract.Presenter<DiscoverContract.View> {
        fun loadMangaListData()
    }
}