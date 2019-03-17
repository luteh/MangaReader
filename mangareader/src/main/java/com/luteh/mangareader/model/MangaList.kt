package com.luteh.mangareader.model

import com.google.gson.annotations.SerializedName

data class MangaList(
    @SerializedName("end")
    val end: Int,
    @SerializedName("manga")
    val manga: List<Manga>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)