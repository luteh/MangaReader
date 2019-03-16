package com.luteh.comicreader.ui.main

import com.luteh.comicreader.model.Comic
import com.luteh.comicreader.common.base.BaseContract
import com.luteh.comicreader.model.Manga

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class DiscoverContract {
    interface View : BaseContract.View {
        fun showErrorMessage(message: String)
        fun onBannerLoadDoneListener(bannerList: ArrayList<String>)
        fun onComicLoadDoneListener(comicList: MutableList<Comic>)
        fun onSuccessLoadMangaListData(mangaList: List<Manga>)
    }

    interface Presenter : BaseContract.Presenter<DiscoverContract.View> {
        fun loadBannerData()
        fun loadComicData()
        fun loadMangaListData()
    }
}