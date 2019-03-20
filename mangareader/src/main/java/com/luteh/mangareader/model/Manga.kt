package com.luteh.mangareader.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Manga(
    @SerializedName("a")
    val alias: String,
    @SerializedName("c")
    val category: List<String>,
    @SerializedName("h")
    val hits: Int,
    @SerializedName("i")
    val id: String,
    @SerializedName("im")
    val image: String,
    @SerializedName("ld")
    val lastChapterDate: Double,
    @SerializedName("s")
    val status: Int,
    @SerializedName("t")
    val title: String
)