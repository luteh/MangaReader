package com.luteh.mangareader.common

import com.luteh.mangareader.data.model.api.Chapter
import com.luteh.mangareader.data.model.api.Comic
import com.luteh.mangareader.data.model.api.Manga

/**
 * Created by Luthfan Maftuh on 11/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
object Common {
    fun formatString(name: String): String {
        val finalResult =
            StringBuilder(if (name.length > 15) name.substring(0, 15) + "..." else name)
        return finalResult.toString()
    }

    var comicList: MutableList<Comic> = ArrayList()
    var selectedComic: Comic? = null
    lateinit var chapterList: List<Chapter>
    lateinit var selectedChapter: Chapter
    var chapterIndex: Int = -1
    var mangaList: MutableList<Manga> = ArrayList()

    var categories = arrayOf(
        "Action",
        "Adult",
        "Adventure",
        "Comedy",
        "Completed",
        "Cooking",
        "Doujinshi",
        "Drama",
        "Drop",
        "Ecchi",
        "Fantasy",
        "Gender bender",
        "Harem",
        "Historical",
        "Horror",
        "Jose",
        "Latest",
        "Manhua",
        "Manhwa",
        "Material arts",
        "Mature",
        "Mecha",
        "Medical",
        "Mystery",
        "Newest",
        "One shot",
        "Ongoing",
        "Psychological",
        "Romance",
        "School life",
        "Sci fi",
        "Seinen",
        "Shoujo",
        "Shoujo a",
        "Shounen",
        "Shounen ai",
        "Slice of life",
        "Smut",
        "Sports",
        "Superhero",
        "Supernatural",
        "Top Read",
        "Tragedy",
        "Webtoons",
        "Yaoi",
        "Yuri"
    )
}