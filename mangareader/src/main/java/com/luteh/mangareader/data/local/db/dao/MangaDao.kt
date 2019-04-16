package com.luteh.mangareader.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luteh.mangareader.data.model.db.Manga

/**
 * Created by Luthfan Maftuh on 16/04/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Dao
interface MangaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(manga: Manga)

    @Update
    fun update(manga: Manga)

    @Delete
    fun delete(manga: Manga)

    /** Delete all [Manga] data from table manga_table */
    @Query("DELETE FROM manga_table")
    fun deleteAllMangas()

    /**
     * Get all [Manga] datas from table manga_table
     * Wrap List<Manga> with LiveData to observe any changed [Manga] data on manga_table
     */
    @Query("SELECT * FROM manga_table")
    fun getAllMangas(): LiveData<List<Manga>>
}