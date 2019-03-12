package com.luteh.comicreader.ui.main

import com.luteh.comicreader.model.Comic
import com.luteh.comicreader.common.base.BaseContract

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class MainContract {
    interface View : BaseContract.View {
        fun showErrorMessage(message: String)
        fun onBannerLoadDoneListener(bannerList: ArrayList<String>)
        fun onComicLoadDoneListener(comicList: MutableList<Comic>)
    }

    interface Presenter : BaseContract.Presenter<MainContract.View> {
        fun loadBannerData()
        fun loadComicData()
    }
}