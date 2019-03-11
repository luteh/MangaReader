package com.luteh.comicreader.`interface`

import com.luteh.comicreader.model.Comic

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
interface IComicLoadDoneListener {
    fun onComicLoadDoneListener(comicList: List<Comic>)
}