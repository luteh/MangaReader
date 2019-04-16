package com.luteh.mangareader.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "manga_table")
data class Manga(
    @SerializedName("a")
    val alias: String,
    @SerializedName("c")
    val category: List<String>,
    @SerializedName("h")
    val hits: Int,
    @field:PrimaryKey
    @SerializedName("i")
    val id: String,
    @SerializedName("im")
    val image: String,
    @ColumnInfo(name = "last_chapter_date")
    @SerializedName("ld")
    val lastChapterDate: Double,
    @SerializedName("s")
    val status: Int,
    @SerializedName("t")
    val title: String
)