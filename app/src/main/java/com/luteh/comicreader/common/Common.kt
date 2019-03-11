package com.luteh.comicreader.common

import com.luteh.comicreader.model.Chapter
import com.luteh.comicreader.model.Comic

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
object Common {
    fun formatString(name: String): String {
        val finalResult = StringBuilder(if (name.length > 15) name.substring(0, 15) + "..." else name)
        return finalResult.toString()
    }

    var comicList: List<Comic> = ArrayList()
    var selectedComic: Comic? = null
    lateinit var chapterList: List<Chapter>
    lateinit var selectedChapter: Chapter
    var chapterIndex: Int = -1

}