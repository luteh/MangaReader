package com.luteh.mangareader.data.model.api

import com.google.gson.annotations.SerializedName


data class MangaListResponse(
    @SerializedName("end")
    val end: Int,
    @SerializedName("manga")
    val manga: MutableList<Manga>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)