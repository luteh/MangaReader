package com.luteh.comicreader.ui

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class BaseContract {
    interface Presenter<in T> {
        fun attach(view: T)
    }

    interface View {

    }
}