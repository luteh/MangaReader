package com.luteh.mangareader.ui.main

import com.luteh.mangareader.model.Comic
import com.luteh.mangareader.common.base.BaseContract
import com.luteh.mangareader.model.Manga

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class DiscoverContract {
    interface View : BaseContract.View {
        fun showErrorMessage(message: String)
        fun onSuccessLoadMangaListData(mangaList: List<Manga>)
    }

    interface Presenter : BaseContract.Presenter<DiscoverContract.View> {
        fun loadMangaListData()
    }
}