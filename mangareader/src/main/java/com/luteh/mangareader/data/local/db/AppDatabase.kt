package com.luteh.mangareader.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luteh.mangareader.data.local.db.dao.MangaDao
import com.luteh.mangareader.data.model.db.Manga

/**
 * Created by Luthfan Maftuh on 16/04/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Database(entities = [Manga::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mangaDao(): MangaDao

    // TODO: 16/04/2019 Create repository class
}